package org.usfirst.frc.team4965.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
      Robot.drivetrain.getDrivePID(1).setSetpoint(setpoint);
      
      Robot.drivetrain.getDrivePID(1).enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Drive Encoder", Robot.drivetrain.getEncoder(1));
    	SmartDashboard.putNumber("Encoder PID Out", Robot.drivetrain.getDrivePID(1).get());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drivetrain.getDrivePID(1).onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
      Robot.drivetrain.mecanumDrive(0,0,0,0);
      Robot.drivetrain.getDrivePID(1).disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
      Robot.drivetrain.mecanumDrive(0,0,0,0);
    }
}
