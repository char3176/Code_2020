/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.constants.TurretConstants;
import frc.robot.subsystems.ShooterByJake;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShootTurret extends CommandBase {

  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private ShooterByJake m_ShooterByJake = ShooterByJake.getInstance();

  public ShootTurret() {
    addRequirements(m_ShooterByJake);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
    m_ShooterByJake.setBoosterOutput(0.0);
    m_ShooterByJake.setFlywheelOutput(0.0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    m_ShooterByJake.setBoosterOutput(TurretConstants.TURRET_MAX_BOOSTER_OUTPUT);
    m_ShooterByJake.setFlywheelOutput(TurretConstants.TURRET_MAX_FLYWHEEL_OUTPUT);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
  }
}
