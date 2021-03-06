
package org.usfirst.frc.team4965.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team4965.robot.Robot;

/**
 *
 */
public class DriveForTime extends Command {

    Timer driveTime;
    double time; 
  
    public DriveForTime(double time) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
      
        driveTime = new Timer();
        this.time = time; 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        driveTime.reset();
        driveTime.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.drivetrain.mecanumDrive(0, -0.5, 0, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(driveTime.get() >= time)
        {
           return true; 
        }
      
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.drivetrain.mecanumDrive(0, 0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        Robot.drivetrain.mecanumDrive(0, 0, 0, 0);
    }
}
