
package org.usfirst.frc.team4965.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import java.lang.Math;
import java.util.Comparator;
import java.util.Vector;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.USBCamera;


/**
 *
 */
public class EyeOfSauron extends Subsystem {
    
 	
	     int session;
	     
		//A structure to hold measurements of a particle
		public class ParticleReport implements Comparator<ParticleReport>, Comparable<ParticleReport>
		{
			double PercentAreaToImageArea;
			double Area;
			double ConvexHullArea;
			double BoundingRectLeft;
			double BoundingRectTop;
			double BoundingRectRight;
			double BoundingRectBottom;
			double Orientation;
			
			public int compareTo(ParticleReport r)
			{
				return (int)(r.Area - this.Area);
			}
			
			public int compare(ParticleReport r1, ParticleReport r2)
			{
				return (int)(r1.Area - r2.Area);
			}
		};

		//Images
		Image frame;
		Image binaryFrame;
		int imaqError;

		//Constants
		NIVision.Range TOTE_HUE_RANGE = new NIVision.Range(0, 20);	
		NIVision.Range TOTE_SAT_RANGE = new NIVision.Range(55, 130);
		NIVision.Range TOTE_VAL_RANGE = new NIVision.Range(125, 255);
		double AREA_MINIMUM = 5.5; //Default Area minimum for particle as a percentage of total image area
		double LONG_RATIO = 2.22; //Tote long side = 26.9 / Tote height = 12.1 = 2.22
		double SHORT_RATIO = 1.4; //Tote short side = 16.9 / Tote height = 12.1 = 1.4
		double SCORE_MIN = 75.0;  //Minimum score to be considered a tote
		double VIEW_ANGLE = 49.4; //View angle fo camera, set to Axis m1011 by default (49.4), 64 for m1013, 51.7 for 206, 52 for HD3000 square, 60 for HD3000 640x480
		NIVision.ParticleFilterCriteria2 criteria[] = new NIVision.ParticleFilterCriteria2[1];
		NIVision.ParticleFilterOptions2 filterOptions = new NIVision.ParticleFilterOptions2(0,0,1,1);
		
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
  
		//Comparator function for sorting particles. Returns true if particle 1 is larger
		static boolean CompareParticleSizes(ParticleReport particle1, ParticleReport particle2)
		{
			//we want descending sort order
			return particle1.PercentAreaToImageArea > particle2.PercentAreaToImageArea;
		}

		/**
		 * Computes the estimated distance to a target using the width of the particle in the image. For more information and graphics
		 * showing the math behind this approach see the Vision Processing section of the ScreenStepsLive documentation.
		 *
		 * @param image The image to use for measuring the particle estimated rectangle
		 * @param report The Particle Analysis Report for the particle
		 * @param isLong Boolean indicating if the target is believed to be the long side of a tote
		 * @return The estimated distance to the target in feet.
		 */
		double computeDistance (Image image, ParticleReport report, boolean isLong) {
			double normalizedWidth, targetWidth;
			NIVision.GetImageSizeResult size;

			size = NIVision.imaqGetImageSize(image);
			normalizedWidth = 2*(report.BoundingRectRight - report.BoundingRectLeft)/size.width;
			targetWidth = isLong ? 26.0 : 16.9;

			return  targetWidth/(normalizedWidth*12*Math.tan(VIEW_ANGLE*Math.PI/(180*2)));
		}
		
		public void autonomous() {
			//while (isAutonomous() && isEnabled())
			{
				//read file in from disk. For this example to run you need to copy image20.jpg from the SampleImages folder to the
				//directory shown below using FTP or SFTP: http://wpilib.screenstepslive.com/s/4485/m/24166/l/282299-roborio-ftp
				//NIVision.IMAQdxStartAcquisition(session);
				
				frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
				NIVision.IMAQdxGrab(session, frame, 1);
				
				//Update threshold values from SmartDashboard. For performance reasons it is recommended to remove this after calibration is finished.
				TOTE_HUE_RANGE.minValue = (int)SmartDashboard.getNumber("Tote hue min", TOTE_HUE_RANGE.minValue);
				TOTE_HUE_RANGE.maxValue = (int)SmartDashboard.getNumber("Tote hue max", TOTE_HUE_RANGE.maxValue);
				TOTE_SAT_RANGE.minValue = (int)SmartDashboard.getNumber("Tote sat min", TOTE_SAT_RANGE.minValue);
				TOTE_SAT_RANGE.maxValue = (int)SmartDashboard.getNumber("Tote sat max", TOTE_SAT_RANGE.maxValue);
				TOTE_VAL_RANGE.minValue = (int)SmartDashboard.getNumber("Tote val min", TOTE_VAL_RANGE.minValue);
				TOTE_VAL_RANGE.maxValue = (int)SmartDashboard.getNumber("Tote val max", TOTE_VAL_RANGE.maxValue);

				//Threshold the image looking for yellow (tote color)
				NIVision.imaqColorThreshold(binaryFrame, frame, 255, NIVision.ColorMode.HSV, TOTE_HUE_RANGE, TOTE_SAT_RANGE, TOTE_VAL_RANGE);

				//Send particle count to dashboard
				int numParticles = NIVision.imaqCountParticles(binaryFrame, 1);
				SmartDashboard.putNumber("Masked particles", numParticles);

				//Send masked image to dashboard to assist in tweaking mask.
				CameraServer.getInstance().setImage(binaryFrame);

				//filter out small particles
				float areaMin = (float)SmartDashboard.getNumber("Area min %", AREA_MINIMUM);
				criteria[0].lower = areaMin;
				imaqError = NIVision.imaqParticleFilter4(binaryFrame, binaryFrame, criteria, filterOptions, null);

				//Send particle count after filtering to dashboard
				numParticles = NIVision.imaqCountParticles(binaryFrame, 1);
				SmartDashboard.putNumber("Filtered particles", numParticles);

				if(numParticles > 0)
				{
					//Measure particles and sort by particle size
					Vector<ParticleReport> particles = new Vector<ParticleReport>();
					for(int particleIndex = 0; particleIndex < numParticles; particleIndex++)
					{
						ParticleReport par = new ParticleReport();
						par.PercentAreaToImageArea = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA);
						par.Area = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_AREA);
						par.ConvexHullArea = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_CONVEX_HULL_AREA);
						par.Orientation = NIVision.imaqMeasureParticle(binaryFrame, particleIndex, 0, NIVision.MeasurementType.MT_ORIENTATION);
						particles.add(par);
					}
					particles.sort(null);
					
					//first particle should be largest, and the floor pattern
					SmartDashboard.putNumber("Orientation", particles.get(0).Orientation);
				} 
				else 
				{
					
				}
				

				Timer.delay(0.005);				// wait for a motor update time
			}
		}
  
		public void operatorControl() {
			NIVision.IMAQdxStartAcquisition(session);

	        /**
	         * grab an image, draw the circle, and provide it for the camera server
	         * which will in turn send it to the dashboard.
	         */
	        NIVision.Rect rect = new NIVision.Rect(10, 10, 100, 100);

	        //while (isOperatorControl() && isEnabled()) 
	        {

	            NIVision.IMAQdxGrab(session, frame, 1);
	            NIVision.imaqDrawShapeOnImage(frame, frame, rect,
	                    DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f);
	            
	            CameraServer.getInstance().setImage(frame);

	            /** robot code here! **/
	            Timer.delay(0.005);		// wait for a motor update time
	        }
	       //NIVision.IMAQdxStopAcquisition(session);
		}
}