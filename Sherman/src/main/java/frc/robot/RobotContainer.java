package frc.robot;

import java.io.IOException;
import java.nio.file.Path;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.ChaseBall;
import frc.robot.commands.SimpleClimb;
import frc.robot.commands.ConveyorIntake;
import frc.robot.commands.Harvest;
import frc.robot.commands.ManualConveyor;
import frc.robot.commands.ManualHarvest;
import frc.robot.commands.ManualShooter;
import frc.robot.commands.OscillateTurret;
import frc.robot.commands.SimpleShoot;
import frc.robot.commands.Sow;
import frc.robot.commands.SpinDownShooter;
import frc.robot.commands.SpinUpShooter;
import frc.robot.commands.TurretToZero;
import frc.robot.constants.DrivetrainConstants;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;

public class RobotContainer {
  private Compressor m_Compressor = new Compressor();
  private Controller m_Controller = Controller.getInstance();
  private Conveyor m_Conveyor = Conveyor.getInstance();
  private Drivetrain m_Drivetrain = Drivetrain.getInstance();
  private Turret m_Turret = Turret.getInstance();
  private Shooter m_Shooter = Shooter.getInstance();

  public RobotContainer() {
    m_Compressor.start();
    m_Drivetrain.setDefaultCommand(new ArcadeDrive(() -> m_Controller.getArcadeDriveSpeed(), () -> m_Controller.getArcadeDriveRot(), () -> m_Controller.shift()));
    m_Conveyor.setDefaultCommand(new ConveyorIntake());
    //m_Turret.setDefaultCommand(new OscillateTurret());
    // m_Shooter.setDefaultCommand(new SpinDownShooter());
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    //High Level
    m_Controller.getHarvestButton().whenHeld(new Harvest());
    m_Controller.getBallChaseButton().whenHeld(new ChaseBall());
    m_Controller.getShootButton().whenHeld(new SimpleShoot(() -> m_Controller.getConveyorShootUp(), () -> m_Controller.getConveyorShootDown()));

    //Low Level
    m_Controller.getManualHarvestButton().whenHeld(new ManualHarvest(() -> m_Controller.getManualHarvestMotorSupply(), () -> m_Controller.getManualHarvestActuate()));
    m_Controller.getManualConveyorButton().whenHeld(new ManualConveyor(() -> m_Controller.getManualConveyorSupplier()));
    m_Controller.getClimbButton().whenHeld(new SimpleClimb(() -> m_Controller.getClimbPercent()));
    m_Controller.getSowButton().whenHeld(new Sow());
    // m_Controller.getManualShooterButton().whenHeld(new ManualShooter(() -> m_Controller.getManualShooterFlywheel(), () -> m_Controller.getManualShooterBooster()));
    //m_Controller.getManualTurretButton().whenHeld(new ManualTurret(() -> m_Controller.getManualTurretIncrease()));
    m_Controller.getSpinDownButton().whenPressed(new SpinDownShooter());
    m_Controller.getSpinUpButton().whenPressed(new SpinUpShooter());

  }

  public Command getAutonomousCommand() throws IOException {
    Trajectory m_Trajectory;
    String trajectoryJSON = "paths/output/Unnamed.wpilib.json";
    Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
    m_Trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    RamseteCommand m_RamseteCommand = new RamseteCommand(m_Trajectory, () -> m_Drivetrain.getCurrentPose(), new RamseteController(), m_Drivetrain.getKinematics(), m_Drivetrain::setSpeedsDouble, m_Drivetrain);
    return m_RamseteCommand;
  }
}
