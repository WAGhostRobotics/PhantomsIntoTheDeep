package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.core.Wojcik;
import org.firstinspires.ftc.teamcode.library.DriveStyle;
import org.firstinspires.ftc.teamcode.library.DriverOrientedControl;
import org.firstinspires.ftc.teamcode.library.MecanumDrive;

@TeleOp(name = "TeleOpBoomer") // the name is what shows up on your phone/driver hub
public class TeleOpParent extends LinearOpMode {

//    DriverOrientedControl drive;
    public double movementPwr = 1;
    public double turningMultiplier = 1;
    DriveStyle.DriveType type = DriveStyle.DriveType.MECANUMTANK;

    @Override
    public void runOpMode() throws InterruptedException{

        Wojcik.init(hardwareMap);

        waitForStart();

        MecanumDrive drive = new MecanumDrive(hardwareMap);

        while (opModeIsActive()) {

            if(gamepad1.b){
                Wojcik.claw.open();
            }
            if(gamepad1.a){
                Wojcik.claw.close();
            }

            Wojcik.lift.setPosition(gamepad2.left_stick_y);

            double driveTurn = Math.pow(gamepad1.right_stick_x, 3); //change to minus if broken
            double driveY = Math.pow(gamepad1.left_stick_x, 3);
            double driveX = Math.pow(gamepad1.left_stick_y, 3);
            drive.drive(Math.hypot(driveX, driveY), Math.toDegrees(Math.atan2(driveY, driveX)), driveTurn, movementPwr);


        }
    }
}
