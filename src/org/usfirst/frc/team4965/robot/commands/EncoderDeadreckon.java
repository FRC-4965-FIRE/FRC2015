package org.usfirst.frc.team4965.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4965.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.*;

/**
 *
 */
public class EncoderDeadreckon extends Command {

	double setpoint;
    public EncoderDeadreckon(double setpoint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	this.setpoint = setpoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.mecanumDrive(0, .75, 0, 0);
    	SmartDashboard.putNumber("Encoder Value", Robot.drivetrain.getEncoder());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(Robot.drivetrain.getEncoder() > setpoint)
        {
        	return true;
        }
        
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
