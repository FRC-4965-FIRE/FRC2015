package org.usfirst.frc.team4965.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4965.robot.Robot;

/**
 *
 */
public class OpenIntake extends Command {

    public OpenIntake() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
      Robot.Intake.Open();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
      return isOpen();
    }

    // Called once after isFinished returns true
    protected void end() {
      Robot.Intake.stopOpen();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
      Robot.Intake.stopOpen();
    }
}