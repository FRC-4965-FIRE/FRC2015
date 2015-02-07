package org.usfirst.frc.team4965.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4965.robot.Robot;

/**
 *
 */
public class ArmLift extends Command {

    public ExampleCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.Lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
      lift.Down(0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return lift.isDown();
    }

    // Called once after isFinished returns true
    protected void end() {
      lift.liftStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
      lift.liftStop();
    }
}
