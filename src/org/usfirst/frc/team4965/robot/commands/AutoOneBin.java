package org.usfirst.frc.team4965.robot.commands;

import org.usfirst.frc.team4965.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoOneBin extends CommandGroup {
    
    public  AutoOneBin() {
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
    	
    	addSequential(new ArmLift(true, 1.7));
    	addSequential(new CloseIntake(true, 3));
    	addSequential(new ArmLift(true, .5));
    	addSequential(new DriveForDistance(-1050));
    	addSequential(new ArmLower(true, .5));
    }
}
