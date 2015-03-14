package org.usfirst.frc.team4965.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4965.robot.Robot;
/**
 *
 */
public class JoystickDrive extends Command {
	
    public JoystickDrive() {
    	requires(Robot.drivetrain);
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        SmartDashboard.putBoolean("switch drive", Robot.drivetrain.switchDrive);
    	/*if(Robot.drivetrain.switchDrive)
    	{
    	   Robot.drivetrain.arcadeDrive(Robot.oi.leftStickY(), Robot.oi.rightStickX(), Robot.oi.leftStickX(), Robot.oi.isOverdrive());
    	}
    	else
    	{
        	Robot.drivetrain.ExtendedTankDrive(Robot.oi.leftStickY(), Robot.oi.rightStickY(), 
                    Robot.oi.LeftTrigger(), Robot.oi.RightTrigger(),
                    Robot.oi.isOverdrive());

    	}*/
        Robot.drivetrain.joystickDrive(Robot.oi.leftStickX(), Robot.oi.leftStickY(), Robot.oi.RightTrigger(), Robot.oi.LeftTrigger());
    } 
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
