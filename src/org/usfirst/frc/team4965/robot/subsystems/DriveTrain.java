package org.usfirst.frc.team4965.robot.subsystems;

import org.usfirst.frc.team4965.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;

import org.usfirst.frc.team4965.robot.commands.JoystickDrive;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
	public static DriveTrain instance;
	RobotDrive drive;
  Encoder enc; 
  Victor krum;
	
	public static boolean ReverseDrive = false;
	
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
        drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
      
        enc = new Encoder(RobotMap.EncoderOne_A, RobotMap.EncoderOne_B);
      
        krum = new Victor(RobotMap.TestVictor);
    }
	
    public int getEncoder()
    {
      return enc.get();
    }
  
    public void runVictor()
    {
      krum.set(.5);
    }
  
    public void stopVictor()
    {
      krum.set(0);
    }

    public void initDefaultCommand() 
    {
        setDefaultCommand(new JoystickDrive());
    }
    
    public void drive(double LeftSpeed, double RightSpeed)
    {
      if(ReverseDrive = true)
        drive.tankDrive(-LeftSpeed, -RightSpeed);
      else
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
    
    public void ExtendedTankDrive(double Left, double Right, double Strafe)
    {
      if(ReverseDrive == false)
      {
          if(Strafe < 0.1 && Strafe > -0.1)
            drive.tankDrive(Right, -Left);
        else
            drive.mecanumDrive_Cartesian(-Strafe, 0, 0, 0);
      }
      
      else
      {
        if(Strafe < 0.1 && Strafe > -0.1)
            drive.tankDrive(Left, -Right);
        else
            drive.mecanumDrive_Cartesian(Strafe, 0, 0, 0);
      }
     }
    }
    

