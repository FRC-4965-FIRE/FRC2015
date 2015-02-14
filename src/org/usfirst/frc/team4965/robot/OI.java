package org.usfirst.frc.team4965.robot;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

//import org.usfirst.frc.team4965.robot.commands.ExampleCommand;
import org.usfirst.frc.team4965.robot.commands.*;
import edu.wpi.first.wpilibj.Joystick;
//test difference
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	 Joystick controller = new Joystick(0);
   // Joystick controllerTwo = new Joystick(1);
   
   Button btnOne = new JoystickButton(controller, 1);
   Button btnTwo = new JoystickButton(controller, 2);
   Button btnThree = new JoystickButton(controller, 3);
   Button btnFour = new JoystickButton(controller, 4);
  
   public OI()
   {
     btnOne.whileHeld(new ArmLift());
     btnTwo.whileHeld(new OpenIntake());
     btnThree.whileHeld(new ArmLower());
     btnFour.whileHeld(new CloseIntake());
   }
    
    public double leftStickY()
    {
        if (controller.getY() < 0.2 && controller.getY() > -0.2)
        {
            return 0.0;
        }
        return controller.getY();
    }
    public double rightStickY()
    {
        if (controller.getRawAxis(5) < 0.2 && controller.getRawAxis(5) > -0.2)
        {
            return 0.0;
        }
        return controller.getRawAxis(5);
    }
    
    public double leftStickX()
    {
        if (controller.getX() < 0.2 && controller.getX() > -0.2)
        {
            return 0.0;
        }
        return controller.getX();
    }
    
    public double RightTrigger()
    {
        if (controller.getRawAxis(3) < 0.3 && controller.getRawAxis(3) > -0.3)
        {
            return 0.0;
        }
        
        return controller.getRawAxis(3);
    }
  
    public double LeftTrigger()
    {
        if (controller.getRawAxis(2) < 0.3 && controller.getRawAxis(2) > -0.3)
        {
            return 0.0;
        }
        
        return controller.getRawAxis(2);
    }
}

