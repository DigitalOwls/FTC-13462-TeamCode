package org.firstinspires.ftc.teamcode.Arm;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.wpilibcontroller.ElevatorFeedforward;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Elevator extends SubsystemBase {
    public static class elevatorParams {
        private static double kS = 1, kG= 0, kV = 0.0001;
    }
    private ElevatorFeedforward elevatorKSGV= new ElevatorFeedforward(elevatorParams.kS, elevatorParams.kV, elevatorParams.kG);
    private final DcMotor elevator;
    public Elevator(HardwareMap hardwareMap) {
        elevator = hardwareMap.get(DcMotor.class, "liftdrive");
    }

    public void extend(double distance) {
     //   elevator.
    }
}
