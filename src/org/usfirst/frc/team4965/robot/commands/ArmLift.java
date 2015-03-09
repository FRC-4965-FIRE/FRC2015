package org.usfirst.frc.team4965.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4965.robot.Robot;

/**
 *
 */
public class ArmLift extends Command {

    public ArmLift() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
      Robot.lift.Up(0.75);
      
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.lift.isUp();
    }

    // Called once after isFinished returns true
    protected void end() {
      Robot.lift.stopLift();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
      Robot.lift.stopLift();
    }
}
