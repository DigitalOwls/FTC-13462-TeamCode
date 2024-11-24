package org.firstinspires.ftc.teamcode.Arm;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorControllerEx;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import static org.firstinspires.ftc.teamcode.DConstants.*;

public class Pickup extends SubsystemBase {
    private final Servo claw;
    private final DcMotorEx pivot;
    private double pivotkP = 0.5; //needs to be tuned
    PController pivotP = new PController(pivotkP);

    public Pickup(HardwareMap hardwareMap) {
        claw = hardwareMap.get(Servo.class, "clawservo");
        pivot = hardwareMap.get(DcMotorEx.class, "pivot");
    }
    //name should be the same as given in the configuration file
    public void grabSpecimen() {
        claw.setPosition(GRIP_POSITION);
    }
    public void open() {
        claw.setPosition(OPEN_POSITION);
    }
    public void grabSample() {

    }
    public double getPositon() {
        return claw.getPosition();
    }
    public int getPivotPositon() {
        return pivot.getCurrentPosition();
    }
    public void stop(DcMotor motor) {
        motor.setPower(0);
    }
    public void setPivotPosition(double angle) {
        pivotP.setSetPoint((angle / 90) * TICKS_PER_QUARTER_REVOLUTION);
        pivotP.setTolerance(50);
        while(!pivotP.atSetPoint()) {
            double output = pivotP.calculate(pivot.getCurrentPosition());
            pivot.setVelocity(output);
        }
        stop(pivot);

    }

}
