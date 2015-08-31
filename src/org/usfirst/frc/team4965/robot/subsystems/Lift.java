
package org.usfirst.frc.team4965.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    Victor armLift;
   
  
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
      
      armLift = new Victor(RobotMap.ArmVictor);
    }
  
   public void Up(double speed)
   {
       armLift.set(-speed);
   } 
  
   public void Down(double speed) 
   {
      armLift.set(speed);
   }
  
   public void stopLift()
   {
      armLift.set(0.0); 
   }
  
   public boolean isUp()
   {
	   SmartDashboard.putBoolean("up switch", limitUp.get());
       return limitUp.get();  
   }
  
   public boolean isDown()
   {
	 SmartDashboard.putBoolean("down switch", limitDown.get());
     return limitDown.get();
   }
  
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
