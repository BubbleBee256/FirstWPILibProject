// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class WheelSubsystem extends SubsystemBase {
    // Thread-safe singleton design pattern.
    private static volatile WheelSubsystem instance;
    private static Object mutex = new Object();

    private final int DRIVING_MOTOR_ID = 3;
    private final int TURNING_MOTOR_ID = 2;
    private final int CANCODER_ID = 10;

    private CANSparkMax drivingMotor = new CANSparkMax(DRIVING_MOTOR_ID, MotorType.kBrushless);
    private CANSparkMax turningMotor = new CANSparkMax(TURNING_MOTOR_ID, MotorType.kBrushless);
    private CANcoder motorInfo = new CANcoder(CANCODER_ID, "swerve");
    private StatusSignal<Double> statusSignal = motorInfo.getAbsolutePosition();


    public static WheelSubsystem getInstance() {
        WheelSubsystem result = instance;
       
        if (result == null) {
            synchronized (mutex) {
                result = instance;
                if (result == null) {
                    instance = result = new WheelSubsystem();
                }
            }
        }
        return instance;
    }

    /** Creates a new ExampleSubsystem. */
    public WheelSubsystem() {
        super("ExampleSubsystem");
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
    
    public void setDrivingMotorSpeed(double speed) {
        drivingMotor.set(speed);
    }

    public void setTurningMotorSpeed(double speed) {
        turningMotor.set(speed);
    }

    public double getMotorPosition() {
        return Units.radiansToDegrees(statusSignal.getValue());
    }
}
