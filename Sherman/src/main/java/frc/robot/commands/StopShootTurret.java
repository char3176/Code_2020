
package frc.robot.commands;

import frc.robot.subsystems.ShooterByJake;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class StopShootTurret extends CommandBase {

  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final ShooterByJake m_ShooterByJake = ShooterByJake.getInstance();

  private boolean stop = false;

  public StopShootTurret() { 
    addRequirements(m_ShooterByJake);
    stop = true;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    m_ShooterByJake.setBoosterOutput(0.0);
    m_ShooterByJake.setFlywheelOutput(0.0);
  }

  @Override
  public boolean isFinished() {
    if(stop) {
        return true;
    }
    return false;
  }
}
