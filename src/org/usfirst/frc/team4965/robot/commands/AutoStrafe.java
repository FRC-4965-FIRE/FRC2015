
package org.usfirst.frc.team4965.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4965.robot.Robot;

/**
 *
 */
public class AutoStrafe extends Command {

    private double distance;
    private double angle;
  
    public AutoStrafe(double distance, double angle) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
      
        this.distance = distance;
        this.angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
      drivetrain.getDrivePID().setSetpoint(distance);
      drivetrain.getTurnPID().setSetpoint(angle);
      
      drivetrain.getDrivePID().enable();
      drivetrain.getTurnPID().enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
      drivetrain.strafePID();
      drivetrain.runVictor();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (drivetrain.getDrivePID().onTarget() && drivetrain.getTurnPID().onTarget());
    }

    // Called once after isFinished returns true
    protected void end() {
      drivetrain.mecanumDrive(0, 0, 0, 0);
      drivetrain.getDrivePID().disable();
      drivetrain.getTurnPID().disable();
      driveTrain.stopVictor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
      this.end();
    }
}