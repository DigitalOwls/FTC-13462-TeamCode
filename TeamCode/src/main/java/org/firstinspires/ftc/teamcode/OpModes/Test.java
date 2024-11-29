package org.firstinspires.ftc.teamcode.OpModes;



import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.ActionCommand;
import org.firstinspires.ftc.teamcode.Arm.Commands.GrabSpecimen;
import org.firstinspires.ftc.teamcode.Arm.Pickup;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.MyRobot;

@Autonomous
public final class Test extends LinearOpMode {
    //private MecanumDrive drive;
    private MyRobot robot;
    private Pose2d initialPose;
    @Override
    public void runOpMode()
    {
        robot = new MyRobot(MyRobot.OpModeType.AUTO_SPECIMEN_RED, hardwareMap);
        initialPose = MyRobot.initialPose;
        TrajectoryActionBuilder scorePreload = robot.drive.actionBuilder(initialPose)
                .lineToY(-40);

        TrajectoryActionBuilder afterPreloadSpecimen = scorePreload.endTrajectory().fresh()
                .setTangent(Math.toRadians(315))
                .lineToY(-55)
                .splineToLinearHeading(new Pose2d(48, -7.5, Math.toRadians(60)), Math.toRadians(45))
                .setTangent(Math.toRadians(90))
                .lineToYSplineHeading(-50, Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(56, -7.5, Math.toRadians(90)), Math.toRadians(30))
                .setTangent(Math.toRadians(90))
                .lineToY(-50)
                .splineToLinearHeading(new Pose2d(6.5, -35, Math.toRadians(90)), Math.toRadians(75));
Command afterPreloadSpecimenCommand = new ActionCommand(afterPreloadSpecimen.build(), null);
Command scorePreloadCommand = new ActionCommand(scorePreload.build(), null);
CommandScheduler.getInstance().schedule(
        new SequentialCommandGroup(scorePreloadCommand, new GrabSpecimen(robot.pickup),afterPreloadSpecimenCommand)
);
        waitForStart();

        while(opModeIsActive()) {
CommandScheduler.getInstance().run();


        }



    }
}
