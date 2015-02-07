package org.usfirst.frc.team4965.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
 // PWM ports
	  public static final int PWM_ZERO  = 0;
    public static final int PWM_ONE   = 1;
    public static final int PWM_TWO   = 2;
    public static final int PWM_THREE = 3;
    public static final int PWM_FOUR  = 4;
    
    public static final int DIO_ZERO  = 0;
    public static final int DIO_ONE   = 1;
    public static final int DIO_TWO   = 2;
    public static final int DIO_THREE = 3;
    public static final int DIO_FOUR  = 4;
    public static final int DIO_FIVE  = 5;
    public static final int DIO_SIX   = 6;
    
    public static final int ANLG_ZERO  = 0;
    public static final int ANLG_ONE   = 1;
    public static final int ANLG_TWO   = 2;
    public static final int ANLG_THREE = 3;
    public static final int ANLG_FOUR  = 4;
    public static final int ANLG_FIVE  = 5;
    public static final int ANLG_SIX   = 6;
      
  //encoder ports
    public static final int EncoderOne_A = DIO_ZERO;
    public static final int EncoderOne_B = DIO_ONE;
  
  //limit switches
  public static final int LimitOne = DIO_TWO;

//motor ports
    public static final int LeftFront = PWM_ZERO;//J4
    public static final int RightFront = PWM_ONE;//J3
    public static final int LeftBack = PWM_TWO;//J2
    public static final int RightBack = PWM_THREE;
    public static final int TestVictor = PWM_FOUR;

 //Gyro
    public static final int Gyro = ANLG_ZERO;

   
}
