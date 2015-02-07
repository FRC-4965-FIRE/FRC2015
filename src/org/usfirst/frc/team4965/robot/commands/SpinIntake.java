package org.usfirst.frc.team4965.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4965.robot.Robot;

/**
 *
 */
public class SpinIntake extends Command {

    public SpinIntake() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
      Robot.Intake.spintake();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
      return false;
    }

    // Called once after isFinished returns true
    protected void end() {
      Robot.Intake.stopSpin();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
      Robot.Intake.stopSpin();
    }
}