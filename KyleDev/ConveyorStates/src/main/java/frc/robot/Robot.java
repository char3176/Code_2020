package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;

public class Robot extends TimedRobot {
  public Joystick Joy;
  public CANSparkMax cMotor;
  public CANPIDController cPID;
  public CANEncoder cEncoder;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;

  @Override
  public void robotInit() {
    cMotor = new CANSparkMax(1, MotorType.kBrushless);
    cMotor.restoreFactoryDefaults();
    cPID = cMotor.getPIDController();
    cEncoder = cMotor.getEncoder();
    Joy = new Joystick(1);

    kP = 0.015;
    kI = 0.01;
    kD = 0.005;
    kIz = 0;
    kFF = 0;
    kMaxOutput = 1;
    kMinOutput = -1;
    
    cPID.setP(kP);
    cPID.setI(kI);
    cPID.setD(kD);
    cPID.setIZone(kIz);
    cPID.setFF(kFF);
    cPID.setOutputRange(kMinOutput, kMaxOutput);

    SmartDashboard.putNumber("P Gain", kP);
    SmartDashboard.putNumber("I Gain", kI);
    SmartDashboard.putNumber("D Gain", kD);
    SmartDashboard.putNumber("I Zone", kIz);
    SmartDashboard.putNumber("Feed Forward", kFF);
    SmartDashboard.putNumber("Max Output", kMaxOutput);
    SmartDashboard.putNumber("Min Output", kMinOutput);
    SmartDashboard.putNumber("Set Rotations", 0);
  }

  @Override
  public void teleopPeriodic() {
    if(Joy.getRawButton(1)) {
      cMotor.set(-1);
    } else {
      cMotor.set(0);
    }
    double p = SmartDashboard.getNumber("P Gain", 0);
    double i = SmartDashboard.getNumber("I Gain", 0);
    double d = SmartDashboard.getNumber("D Gain", 0);
    double iz = SmartDashboard.getNumber("I Zone", 0);
    double ff = SmartDashboard.getNumber("Feed Forward", 0);
    double max = SmartDashboard.getNumber("Max Output", 0);
    double min = SmartDashboard.getNumber("Min Output", 0);
    double rotations = SmartDashboard.getNumber("Set Rotations", 0);

    if((p != kP)) {cPID.setP(p); kP = p;}
    if((i != kI)) {cPID.setI(i); kI = i;}
    if((d != kD)) {cPID.setD(d); kD = d;}
    if((iz != kIz)) {cPID.setIZone(iz); kIz = iz;}
    if((ff != kFF)) {cPID.setFF(ff); kFF = ff;}
    if((max != kMaxOutput) || (min != kMinOutput)) {
      cPID.setOutputRange(min, max);
      kMinOutput = min; kMaxOutput = max;
    }

    cPID.setReference(rotations, ControlType.kPosition);

    SmartDashboard.putNumber("SetPoint", rotations);
    SmartDashboard.putNumber("ProcessVariable", cEncoder.getPosition());
  }
}
