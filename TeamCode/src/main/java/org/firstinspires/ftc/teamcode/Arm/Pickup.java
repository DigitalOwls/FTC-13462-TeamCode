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
    private double pivotkP = 0.75; //needs to be tuned
    PController pivotP = new PController(pivotkP);

    public Pickup(HardwareMap hardwareMap) {
        claw = hardwareMap.get(Servo.class, "clawServo");
        pivot = hardwareMap.get(DcMotorEx.class, "swingmotor");
        resetPivotPositon();
        pivot.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }
    //name should be the same as given in the configuration file
    public void close() {
        claw.setPosition(GRIP_POSITION);
    }
    public void open() {
        claw.setPosition(OPEN_POSITION);
    }
    public void grabSample() {

    }
    public double getClawPosition() {
        return claw.getPosition();
    }
    public int getPivotPosition() {
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


    public void resetPivotPositon()
    {
        pivot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void setPivotPower(float power) {
        pivot.setPower(power);
    }
    public void setPivotMode(DcMotor.RunMode mode) {
        pivot.setMode(mode);
    }

}
