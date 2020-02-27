/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command turretCommand;

  private RobotContainer robotContainer;

  private VisionClient m_VisionClient = VisionClient.getInstance();
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    robotContainer = new RobotContainer();
    putDashboardInit();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    putDashboard();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    // m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // // schedule the autonomous command (example)
    // if (m_autonomousCommand != null) {
    //   m_autonomousCommand.schedule();
    // }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    turretCommand = robotContainer.getTurretCommand();
    if(turretCommand != null) {
      //turretCommand.schedule();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  public void putDashboardInit() {
    SmartDashboard.putNumber("Timestamp", m_VisionClient.getTimestamp()); 
    SmartDashboard.putBoolean("isBallTargetAvail", m_VisionClient.isBallTargetAvail());
    SmartDashboard.putNumber("Ball Distance", m_VisionClient.getBallDistance());
    SmartDashboard.putNumber("Ball Angle", m_VisionClient.getBallAngle());
    SmartDashboard.putNumber("Distance P", 0);
    SmartDashboard.putNumber("Angle P", 0);
    SmartDashboard.putNumber("Angle I", 0);
    SmartDashboard.putNumber("Angle D", 0);

    //Turret
    SmartDashboard.putNumber("kP", 0.0);
    SmartDashboard.putNumber("kI", 0.0);
    SmartDashboard.putNumber("kD", 0.0);
  }

  public void putDashboard() {
    SmartDashboard.putNumber("Timestamp", m_VisionClient.getTimestamp());
    SmartDashboard.putBoolean("isBallTargetAvail", m_VisionClient.isBallTargetAvail());
    SmartDashboard.putNumber("Ball Distance", m_VisionClient.getBallDistance());
    SmartDashboard.putNumber("Ball Angle", m_VisionClient.getBallAngle());
    Command command = robotContainer.getTurretCommand();
    String commandText = "None";
    if(command != null) {
      commandText = command.getName();
    }
    SmartDashboard.putString("___Selected Command", commandText);
  }
}