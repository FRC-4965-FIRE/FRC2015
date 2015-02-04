
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
      Robot.drivetrain.resetEncoder();
      Robot.drivetrain.getDrivePID().setSetpoint(distance);
      Robot.drivetrain.getTurnPID().setSetpoint(angle);
      
      Robot.drivetrain.getDrivePID().enable();
      Robot.drivetrain.getTurnPID().enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
      Robot.drivetrain.strafePID();
      //Robot.drivetrain.runVictor();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.drivetrain.getDrivePID().onTarget() && Robot.drivetrain.getTurnPID().onTarget());
    }

    // Called once after isFinished returns true
    protected void end() {
      Robot.drivetrain.mecanumDrive(0, 0, 0, 0);
      Robot.drivetrain.getDrivePID().disable();
      Robot.drivetrain.getTurnPID().disable();
      Robot.drivetrain.stopVictor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
      this.end();
    }
}