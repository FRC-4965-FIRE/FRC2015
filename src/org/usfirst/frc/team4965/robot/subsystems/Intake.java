
package org.usfirst.frc.team4965.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
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
    
   
  
    public static Intake getInstance() 
     {
        if(instance == null)
            instance = new Intake();
      
        return instance;
     }
  
     private Intake()
     {
        leftWheel = new Talon(RobotMap.leftTalon);
        rightWheel = new Talon(RobotMap.rightTalon);
        openArm = new Talon(RobotMap.centerTalon);
        limitOpen = new DigitalInput(RobotMap.LimitThree);
     }
  
   public void Open(double speed)
   {
       openArm.set(speed);
   } 
    
  public void stopOpen(){

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
  
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
