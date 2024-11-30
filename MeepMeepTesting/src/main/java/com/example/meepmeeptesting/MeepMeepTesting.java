package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(100, 100, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(6.5, -64, Math.toRadians(90)))
                .lineToY(-40)
                .setTangent(Math.toRadians(315))
                .lineToYSplineHeading(-55, Math.toRadians(45))
                .splineToLinearHeading(new Pose2d(48, -7.5, Math.toRadians(60)), Math.toRadians(45))
                .setTangent(Math.toRadians(90))
                .lineToYSplineHeading(-50, Math.toRadians(90))
                .splineToLinearHeading(new Pose2d(56, -7.5, Math.toRadians(90)), Math.toRadians(30))
                .setTangent(Math.toRadians(90))
                .lineToY(-50)
                .splineToLinearHeading(new Pose2d(-12, -35, Math.toRadians(90)), Math.toRadians(75))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}