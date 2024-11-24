package org.firstinspires.ftc.teamcode.OpModes;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Arm.Pickup;
import org.firstinspires.ftc.teamcode.MecanumDrive;

public final class Test extends LinearOpMode {
    Pose2d startPose = new Pose2d(0, 0, 0);
    private MecanumDrive drive;
    @Override
    public void runOpMode()
    {
        drive = new MecanumDrive(hardwareMap, startPose);


    }
}
