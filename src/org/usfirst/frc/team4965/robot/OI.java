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
	 public Joystick controller = new Joystick(0);
     public Joystick controllerTwo = new Joystick(1);
   
   Button btnOne = new JoystickButton(controller, 1);
   Button btnTwo = new JoystickButton(controller, 2);
   Button btnThree = new JoystickButton(controller, 3);
   Button btnFour = new JoystickButton(controller, 4);
   Button btnFive = new JoystickButton(controller, 5);
   Button btnSix = new JoystickButton(controller, 6);
   
   Button joy2BtnOne = new JoystickButton(controllerTwo, 1);
   Button joy2BtnTwo = new JoystickButton(controllerTwo, 2);
   Button joy2BtnThree = new JoystickButton(controllerTwo, 3);
   Button joy2BtnFour = new JoystickButton(controllerTwo, 4);
   Button joy2BtnFive = new JoystickButton(controllerTwo, 5);
   Button joy2BtnSix = new JoystickButton(controllerTwo, 6);
   
  
   public OI()
   {
	   btnOne.whenPressed(new SwitchDrive());
	   joy2BtnOne.whileHeld(new ArmLower());
	   joy2BtnTwo.whileHeld(new OpenIntake());
	   joy2BtnFour.whileHeld(new ArmLift());
	   joy2BtnThree.whileHeld(new CloseIntake());
	   joy2BtnFive.whileHeld(new SpinIntake());
	   joy2BtnSix.whileHeld(new ReverseIntake());
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
    
    public double rightStickX()
    {
        if (controller.getRawAxis(4) < 0.2 && controller.getRawAxis(4) > -0.2)
        {
            return 0.0;
        }
        return controller.getRawAxis(4);    	
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
    
    public boolean isOverdrive()
    {
    	return (controller.getRawButton(5) && controller.getRawButton(6));
    }
}

