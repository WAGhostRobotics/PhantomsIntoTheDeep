package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.core.Professor;
import org.firstinspires.ftc.teamcode.library.DriveStyle;
import org.firstinspires.ftc.teamcode.library.MecanumDrive;

@TeleOp(name = "TeleOpBoomer") // the name is what shows up on your phone/driver hub
public class TeleOpParent extends LinearOpMode {

//    DriverOrientedControl drive;
    public double movementPwr = 1;
    DriveStyle.DriveType type = DriveStyle.DriveType.MECANUMARCADE;

    boolean lastClawChangeIn = false;
    boolean inClawOpen = true;

    double targetL;
    double targetR;

    @Override
    public void runOpMode() throws InterruptedException{

        Professor.init(hardwareMap, true);

        Professor.inclaw.open();
        Professor.outclaw.close();
        Professor.outclaw.setArmPos(1);
        Professor.outlift.setPosition(0);

        waitForStart();

        MecanumDrive drive = new MecanumDrive(hardwareMap);

//        DriverOrientedControl drive = new DriverOrientedControl()
        //pass args and motors

        while (opModeIsActive()) {

            if(gamepad1.left_trigger>0.1){
                movementPwr = 0.25;
            }
            else{
                movementPwr = 1;
            }

//            if(gamepad2.dpad_up || lowering) {
//                Professor.outlift.setPosition(0);
//                lowering = !Professor.outlift.atTarget();
//            }
//            else {
//                Professor.outlift.setPower(gamepad2.left_stick_y);
//            }

            if(gamepad2.a){
                wallSpecimin();
            }
            if(gamepad2.y){
                hangSpecimin();
            }

//            if(gamepad2.dpad_up){
//                targetL -= 0.05;
//                targetR += 0.05;
//            }
//
//            if(gamepad2.dpad_down){
//                targetL += 0.05;
//                targetR -= 0.05;
//            }
//
//            if(gamepad2.dpad_left){
//                targetL += 0.05;
//                targetR += 0.05;
//            }
//
//            if(gamepad2.dpad_right){
//                targetL -= 0.05;
//                targetR -= 0.05;
//            }

            if(gamepad2.left_trigger>0.1){
                Professor.outlift.setPower(-gamepad2.left_trigger);
            }

            else if(gamepad2.right_trigger>0.1){
                Professor.outlift.setPower(gamepad2.right_trigger);
            }

            else{
                Professor.outlift.setPower(-0.05);
            }
            if(gamepad2.left_bumper){
                Professor.outclaw.close();
            }
            if(gamepad2.right_bumper){
                Professor.outclaw.open();
            }


//            Professor.inclaw.setClawRot(gamepad2.left_stick_x, gamepad2.right_stick_x+0.1);
//            Professor.inlift.setPosition(gamepad2.left_stick_y);

            double driveTurn = Math.pow(gamepad1.right_stick_x, 3); //change to minus if broken
            double driveY = Math.pow(gamepad1.left_stick_x, 3);
            double driveX = Math.pow(gamepad1.left_stick_y, 3);
            drive.drive(Math.hypot(driveX, driveY), Math.toDegrees(Math.atan2(driveY, driveX)), driveTurn, movementPwr);
            //Use driverOrientedControl.drive passing gamepad1 and movementPwr as args

            telemetry.addData("InLift", Professor.inlift.getPosition());
            telemetry.addData("InClaw", Professor.inclaw.getClawPos());
//            telemetry.addData("InDOF", Professor.inclaw.getDOFPosition());
            telemetry.addData("OutClaw", Professor.outclaw.getClawPos());
            telemetry.addData("OutDOF", Professor.outclaw.getDOFPosition());
            telemetry.addData("OutArm", Professor.outclaw.getArmPos());
            telemetry.addData("x", gamepad2.x);

            telemetry.update();
        }
    }
    public void wallSpecimin(){
        try{
            Thread.sleep(800);
        }
        catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
        Professor.outclaw.setDofPos(0.4);
        Professor.outclaw.setArmPos(0);

    }
    public void hangSpecimin(){
        Professor.outclaw.setDofPos(0.68);
        Professor.outclaw.setArmPos(0.3);
        try{
            Thread.sleep(800);
        }
        catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}