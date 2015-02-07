
package org.usfirst.frc.team4965.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.DigitalInput;
import org.usfirst.frc.team4965.robot.*;
/**
 *
 */
public class Lift extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public static Lift instance;
    DigitalInput limitUp;
    DigitalInput limitDown;
    Victor armLeft;
    Victor armRight;
   
  
   public static Lift getInstance()
   {
        if(instance == null)
            instance = new Lift();
    
        return instance;
   }

    private Lift()
    {
      limitUp = new DigitalInput(RobotMap.LimitOne);
      limitDown = new DigitalInput(RobotMap.LimitTwo);
      
      armLeft = new Victor(RobotMap.LeftVictor);
      armRight = new Victor(RobotMap.RightVictor);
    }
  
   public void Up(double speed)
   {
       armLeft.set(speed);
       armRight.set(-speed);
   } 
  
   public void Down(double speed) 
   {
      armLeft.set(-speed);
      armRight.set(speed);
   }
  
   public void stopLift()
   {
      armLeft.set(0.0);
      armRight.set(0.0);  
   }
  
   public boolean isUp()
   {
       return limitUp.get();  
   }
  
   public boolean isDown()
   {
     return limitDown.get();
   }
  
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
