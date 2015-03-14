
package org.usfirst.frc.team4965.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team4965.robot.*;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 */
public class Intake extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public static Intake instance;
    Talon leftWheel;
    Talon rightWheel;
    Talon openArm;
    DigitalInput limitOpen;
    DigitalInput limitClose;
   
  
    public static Intake getInstance() 
     {
        if(instance == null)
            instance = new Intake();
      
        return instance;
     }
  
     private Intake()
     {
        leftWheel = new Talon(RobotMap.LeftTalon);
        rightWheel = new Talon(RobotMap.RightTalon);
        openArm = new Talon(RobotMap.CenterTalon);
        //limitOpen = new DigitalInput(RobotMap.LimitThree);
        //limitClose = new DigitalInput(RobotMap.LimitFour);
     }
  
   public void Open(double speed)
   {
       openArm.set(-speed);
   } 
   
   public void Close(double speed)
   {
	   openArm.set(speed);
   }
    
  public void stop()
  {
       openArm.set(0.0);
  }
  
  public void spintake(double speed)
  {
    leftWheel.set(speed);
    rightWheel.set(speed);
  }
  
  public void stopSpin()
  {
    leftWheel.set(0.0);
    rightWheel.set(0.0);
  }
  public boolean isOpen()
  {
    return limitOpen.get();
  }    
  
  public boolean isClosed()
  {
	return limitClose.get();
  }
  
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
