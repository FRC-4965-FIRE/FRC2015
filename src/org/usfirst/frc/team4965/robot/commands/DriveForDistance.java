package org.usfirst.frc.team4965.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4965.robot.*;

/**
 *
 */
public class DriveForDistance extends Command {
    public double setpoint;
    
    public DriveForDistance(double setpoint) {
        requires(Robot.drivetrain);
        this.setpoint = setpoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
      Robot.drivetrain.getDrivePID().setSetpoint(setpoint);
      
      Robot.drivetrain.getDrivePID().enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
      Robot.drivetrain.drivePID();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
      Robot.drivetrain.mecanumDrive(0,0,0,0);
      Robot.drivetrain.getDrivePID().disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
