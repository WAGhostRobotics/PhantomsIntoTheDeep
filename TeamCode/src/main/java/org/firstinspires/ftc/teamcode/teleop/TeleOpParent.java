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
    boolean clawOpen = true;

    @Override
    public void runOpMode() throws InterruptedException{

        Professor.init(hardwareMap, true);

        Professor.inclaw.open();

        waitForStart();

        MecanumDrive drive = new MecanumDrive(hardwareMap);

//        DriverOrientedControl drive = new DriverOrientedControl()
        //pass args and motors

        while (opModeIsActive()) {

            if(gamepad2.a && !lastClawChange){
                if(clawOpen){
                    Professor.inclaw.close();
                }
                else if(!clawOpen){
                    Professor.inclaw.open();
                }
                clawOpen = !clawOpen;
            }

            lastClawChange = gamepad2.a;

            if(gamepad1.left_trigger>0.1){
                movementPwr = 0.25;
            }
            else{
                movementPwr = 1;
            }
            if(gamepad2.dpad_left){
                Professor.inclaw.spinLeft();
            }

            if(gamepad2.dpad_right){
                Professor.inclaw.spinRight();
            }

            Professor.inlift.setPower(gamepad2.right_trigger - gamepad2.left_trigger);

            double driveTurn = Math.pow(gamepad1.right_stick_x, 3); //change to minus if broken
            double driveY = Math.pow(gamepad1.left_stick_x, 3);
            double driveX = Math.pow(gamepad1.left_stick_y, 3);
            drive.drive(Math.hypot(driveX, driveY), Math.toDegrees(Math.atan2(driveY, driveX)), driveTurn, movementPwr);
            //Use driverOrientedControl.drive passing gamepad1 and movementPwr as args

            telemetry.addData("Pos L", Professor.inlift.getPosition()[0]);
            telemetry.addData("Pos R", Professor.inlift.getPosition()[1]);

            telemetry.update();
        }
    }
}