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

    boolean lastClawChange = false;
    boolean endgame = false;

    @Override
    public void runOpMode() throws InterruptedException{

        Professor.init(hardwareMap, true);

        Professor.inclaw.open();
        Professor.outclaw.close();
        Professor.outclaw.setArmPos(1);
//        Professor.outlift.setPosition(0);

        waitForStart();

        MecanumDrive drive = new MecanumDrive(hardwareMap);

//        DriverOrientedControl drive = new DriverOrientedControl()
        //pass args and motors

        while (opModeIsActive()) {

            if (gamepad1.left_trigger > 0.1) {
                movementPwr = 0.25;
            } else {
                movementPwr = 1;
            }

            if (gamepad2.a) {
                grabSpecimin();
            }
            if (gamepad2.y) {
                hangSpecimin();
            }
            if (gamepad2.x) {
                transferPos();
            }
            if (gamepad2.b) {
                intakePos();
            }

            if (Professor.outlift.getPosition() < 2000) {
                Professor.outlift.setPower(gamepad2.left_trigger - gamepad2.right_trigger - 0.00005 * Professor.outlift.getPosition());
            }
            else {
                Professor.outlift.setPower(gamepad2.left_trigger - 0.1);
            }

            if (gamepad2.right_bumper && !lastClawChange) {
                Professor.outclaw.switchClaw();
                Professor.inclaw.switchClaw();
                lastClawChange = true;
            } else if (!gamepad2.right_bumper) {
                lastClawChange = false;
            }

            if (gamepad2.left_bumper){
                endgame = true;
            }

            if (gamepad2.dpad_up) {
                Professor.inlift.moveIn();
            }

            if (gamepad2.dpad_down) {
                Professor.inlift.moveOut();
            }

            if (endgame) {
                if (gamepad2.dpad_left) {
                    Professor.inclaw.setClawRot(0.1, 0.9);
                }
                if (gamepad2.dpad_right) {
                    hang();
                }
            }

            double driveTurn = Math.pow(gamepad1.right_stick_x, 3); //change to minus if broken
            double driveY = Math.pow(gamepad1.left_stick_x, 3);
            double driveX = Math.pow(gamepad1.left_stick_y, 3);
            drive.drive(Math.hypot(driveX, driveY), Math.toDegrees(Math.atan2(driveY, driveX)), driveTurn, movementPwr);
            //Use driverOrientedControl.drive passing gamepad1 and movementPwr as args

            telemetry.addData("InClaw", Professor.inclaw.getClawPos());
            telemetry.addData("OutDOF", Professor.outclaw.getDOFPosition());
            telemetry.addData("OutArm", Professor.outclaw.getArmPos());
            telemetry.addData("Slides", Professor.outlift.getPosition());

            telemetry.update();
        }
    }
    public void transferPos(){
        Professor.outclaw.setArmPos(0.22);
        try{
            Thread.sleep(200);
        }
        catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
        Professor.inclaw.setClawRot(0.75, 0.25);
        Professor.inlift.setPosition(0.48);
        Professor.outclaw.setDofPos(0.16);
        Professor.outclaw.setArmPos(1);
    }
    public void intakePos(){
        Professor.inclaw.switchClaw();
        try{
            Thread.sleep(200);
        }
        catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
        Professor.inclaw.setClawRot(0.25, 0.75);
        try{
            Thread.sleep(100);
        }
        catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
        Professor.inclaw.switchClaw();
    }
    public void hangSpecimin(){
        Professor.outclaw.setDofPos(0.85);
        Professor.outclaw.setArmPos(0.2);
    }
    public void grabSpecimin(){
        Professor.outclaw.setDofPos(0.50);
        Professor.outclaw.setArmPos(0.99);
    }

    public void hang(){
        while(true) {
            Professor.outlift.setPower(1);
            Professor.inlift.setPosition(0);
        }
    }
}