
package org.usfirst.frc.team4965.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.*;
import org.usfirst.frc.team4965.robot.Robot;


/**
 *
 */
public class SpinVictor extends Command {

    public SpinVictor() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
      SmartDashboard.putNumber("Encoder 1", Robot.drivetrain.getEncoder());
      SmartDashboard.putBoolean("Limit Switch", Robot.drivetrain.limitSet());
      Robot.drivetrain.runVictor(0.8);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
      return Robot.drivetrain.limitSet(); 
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stopVictor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.stopVictor();
    }
}
