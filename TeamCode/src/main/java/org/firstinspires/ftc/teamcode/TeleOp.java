package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.robocol.Command;

import org.firstinspires.ftc.teamcode.Arm.Commands.ScoreSpecimen;
import org.firstinspires.ftc.teamcode.Arm.Pickup;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends LinearOpMode {
    private MyRobot robot;
    private Pickup pickup;
    @Override
    public void runOpMode()
    {
        robot = new MyRobot(MyRobot.OpModeType.TELEOP, hardwareMap);
        pickup = robot.pickup;
        pickup.setPivotMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addLine("pivotTicks: " + pickup.getPivotPosition());
        GamepadEx manipulatorOp = new GamepadEx(gamepad2);
        GamepadEx driverOp = new GamepadEx(gamepad1);

        Button cancelSchedule = new GamepadButton(driverOp, GamepadKeys.Button.START);
        Button scoreSpecimen =  new GamepadButton(manipulatorOp, GamepadKeys.Button.A);
        Button closeClaw =  new GamepadButton(manipulatorOp, GamepadKeys.Button.LEFT_BUMPER);
        Button openClaw =  new GamepadButton(manipulatorOp, GamepadKeys.Button.RIGHT_BUMPER);
        CommandScheduler.getInstance().enable();
        waitForStart();
        while(opModeIsActive() && !isStopRequested()) {
            pickup.setPivotPower(-(float) manipulatorOp.getLeftY());
           scoreSpecimen.whenPressed(new ScoreSpecimen(pickup));
           closeClaw.whenPressed(new InstantCommand(() -> {pickup.close();}, pickup));
           openClaw.whenPressed(new InstantCommand(() -> {pickup.open();}, pickup));
           cancelSchedule.whenHeld(new InstantCommand(()-> {
               CommandScheduler.getInstance().disable();
           CommandScheduler.getInstance().reset();
           CommandScheduler.getInstance().enable();

           }), false);
            CommandScheduler.getInstance().run();
            telemetry.addLine("pivotTicks: " + pickup.getPivotPosition());
            telemetry.update();

            //CommandScheduler.getInstance().reset();
        }
        if(isStopRequested()) {
            CommandScheduler.getInstance().disable();
        }
    }
}
