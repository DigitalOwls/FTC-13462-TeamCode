package org.firstinspires.ftc.teamcode.Arm.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Arm.Pickup;
public class GrabSpecimen extends CommandBase {
    private final Pickup pickup;
    public GrabSpecimen(Pickup pickup) {
        this.pickup = pickup;
        addRequirements(this.pickup);
    }
    @Override
    public void initialize() {
        pickup.open();
    }
    @Override
    public void execute() {
        //pickup.grab();
    }
    @Override
    public boolean isFinished() {
        return true;
    }
}
