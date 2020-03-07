package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.*;
import frc.robot.constants.ControllerConstants;

public class Controller {
    // Instantiating the instance of Controller to be used system-wide
    private static Controller instance = new Controller();

    // Instantiating necessary joysticks and buttons
    private final Joystick speedStick = new Joystick(ControllerConstants.SPEED_STICK_ID);
    private final Joystick rotStick = new Joystick(ControllerConstants.ROT_STICK_ID);
    private final XboxController operator = new XboxController(ControllerConstants.OPERATOR_ID);

    private final JoystickButton ballChaseButton = new JoystickButton(speedStick, ControllerConstants.BALL_CHASE_BUTTON_ID);
    private final JoystickButton climbButton = new JoystickButton(operator, Button.kA.value);
    private final JoystickButton manualHarvestButton = new JoystickButton(operator, Button.kBumperLeft.value);
    private final JoystickButton manualConveyorButton = new JoystickButton(operator, Button.kBumperRight.value);
    private final JoystickButton harvestButton = new JoystickButton(operator, Button.kX.value);
    //private final POVButton sowButton = new POVButton(operator, 180);
    // private final JoystickButton manualShooterButton = new JoystickButton(operator, Button.kBack.value);
    // private final JoystickButton manualTurretButton = new JoystickButton(operator, Button.kStart.value);
    private final JoystickButton spinUpButton = new JoystickButton(operator, Button.kStart.value);
    private final JoystickButton spinDownButton = new JoystickButton(operator, Button.kBack.value);
    private final JoystickButton shootButton = new JoystickButton(operator, Button.kB.value);
    private final JoystickButton sowButton = new JoystickButton(operator, Button.kY.value);

    // getInstance function for controller
    public static Controller getInstance() {
        return instance;
    }

    // Gets the joystick speed input for normal arcade drive
    public double getArcadeDriveSpeed() {
        // Switches between a fast and slow drive control by multiplying joystick output by a constant
        if (!speedStick.getRawButton(1)) {
            // Adds in deadband logic
            if (Math.abs(speedStick.getY()) < ControllerConstants.DEADBAND) {
                return 0.0;
            } else if (speedStick.getY() < 0) {
                return (speedStick.getY() + ControllerConstants.DEADBAND) * ControllerConstants.SLOW_SPEED_MULT * (10.0 / 9.0);
            } else {
                return (speedStick.getY() - ControllerConstants.DEADBAND) * ControllerConstants.SLOW_SPEED_MULT  * (10.0 / 9.0);
            }
        } else {
            // Adds in deadband logic
            if (Math.abs(speedStick.getY()) < ControllerConstants.DEADBAND) {
                return 0.0;
            } else if (speedStick.getY() < 0) {
                return (speedStick.getY() + ControllerConstants.DEADBAND) * (10.0 / 9.0);
            } else {
                return (speedStick.getY() - ControllerConstants.DEADBAND) * (10.0 / 9.0);
            }
        }
    }

    // Gets the joystick rotation input for normal arcade drive
    public double getArcadeDriveRot() {
        if(!speedStick.getRawButton(1)){
            // Adds in deadband logic
            if (Math.abs(rotStick.getX()) < ControllerConstants.DEADBAND) {
                return 0.0;
            } else if (rotStick.getX() < 0) {
                return (rotStick.getX() + ControllerConstants.DEADBAND) * ControllerConstants.SLOW_ROT_MULT * Math.PI;
            } else {
                return (rotStick.getX() - ControllerConstants.DEADBAND) * ControllerConstants.SLOW_ROT_MULT * Math.PI;
            }
        } else {
            // Adds in deadband logic
            if (Math.abs(rotStick.getX()) < ControllerConstants.DEADBAND) {
                return 0.0;
            } else if (rotStick.getX() < 0) {
                return (rotStick.getX() + ControllerConstants.DEADBAND) * Math.PI;
            } else {
                return (rotStick.getX() - ControllerConstants.DEADBAND) * Math.PI;
            }
        }
    }

    public boolean shift() {
        return speedStick.getRawButtonPressed(5);
    }

    public Boolean resetOdometry() {
        return speedStick.getRawButton(6);
    }

    // Getters for buttons to be passed through RobotContainer
    public JoystickButton getBallChaseButton() {
        return ballChaseButton;
    } 

    public JoystickButton getClimbButton() {
        return climbButton;
    }

    public JoystickButton getManualHarvestButton() {
        return manualHarvestButton;
    }

    public double getManualHarvestMotorSupply() {
        return operator.getY();
    }

    public boolean getManualHarvestActuate() {
        return operator.getBButtonPressed();
    }

    public JoystickButton getManualConveyorButton() {
        return manualConveyorButton;
    }

    public double getManualConveyorSupplier() {
        return operator.getY();
    }

    public JoystickButton getHarvestButton() {
        return harvestButton;
    }

    public double getClimbPercent() {
        return operator.getY(Hand.kLeft);
    }

    public JoystickButton getSowButton() {
        return sowButton;
    }

    // public JoystickButton getManualShooterButton() {
    //     return manualShooterButton;
    // }

    public double getManualShooterFlywheel() {
        return operator.getY(Hand.kRight);
    }

    public double getManualShooterBooster() {
        return operator.getY(Hand.kLeft);
    }

    // public JoystickButton getManualTurretButton() {
    //     return manualTurretButton;
    // }

    public double getManualTurretIncrease() {
        return operator.getX(Hand.kLeft);
    }

    public JoystickButton getSpinUpButton() {
        return spinUpButton;
    }

    public JoystickButton getSpinDownButton() {
        return spinDownButton;
    }

    public boolean getConveyorShootUp() {
        if (operator.getPOV() == 0 || operator.getPOV() == 45 || operator.getPOV() == 315) {
            return true;
        } else {
            return false;
        }
    }

    public boolean getConveyorShootDown() {
        if (operator.getPOV() == 135 || operator.getPOV() == 180 || operator.getPOV() == 225) {
            return true;
        } else {
            return false;
        }
    }

    public JoystickButton getShootButton() {
        return shootButton;
    }
}