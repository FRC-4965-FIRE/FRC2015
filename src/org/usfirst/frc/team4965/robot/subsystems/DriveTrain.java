package org.usfirst.frc.team4965.robot.subsystems;

import org.usfirst.frc.team4965.robot.Robot;
import org.usfirst.frc.team4965.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.DigitalInput;


import org.usfirst.frc.team4965.robot.commands.JoystickDrive;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
	public static DriveTrain instance;
	RobotDrive drive;
  Encoder enc; 
  Victor krum;
  Jaguar dummyPID;
  Victor dummyPID2;
  PIDController drivePID;
  PIDController turnPID;
  Gyro gyroscope;
  
  
  private static final double driveKp = 0.05;
  private static final double driveKi = 0.0;
  private static final double driveKd = 0.0;
  
  private static final double turnKp = 0.01;
  private static final double turnKi = 0.0;
  private static final double turnKd = 0.0;
	
  public static DriveTrain getInstance()
    {
        if(instance == null)
            instance = new DriveTrain();
    
        return instance;
    }
    
    private DriveTrain() {
        super("DriveTrain");
       
        drive = new RobotDrive(new Jaguar(RobotMap.LeftFront), new Jaguar(RobotMap.LeftBack), 
                                    new Jaguar(RobotMap.RightFront), new Jaguar(RobotMap.RightBack));
      
        gyroscope = new Gyro(RobotMap.Gyro);
      
        enc = new Encoder(RobotMap.EncoderOne_A, RobotMap.EncoderOne_B);
        dummyPID = new Jaguar(10);
        dummyPID2 = new Victor(9);
      
        krum = new Victor(RobotMap.TestVictor);
      
        drivePID = new PIDController(driveKp, driveKi, driveKd, enc, dummyPID2);
        turnPID = new PIDController(turnKp, turnKi, turnKd, gyroscope, dummyPID);
      
        turnPID.setContinuous(true);      
        turnPID.setAbsoluteTolerance(0.2);
        drivePID.setAbsoluteTolerance(0.2);
      
    }
	
    public int getEncoder()
    {
      return enc.get();
    }
  
    public void runVictor(double speed)
    {
      krum.set(speed);
    }
  
    public void stopVictor()
    {
      krum.set(0);
    }

    public void initDefaultCommand() 
    {
        setDefaultCommand(new JoystickDrive());
    }
  
    public PIDController getDrivePID()
    {
        return drivePID;
    }
  
    public PIDController getTurnPID()
    {
        return turnPID;
    }
  
    public void drivePID()
    {
        double driveOutput;
        double turnOutput;
        
        driveOutput = drivePID.get();
        turnOutput = turnPID.get();
        SmartDashboard.putNumber("Encoder PID Out", driveOutput);
        SmartDashboard.putNumber("Gyro PID Out", turnOutput);
        drive.mecanumDrive_Cartesian(0.0, driveOutput, turnOutput, 0.0);
    }
  
    public void strafePID()
    {
        double driveOutput;
        double turnOutput;
        
        driveOutput = drivePID.get();
        turnOutput = turnPID.get();
        SmartDashboard.putNumber("Encoder PID Out", driveOutput);
        SmartDashboard.putNumber("Gyro PID Out", turnOutput);
        SmartDashboard.putNumber("Gyro Angle Out", gyroscope.getAngle());
        SmartDashboard.putNumber("Encoder 1", Robot.drivetrain.getEncoder());
        //drive.mecanumDrive_Cartesian(driveOutput, 0.0, turnOutput, 0.0);
        this.runVictor(driveOutput);
    }    
    
    public void turnPID()
    {
    	double output;
    	 
    	output = turnPID.get();
    	
    	SmartDashboard.putNumber("Gyro Angle Out", gyroscope.getAngle());
    	
    	drive.mecanumDrive_Cartesian(0.0, 0.0, output, 0.0);
    }
    
    public void drive(double LeftSpeed, double RightSpeed)
    {
        drive.tankDrive(LeftSpeed, RightSpeed);
    }
    
    public void mecanumDrive(double X, double Y, double Rotation, double Gyro)
    {
        drive.mecanumDrive_Cartesian(X, Y, Rotation, Gyro);
    }
    
    public void Strafe(double X)
    {
       drive.mecanumDrive_Cartesian(X, 0, 0, 0);
    }
    
    public void ExtendedTankDrive(double Left, double Right, double LeftStrafe, double RightStrafe)
    {
       if(LeftStrafe < 0.1 && RightStrafe < 0.1)
       {
          drive.tankDrive(Right, Left);
       }
       else
       {
          if(RightStrafe > 0)
          {
            drive.mecanumDrive_Cartesian(-RightStrafe, 0, 0, 0);
          }
          else
          {
            drive.mecanumDrive_Cartesian(LeftStrafe, 0, 0, 0);
          }
       }
     }
    
    public double getAngle()
    {
      return gyroscope.getAngle();
    }
    
    public void resetGyro()
    {
      gyroscope.reset();       
    }
  
    public void resetEncoder()
    {
      enc.reset();
    }
    
}
    

