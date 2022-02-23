// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//DEFAULT IMPORTS! DO NOT REMOVE!
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//Basic imports, needed for essential robot funtions
//*note*, this does NOT include drive encoders (import spark and other drive encoders seperately)
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.AnalogInput;

//Movement Encoder Imports / motor controllers
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    //motor1.setInverted(true);

    /*
     * m_myRobot = new DifferentialDrive(m_leftMotor, m_rightMotor);
     * m_leftStick = new Joystick(0);
     * m_rightStick = new Joystick(1);
     */
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and
   * test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different
   * autonomous modes using the dashboard. The sendable chooser code works with
   * the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the
   * chooser code and
   * uncomment the getString line to get the auto name from the text box below the
   * Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional comparisons to the
   * switch structure
   * below with additional strings. If using the SendableChooser make sure to add
   * them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {
  }

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {
  }

  /*
   * Defines Drive Terms, potentially needed.
   * private DifferentialDrive m_myRobot;
   * private Joystick m_leftStick;
   * private Joystick m_rightStick;
   */

  /* Motors */
  private final MotorController motor0 = new PWMVictorSPX(0);
  private final MotorController motor1 = new PWMVictorSPX(1);
  private final MotorController motor2 = new PWMVictorSPX(2);
  private final MotorController motor3 = new PWMVictorSPX(3);
  private final MotorController motor4 = new PWMVictorSPX(4);

  /* Config Factory Defaults */
  //motor0.configFactoryDefault();

  /* Joysticks */
  private static XboxController ctrl0 = new XboxController(0);

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {

    // Reverse Button
    int reverse;
    if (ctrl0.ButtonA()) {
      reverse = -1;
    } else {
      reverse = 1;
    }

    // Outter Climb
    motor0.set(reverse * ctrl0.LeftTrigger());
    motor1.set(reverse * ctrl0.LeftTrigger());

    // Arm Extension
    motor2.set(reverse * ctrl0.RightTrigger());
    motor3.set(reverse * ctrl0.RightTrigger());

    // Arm Rotate
    motor4.set(reverse * ctrl0.RightStickY());

  }
}