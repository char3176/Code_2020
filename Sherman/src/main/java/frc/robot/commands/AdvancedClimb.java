package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;
import frc.robot.constants.ClimberConstants;

public class AdvancedClimb extends CommandBase {
  private Climber m_Climber = Climber.getInstance();
  private DoubleSupplier supplier;

  private boolean currentTop;
  private boolean lastTop;
  private boolean currentBottom;
  private boolean lastBottom;

  public AdvancedClimb(DoubleSupplier supplier) {
    this.supplier = supplier;
    addRequirements(m_Climber);
  }

  @Override
  public void initialize() {
    currentTop = m_Climber.getTopLimit();
    currentBottom = m_Climber.getBottomLimit();
  }

  @Override
  public void execute() {
    lastTop = currentTop;
    currentTop = m_Climber.getTopLimit();
    lastBottom = currentBottom;
    currentBottom = m_Climber.getBottomLimit();

    if(currentBottom) {
      m_Climber.resetEncoder();
    }
    /*
    if ((!m_Climber.getTopLimit() && supplier.getAsDouble() < 0) || (!m_Climber.getBottomLimit() && supplier.getAsDouble() > 0)) {
      if (m_Climber.getPostition() < ClimberConstants.CLIMB_HEIGHT * .9) {
        m_Climber.setPercentControl(supplier.getAsDouble());
      } else if (m_Climber.getPostition() < ClimberConstants.CLIMB_HEIGHT * .9) {
        m_Climber.setPercentControl(supplier.getAsDouble() * 0.1);
      }
    } else {
      m_Climber.setPercentControl(0);
    }
    */
  }

  @Override
  public void end(boolean interrupted) {
    m_Climber.setPercentControl(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
