package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(35, -60, 0))
                                .addTemporalMarker(() -> {

                                })
                                .lineToConstantHeading(new Vector2d(17,-60))
                                .UNSTABLE_addTemporalMarkerOffset(-0.5,() -> {
                                    //arm up

                                })
                                .splineToSplineHeading(new Pose2d(17,-32,Math.toRadians(55)),Math.toRadians(70))
                                .addTemporalMarker(() -> {
                                    //let go
                                    })
                                .waitSeconds(0.5)
                                .back(6)
                                .addTemporalMarker(() -> {
                                    //arm down

                                })
                                .turn(Math.toRadians(35))
                                .forward(20)
                                .splineTo(new Vector2d(25,-12),0)
                                .strafeTo(new Vector2d(55,-12))
                                .UNSTABLE_addTemporalMarkerOffset(-0.5, () -> {
                                    //arm up, get cone from stack

                                })
                                .forward(6)
                                .addTemporalMarker(() -> {
                                    //grab, lift


                                })
                                .waitSeconds(0.5)
                                .back(7)
                                .turn(Math.toRadians(-120))
                                .forward(4)
                                .addTemporalMarker(() -> {
                                    //let go

                                })
                                .waitSeconds(0.5)
                                .back(4)
                                .turn(Math.toRadians(120))
                                .forward(7)
                                .addTemporalMarker(() -> {
                                    //grab from stack, lift

                                })
                                .waitSeconds(0.5)
                                .back(50)
                                .turn(Math.toRadians(-42))
                                .forward(7)
                                .addTemporalMarker(() -> {
                                    //let go

                                })
                                .waitSeconds(0.5)
                                .back(7)
                                .addTemporalMarker(() -> {

                                })
                                .turn(Math.toRadians(42))
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}