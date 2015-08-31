package org.usfirst.frc.team4965.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4965.robot.Robot;

/**
 *
 */
public class OpenIntake extends Command {

	private Timer intakeTimer;
	private boolean partial;
	private double time;
	
    public OpenIntake(boolean partial, double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intake);
    	
    	this.partial = partial;
    	this.time = time;
    	intakeTimer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	intakeTimer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
       Robot.intake.Open(0.80);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (partial)
    	{
    		return (intakeTimer.get() >= time);
    	}
    	else
    	{
            return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.stop();
    	intakeTimer.stop();
    	intakeTimer.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intake.stop();
    	intakeTimer.stop();
    	intakeTimer.reset();
    }
}