
package org.usfirst.frc.team4965.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team4965.robot.*;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 */
public class Intake extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public static Intake instance;
    Victor intake;
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
        intake = new Victor(RobotMap.IntakeVictor);
        //limitOpen = new DigitalInput(RobotMap.LimitThree);
        //limitClose = new DigitalInput(RobotMap.LimitFour);
     }
  
   public void Open(double speed)
   {
       intake.set(speed);
   } 
   
   public void Close(double speed)
   {
	   intake.set(-speed);
   }
    
  public void stop()
  {
       intake.set(0.0);
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
