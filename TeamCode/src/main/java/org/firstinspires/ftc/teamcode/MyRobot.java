package org.firstinspires.ftc.teamcode;



import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.command.Robot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Arm.Pickup;

public class MyRobot extends Robot {
    public Pickup pickup;
    public MecanumDrive drive;
    public static Pose2d initialPose;

    public enum OpModeType {
        TELEOP, AUTO_BASKET_RED, AUTO_BASKET_BLUE, AUTO_SPECIMEN_RED, AUTO_SPECIMEN_BLUE
    }

    public MyRobot(OpModeType type, HardwareMap hardwareMap) {
        if (type == OpModeType.TELEOP) {
            initTele(hardwareMap);
        }
        else{
            if(type == OpModeType.AUTO_SPECIMEN_RED) {
               initialPose = new Pose2d(6.5, -64, Math.toRadians(90));
            }
            initAuto(hardwareMap, initialPose);
        }


    }


    public void initTele(HardwareMap hardwareMap) {
        pickup = new Pickup(hardwareMap);


        }

        public void initAuto (HardwareMap hardwareMap, Pose2d startPose) {
        pickup = new Pickup(hardwareMap);
drive = new MecanumDrive(hardwareMap, startPose);
        }
    }

