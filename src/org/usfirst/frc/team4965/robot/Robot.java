
package org.usfirst.frc.team4965.robot;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler; 
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team4965.robot.subsystems.*;
import org.usfirst.frc.team4965.robot.commands.ExampleCommand;
import org.usfirst.frc.team4965.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team4965.robot.commands.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;
	public static DriveTrain drivetrain;
    public static Lift lift;
    public static Intake intake;
    //public static CameraServer server;
    public static EyeOfSauron camera;

    Command autonomousCommand;
    Command teleopCommand;
    
    //int session;
    Image frame;
    

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		//first thing MUST be to create the subsystems
		drivetrain = DriveTrain.getInstance();
        lift = Lift.getInstance();
        intake = Intake.getInstance();
        
    // instantiate the command used for the autonomous period
		oi = new OI();
        autonomousCommand = new DriveForTime(1.75);
        teleopCommand = new JoystickDrive();
        //SmartDashboard.putData("AutoStrafe", new AutoStrafe(200.0, 0));
        
        //frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

        // the camera name (ex "cam0") can be found through the roborio web interface
        //session = NIVision.IMAQdxOpenCamera("cam0",
        //NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        //NIVision.IMAQdxConfigureGrab(session);

        //CameraServer.getInstance().startAutomaticCapture("cam0");
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)a 
       //if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        if (autonomousCommand != null) autonomousCommand.cancel();
        
        Scheduler.getInstance().add(teleopCommand);
        //NIVision.IMAQdxStartAcquisition(session);
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        
        //NIVision.IMAQdxGrab(session, frame, 1);
        //CameraServer.getInstance().setImage(frame);
        SmartDashboard.putNumber("Gyro Angle Out", drivetrain.getAngle());
        
        
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
