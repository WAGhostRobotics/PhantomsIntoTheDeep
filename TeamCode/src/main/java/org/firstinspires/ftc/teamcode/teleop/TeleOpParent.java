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
    boolean lastClawChangeOut = false;
    boolean inClawOpen = true;
    boolean outClawOpen = true;
    boolean lowering;

    @Override
    public void runOpMode() throws InterruptedException{

        Professor.init(hardwareMap, true);

        Professor.inclaw.open();
        Professor.outclaw.close();
        Professor.outclaw.dofReady();

        waitForStart();

        MecanumDrive drive = new MecanumDrive(hardwareMap);

//        DriverOrientedControl drive = new DriverOrientedControl()
        //pass args and motors

        while (opModeIsActive()) {

            if(gamepad2.a && !lastClawChangeIn){
                if(inClawOpen){
                    Professor.inclaw.close();
                }
                else if(!inClawOpen){
                    Professor.inclaw.open();
                }
                inClawOpen = !inClawOpen;
            }

            lastClawChangeIn = gamepad2.a;

            if(gamepad2.y && !lastClawChangeOut){
                if(outClawOpen){
                    Professor.outclaw.close();
                }
                else if(!outClawOpen){
                    Professor.outclaw.open();
                }
                outClawOpen = !outClawOpen;
            }

            lastClawChangeOut = gamepad2.y;

            if (gamepad2.x){
                passSample();
            }

            if(gamepad1.left_trigger>0.1){
                movementPwr = 0.25;
            }
            else{
                movementPwr = 1;
            }
            if(gamepad2.dpad_right){
                Professor.inlift.setPosition(0);
            }

            if(gamepad2.dpad_left){
                Professor.inlift.setPosition(1);
            }

//            if(gamepad2.dpad_up || lowering) {
//                Professor.outlift.setPosition(0);
//                lowering = !Professor.outlift.atTarget();
//            }
//            else {
//                Professor.outlift.setPower(gamepad2.left_stick_y);
//            }

            if(gamepad2.x){
                Professor.outclaw.armUp();
            }
            else if(gamepad2.b){
                Professor.outclaw.armDown();
            }

            if(gamepad2.left_bumper){
                Professor.outclaw.turnDown();
            }

            if(gamepad2.right_bumper){
                Professor.outclaw.turnUp();
            }



            double driveTurn = Math.pow(gamepad1.right_stick_x, 3); //change to minus if broken
            double driveY = Math.pow(gamepad1.left_stick_x, 3);
            double driveX = Math.pow(gamepad1.left_stick_y, 3);
            drive.drive(Math.hypot(driveX, driveY), Math.toDegrees(Math.atan2(driveY, driveX)), driveTurn, movementPwr);
            //Use driverOrientedControl.drive passing gamepad1 and movementPwr as args

            telemetry.addData("Pos L", Professor.inlift.getPosition());

            telemetry.update();
        }
    }
    public void passSample(){
        Professor.inlift.setPosition(1);
        Professor.inclaw.setDOFPosition(1);
        Professor.outlift.setPosition(0);
        lowering = true;

    }
}