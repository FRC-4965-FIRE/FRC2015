
package org.usfirst.frc.team4965.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public static Intake instance;
	
    public static Intake getInstance() 
     {
        if(instance == null)
            instance = new Intake();
      
        return instance;
     }
  
     private Intake()
     {
       //should be using Victors for these, I think
       
     }
  
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
