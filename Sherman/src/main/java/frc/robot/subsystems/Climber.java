package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.ClimberConstants;

public class Climber extends SubsystemBase {
  private static Climber instance = new Climber();

  private CANSparkMax motor= new CANSparkMax(ClimberConstants.MOTOR_ID, MotorType.kBrushless);

  public Climber() {
    motor.restoreFactoryDefaults();
    motor.setIdleMode(IdleMode.kBrake);
    motor.setSecondaryCurrentLimit(100);
    motor.burnFlash();
  }

  @Override
  public void periodic() {
  }

  public static Climber getInstance() {
    return instance;
  }

  public void setPercentControl(double percent) {
    motor.set(percent);
  }
}