
package org.usfirst.frc.team4965.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.Talon;

/**
 *
 */
public class Lift extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public static Lift instance;
  
   public static Lift getInstance()
   {
        if(instance == null)
            instance = new Lift();
    
        return instance;
   }

    private Lift()
    {
      //two talons? Encoders?
      
    }
  
   public void Up()
   {
     
   } 
  
   public void Down() 
   {
    
   }
  
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
