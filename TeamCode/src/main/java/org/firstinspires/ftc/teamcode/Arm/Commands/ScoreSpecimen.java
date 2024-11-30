package org.firstinspires.ftc.teamcode.Arm.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Arm.Pickup;

public class ScoreSpecimen extends CommandBase {
    private final Pickup pickup;
    public ScoreSpecimen(Pickup pickup) {
        this.pickup = pickup;
        addRequirements(this.pickup);
    }

    @Override
    public void initialize() {
        pickup.close();
    }

    @Override
    public void execute() {
        pickup.setPivotPosition(97);

    }
}
