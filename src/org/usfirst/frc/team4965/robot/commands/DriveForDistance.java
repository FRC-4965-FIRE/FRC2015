package org.usfirst.frc.team4965.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4965.robot.*;

/**
 *
 */
public class DriveForDistance extends Command {
    public double setpoint;
    
  //need four separate gains to account for uneven weight distribution
    private static final double frontRightKp = 0.0012;
    private static final double frontRightKi = 0.000009;
    private static final double frontRightKd = 0.00005;
    
    private static final double rearRightKp = 0.0013;
    private static final double rearRightKi = 0.000008;
    private static final double rearRightKd = 0.00005;
    
    private static final double frontLeftKp = 0.0012;
    private static final double frontLeftKi = 0.000009;
    private static final double frontLeftKd = 0.00005;
    
    private static final double rearLeftKp = 0.0013;
    private static final double rearLeftKi = 0.000008;
    private static final double rearLeftKd = 0.00005;
    
    public DriveForDistance(double setpoint) {
        requires(Robot.drivetrain);
        this.setpoint = setpoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
      Robot.drivetrain.resetEncoders();
      Robot.drivetrain.driveReset();
      
      //Robot.drivetrain.getDrivePID(1).setPID(SmartDashboard.getNumber("Front Right Kp", 0.001)/1000,
        //                                     SmartDashboard.getNumber("Front Right Ki", 0.00001)/1000,
        //                                     SmartDashboard.getNumber("Front Right Kd", 0.00006)/1000);
      //Robot.drivetrain.getDrivePID(2).setPID(SmartDashboard.getNumber("Front Left Kp", 0.001)/1000,
        //                                     SmartDashboard.getNumber("Front Left Ki", 0.00001)/1000,
        //                                     SmartDashboard.getNumber("Front Left Kd", 0.00006)/1000);
      //Robot.drivetrain.getDrivePID(3).setPID(SmartDashboard.getNumber("Rear Right Kp", 0.001)/1000,
        //                                     SmartDashboard.getNumber("Rear Right Ki", 0.00001)/1000,
        ///                                     SmartDashboard.getNumber("Rear Right Kd", 0.00006)/1000);
      //Robot.drivetrain.getDrivePID(4).setPID(SmartDashboard.getNumber("Rear Left Kp", 0.001)/1000,
                                             //SmartDashboard.getNumber("Rear Left Ki", 0.00001)/1000,
                                             //SmartDashboard.getNumber("Rear Left Kd", 0.00006)/1000);
      
      Robot.drivetrain.getDrivePID(1).setPID(frontRightKp, frontRightKi, frontRightKd);
      Robot.drivetrain.getDrivePID(2).setPID(frontLeftKp, frontLeftKi, frontLeftKd);
      Robot.drivetrain.getDrivePID(3).setPID(rearRightKp, rearRightKi, rearRightKd);
      Robot.drivetrain.getDrivePID(4).setPID(rearLeftKp, rearLeftKi, rearLeftKd);
      
      //Robot.drivetrain.getDrivePID(1).enable();
      Robot.drivetrain.driveSetpoint(setpoint);
      Robot.drivetrain.driveEnable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Drive Encoder FR", Robot.drivetrain.getEncoder(1));
    	SmartDashboard.putNumber("Drive Encoder FL", Robot.drivetrain.getEncoder(2));
    	SmartDashboard.putNumber("Drive Encoder RR", Robot.drivetrain.getEncoder(3));
    	SmartDashboard.putNumber("Drive Encoder RL", Robot.drivetrain.getEncoder(4));
    	SmartDashboard.putNumber("Encoder PID Out", Robot.drivetrain.getDrivePID(1).get());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drivetrain.getDrivePID(3).onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
      Robot.drivetrain.mecanumDrive(0,0,0,0);
      Robot.drivetrain.driveReset();
      SmartDashboard.putNumber("Encoder PID Out", Robot.drivetrain.getDrivePID(1).get());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
      Robot.drivetrain.mecanumDrive(0,0,0,0);
      Robot.drivetrain.driveReset();
    }
}
