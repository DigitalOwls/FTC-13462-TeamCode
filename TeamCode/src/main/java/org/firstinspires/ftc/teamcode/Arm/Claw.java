package org.firstinspires.ftc.teamcode.Arm;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import static org.firstinspires.ftc.teamcode.DConstants.*;

public class Claw extends SubsystemBase {
    private final Servo claw;
    public Claw(final HardwareMap hMap, final String name) {
        claw = hMap.get(Servo.class, name);
    }
    //name should be the same as given in the configuration file
    public void grab() {
        claw.setPosition(gripPosition);
    }


}
