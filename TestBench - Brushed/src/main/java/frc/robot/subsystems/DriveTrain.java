// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.Victor;

public class DriveTrain extends SubsystemBase {
  private Victor FL = new Victor(1);
  private Victor BL = new Victor(2);
  private Victor FR = new Victor(4);
  private Victor BR = new Victor(3);
  private boolean arcade = true;

  /** Creates a new DriveTrain. */
  public DriveTrain() {}

  public void switchMode() {
    arcade = !arcade;
  }

  public void arcadeDrive(double throttle, double rotation, double speed) {
    double MtrPower = (throttle + rotation) * speed;

    FL.set(MtrPower);
    BL.set(MtrPower);
    FR.set(MtrPower);
    BR.set(MtrPower);
  }

  public void tankDrive(double lThrottle, double rThrottle, double speed) {
    lThrottle *= speed;
    rThrottle *= speed;

    FL.set(lThrottle);
    BL.set(lThrottle);
    FR.set(-rThrottle);
    BR.set(-rThrottle);
  }

  public void doDrive(double throttle, double twist, double speed) {
    if (arcade) {
      arcadeDrive(throttle, twist, speed);
    } else {
      tankDrive(throttle, twist, speed);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}