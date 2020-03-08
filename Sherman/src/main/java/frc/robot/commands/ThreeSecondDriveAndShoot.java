package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Conveyor;
import frc.robot.constants.ConveyorConstants;
import frc.robot.constants.ShooterConstants;

public class ThreeSecondDriveAndShoot extends CommandBase {
  private Drivetrain m_Drivetrain = Drivetrain.getInstance();
  private Double startTime = 0.0;
  private Shooter m_Shooter = Shooter.getInstance();
  private Conveyor m_Conveyor = Conveyor.getInstance();

  public ThreeSecondDriveAndShoot() {
    addRequirements(m_Drivetrain, m_Shooter, m_Conveyor);
  }

  @Override
  public void initialize() {
    startTime = Timer.getFPGATimestamp();
    m_Shooter.setBoosterPercentControl(0);
    m_Shooter.setFlywheelPercentControl(0);
  }  

  @Override
  public void execute() {
    //if under 3 seconds
    if(Timer.getFPGATimestamp() < startTime + 3.0) {
        m_Drivetrain.drive(new ChassisSpeeds(-0.5, 0.0, 0.0));
    } else if(Timer.getFPGATimestamp() < startTime + 3.0 + ShooterConstants.FLYWHEEL_SPINUP_TIME) {
        m_Shooter.setBoosterPercentControl(1);
        m_Shooter.setFlywheelPercentControl(0.8);
    } else if(Timer.getFPGATimestamp() < startTime + 3.0 + ShooterConstants.FLYWHEEL_SPINUP_TIME + ConveyorConstants.SHOOT_TIME) {
        m_Conveyor.setPercentControl(.3);
    } 
  }

  @Override
  public void end(boolean interrupted) {
    m_Drivetrain.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
    m_Shooter.setBoosterPercentControl(0.0);
    m_Shooter.setFlywheelPercentControl(0.0);
    m_Conveyor.setPercentControl(0.0);
  }

  @Override
  public boolean isFinished() {
    if(Timer.getFPGATimestamp() < startTime + 3.0 + ShooterConstants.FLYWHEEL_SPINUP_TIME + ConveyorConstants.SHOOT_TIME + 5) {
      return true;
    } else {
      return false;
    }
  }
}
