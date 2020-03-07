package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSP;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class MotorTest {
    CANSparkMax spark;
    TalonSRX talon;
    VictorSP victor;
    boolean myControllerType;

    //Sets the Talon and Sparks to the type of motor and gives them their CAN ID
    public MotorTest(int controllerType, int motorID) {
        this.myControllerType = controllerType;
        
        if (controllerType == 0) {
            this.spark = new CANSparkMax(motorID, MotorType.kBrushless);
        } else if (controllerType == 1) {
            this.talon = new TalonSRX(motorID);
        } else if (controllerType == 2) {
            this.victor = new VictorSP(motorID);
        }
    }

    public TalonSRX getTalon() {
        return this.talon;
    }

    public CANSparkMax getSpark() {
        return this.spark;
    }

    public VictorSP getVictor() {
        return this.victor;
    }
    
}