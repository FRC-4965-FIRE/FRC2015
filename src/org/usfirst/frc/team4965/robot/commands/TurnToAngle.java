package org.usfirst.frc.team4965.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4965.robot.Robot;
/**
 *
 * @author Developer
 */
public class TurnToAngle extends Command {
    
    double setpoint;
    
    public TurnToAngle(double setpoint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.drivetrain);
        
        this.setpoint = setpoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.drivetrain.getTurnPID().setAbsoluteTolerance(2.0);
        Robot.drivetrain.getTurnPID().setSetpoint(setpoint);
        Robot.drivetrain.getTurnPID().enable();
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.drivetrain.turnPID();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(Robot.drivetrain.getTurnPID().onTarget())
            return true;
        
        
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.drivetrain.mecanumDrive(0, 0, 0, 0);
        Robot.drivetrain.getTurnPID().disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        Robot.drivetrain.mecanumDrive(0, 0, 0, 0);
        Robot.drivetrain.getTurnPID().disable();
    }
}