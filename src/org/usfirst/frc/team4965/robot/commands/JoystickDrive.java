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
        SmartDashboard.putNumber("Drive Encoder FR", Robot.drivetrain.getEncoder(1));
    	SmartDashboard.putNumber("Drive Encoder FL", Robot.drivetrain.getEncoder(2));
    	SmartDashboard.putNumber("Drive Encoder RR", Robot.drivetrain.getEncoder(3));
    	SmartDashboard.putNumber("Drive Encoder RL", Robot.drivetrain.getEncoder(4));
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
        Robot.drivetrain.joystickDrive(Robot.oi.leftStickX(), Robot.oi.leftStickY(), Robot.oi.rightStickX(), 1.0);
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
