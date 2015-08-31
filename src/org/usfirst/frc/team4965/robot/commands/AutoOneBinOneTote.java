package org.usfirst.frc.team4965.robot.commands;

import org.usfirst.frc.team4965.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoOneBinOneTote extends CommandGroup {
    
    public  AutoOneBinOneTote() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	requires(Robot.drivetrain);
    	requires(Robot.intake);
    	requires(Robot.lift);
    	
    	addSequential(new ArmLift(true, 1.5));
    	addSequential(new CloseIntake(true, 1.5));
    	addSequential(new ArmLift(true, 1.9));
    	addSequential(new StrafeForDistance(460, false));
    	addSequential(new ArmLower(true, 0.5));
    	addSequential(new OpenIntake(true, 1.8));
    	addSequential(new DriveForDistance(-250));
    	addSequential(new ArmLower(false, 0.0));
    	addSequential(new DriveForDistance(350));
    	addSequential(new CloseIntake(true, 1.3));
    	addSequential(new DriveForDistance(-900));
    	
    }
}
