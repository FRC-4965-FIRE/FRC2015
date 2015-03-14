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
  Encoder encoderOne, encoderTwo, encoderThree, encoderFour; 
  Victor frontLeft, frontRight, rearLeft, rearRight;
  Jaguar dummyPID;
  Victor dummyPID2;
  PIDController drivePID_FL, drivePID_FR, drivePID_RL, drivePID_RR;
  PIDController turnPID;
  public Gyro gyroscope;
  public boolean switchDrive = false;
  
  
  private static final double driveKp = 0.025;
  private static final double driveKi = 0.01;
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
        
        frontLeft = new Victor(RobotMap.LeftFront);
        frontRight = new Victor(RobotMap.RightFront);
        rearLeft = new Victor(RobotMap.LeftBack);
        rearRight = new Victor(RobotMap.RightBack);
          
        drive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
      
        drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        
        gyroscope = new Gyro(RobotMap.Gyro);
      
        encoderOne   = new Encoder(RobotMap.EncoderOne_A, RobotMap.EncoderOne_B);
        encoderTwo   = new Encoder(RobotMap.EncoderTwo_A, RobotMap.EncoderTwo_B);
        encoderThree = new Encoder(RobotMap.EncoderThree_A, RobotMap.EncoderThree_B);
        encoderFour  = new Encoder(RobotMap.EncoderFour_A, RobotMap.EncoderFour_B);
      
        dummyPID = new Jaguar(10);
        dummyPID2 = new Victor(9);
      
        drivePID_FR = new PIDController(driveKp, driveKi, driveKd, encoderOne, frontRight);
        drivePID_FL = new PIDController(driveKp, driveKi, driveKd, encoderTwo, frontLeft);
        drivePID_RR = new PIDController(driveKp, driveKi, driveKd, encoderThree, rearRight);
        drivePID_RL = new PIDController(driveKp, driveKi, driveKd, encoderFour, rearLeft);
      
        //100% is FAST, limit to 75
        drivePID_FL.setOutputRange(-0.75, 0.75);
        drivePID_FR.setOutputRange(-0.75, 0.75);
        drivePID_RL.setOutputRange(-0.75, 0.75);
        drivePID_RR.setOutputRange(-0.75, 0.75);
        
        turnPID = new PIDController(turnKp, turnKi, turnKd, gyroscope, dummyPID);
      
        turnPID.setContinuous(true);      
        turnPID.setAbsoluteTolerance(0.2);
        drivePID_FL.setAbsoluteTolerance(0.2);
        drivePID_FR.setAbsoluteTolerance(0.2);
        drivePID_RL.setAbsoluteTolerance(0.2);
        drivePID_RR.setAbsoluteTolerance(0.2);
      
    }
	
    public int getEncoder(int number)
    {
      switch (number)
      {
        case 1:
            return encoderOne.get();
        case 2:
           return encoderTwo.get();
        case 3:
           return encoderThree.get();
        case 4:
           return encoderFour.get();
        default:
           return encoderOne.get();
      }
    }
  
    public void initDefaultCommand() 
    {
        setDefaultCommand(new JoystickDrive());
    }
  
    public PIDController getDrivePID(int number)
    {
        switch (number)
      {
        case 1:
            return drivePID_FR;
        case 2:
           return drivePID_FL;
        case 3:
           return drivePID_RR;
        case 4:
           return drivePID_RL;
        default:
           return drivePID_FR;
      }
    }
  
    public void driveSetpoint (double setpoint)
    {
      drivePID_FL.setSetpoint(setpoint);
      drivePID_FR.setSetpoint(setpoint);
      drivePID_RL.setSetpoint(setpoint);
      drivePID_RR.setSetpoint(setpoint);
    }
  
    public void driveEnable()
    {
      drivePID_FL.enable();
      drivePID_FR.enable();
      drivePID_RL.enable();
      drivePID_RR.enable();
    }
  
    public PIDController getTurnPID()
    {
        return turnPID;
    }
  
    public void drivePID()
    {
        double driveOutput;
        double turnOutput;
        
        driveOutput = drivePID_FL.get();
        turnOutput = turnPID.get();
        SmartDashboard.putNumber("Encoder PID Out", driveOutput);
        SmartDashboard.putNumber("Gyro PID Out", turnOutput);
        drive.mecanumDrive_Cartesian(0.0, driveOutput, turnOutput, 0.0);
    }
  
    public void strafePID()
    {
        double driveOutput;
        double turnOutput;
        
        driveOutput = drivePID_FL.get();
        turnOutput = turnPID.get();
        SmartDashboard.putNumber("Encoder PID Out", driveOutput);
        SmartDashboard.putNumber("Gyro PID Out", turnOutput);
        SmartDashboard.putNumber("Gyro Angle Out", gyroscope.getAngle());
        SmartDashboard.putNumber("Encoder 1", Robot.drivetrain.getEncoder(1));
        //drive.mecanumDrive_Cartesian(driveOutput, 0.0, turnOutput, 0.0);
        //this.runVictor(driveOutput);
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
    
    public void ExtendedTankDrive(double Left, double Right, double LeftStrafe, double RightStrafe, boolean overdrive)
    {
       SmartDashboard.putNumber("Drive Encoder", encoderOne.get());
       if((LeftStrafe < 0.1 && RightStrafe < 0.1) && !overdrive)
       {
          drive.tankDrive(-Left*0.75, Right*0.75);
       }
       else if (LeftStrafe < 0.1 && RightStrafe < 0.1)
       {
    	   drive.tankDrive(-Left, Right);
       }
       else
       {
          if(RightStrafe > 0)
          {
            drive.mecanumDrive_Cartesian(RightStrafe, 0, 0, 0);
          }
          else
          {
            drive.mecanumDrive_Cartesian(-LeftStrafe, 0, 0, 0);
          }
       }
     }
    
    public void arcadeDrive(double leftY, double rightX, double leftX, boolean overdrive)
    {
       if(!overdrive)    
       {
    	 drive.mecanumDrive_Cartesian(leftX*.75, leftY*.75, rightX*.75, 0);
       }
       else
       {
    	 drive.mecanumDrive_Cartesian(leftX, leftY, rightX, 0);
       }
    }
    
    public void joystickDrive(double JoyX, double JoyY, double Twist, double Throttle)
    {
    	if(Throttle < 0)
    	{
    		drive.mecanumDrive_Cartesian(JoyX, JoyY, Twist, 0);
    	}
    	else 
    	{
    		drive.mecanumDrive_Cartesian(JoyX*.75, JoyY*.75, Twist*.75, 0);
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
      encoderOne.reset();
      encoderTwo.reset();
      encoderThree.reset();
      encoderFour.reset();
    }
    
}
    

