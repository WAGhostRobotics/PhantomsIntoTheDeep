package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.core.Wojcik;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

public class AutoParent extends LinearOpMode {


    protected boolean red;
    protected boolean bucket;

    private int xMult;
    private int yMult;
    private int thetaMult;
    private int pos;

    private Trajectory drop;
    private Trajectory park;

    Pose2d startPose;

    @Override
    public void runOpMode() throws InterruptedException {

        Wojcik.init(hardwareMap, false);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        xMult = bucket ? 2 : -1;
        yMult = red ? -1 : 1;
        thetaMult = red ? -90 : 90;

        pos = xMult + yMult;

        switch(pos){
            case 3: //Blue bucket
                startPose = new Pose2d(36, 60, Math.toRadians(270));
//                 forward = drive.trajectoryBuilder(new Pose2d(24, 60, Math.toRadians(270)))
//                        .splineTo(new Vector2d(48, 36), Math.toRadians(270))
//                        .build();
//                 basket = drive.trajectoryBuilder(forward.end())
//                        .lineToLinearHeading(new Pose2d(48, 54, Math.toRadians(45)))
//                        .build();
                drop =  drive.trajectoryBuilder(new Pose2d(24, 60, Math.toRadians(270)))
                        .lineToLinearHeading(new Pose2d(48, 60, 0))
                        .build();
                park = drive.trajectoryBuilder(drop.end())
                        .back(5)
                        .splineTo(new Vector2d(0, 36), Math.toRadians(270))
                        .splineTo(new Vector2d(-48, 60), Math.PI)
                        .build();
                break;
            case 1: //Red bucket
                startPose = new Pose2d(-36, -60, Math.toRadians(90));
//                 forward = drive.trajectoryBuilder(new Pose2d(24, -60, Math.toRadians(90)))
//                        .splineTo(new Vector2d(48, -36), Math.toRadians(-270))
//                        .build();
//                 basket = drive.trajectoryBuilder(forward.end())
//                        .lineToLinearHeading(new Pose2d(48, -54, Math.toRadians(-45)))
//                        .build();
                drop =  drive.trajectoryBuilder(new Pose2d(-24, -60, Math.toRadians(90)))
                        .lineToLinearHeading(new Pose2d(-48, -60, Math.PI))
                        .build();
                park = drive.trajectoryBuilder(drop.end())
                        .back(5)
                        .splineTo(new Vector2d(0, -36), Math.toRadians(90))
                        .splineTo(new Vector2d(48, -60), 0)
                        .build();
                break;
            case 0: //Blue far
                startPose = new Pose2d(-36, 60, Math.toRadians(270));
//                 forward = drive.trajectoryBuilder(new Pose2d(-12, 60, Math.toRadians(270)))
//                        .splineTo(new Vector2d(48, 36), Math.toRadians(270))
//                        .build();
//                 basket = drive.trajectoryBuilder(forward.end())
//                        .lineToLinearHeading(new Pose2d(48, 54, Math.toRadians(45)))
//                        .build();
                drop =  drive.trajectoryBuilder(new Pose2d(-12, 60, Math.toRadians(270)))
                        .splineTo(new Vector2d(0, 36), 0)
                        .splineTo(new Vector2d(48, 60), 0)
                        .build();
                park = drive.trajectoryBuilder(drop.end())
                        .back(5)
                        .splineTo(new Vector2d(0, 36), Math.toRadians(270))
                        .splineTo(new Vector2d(-54, 54), Math.toRadians(270))
                        .build();
                break;
            case -2://Red far
                startPose = new Pose2d(36, -60, Math.toRadians(90));
//                 forward = drive.trajectoryBuilder(new Pose2d(-12, -60, Math.toRadians(90)))
//                        .splineTo(new Vector2d(48, -36), Math.toRadians(-270))
//                        .build();
//                 basket = drive.trajectoryBuilder(forward.end())
//                        .lineToLinearHeading(new Pose2d(48, -54, Math.toRadians(-45)))
//                        .build();
                drop =  drive.trajectoryBuilder(new Pose2d(12, -60, Math.toRadians(90)))
                        .splineTo(new Vector2d(0, -36), Math.toRadians(90))
                        .splineTo(new Vector2d(-48, -60), Math.PI)
                        .build();
                park = drive.trajectoryBuilder(drop.end())
                        .back(5)
                        .splineTo(new Vector2d(0, -36), Math.toRadians(90))
                        .splineTo(new Vector2d(48, -60), 0)
                        .build();
                break;
        }

//        startPose = new Pose2d(12, -60, Math.toRadians(270));
        drive.setPoseEstimate(startPose);
//        drop = drive.trajectoryBuilder(startPose)
//                .splineTo(new Vector2d(0, -36), Math.toRadians(270))
//                .splineTo(new Vector2d(48, -60), 0)
//                .build();
        waitForStart();
        Wojcik.claw.open();
        if(isStopRequested()) return;

        drive.followTrajectory(drop);
        Wojcik.claw.close();
        drive.followTrajectory(park);
    }
}