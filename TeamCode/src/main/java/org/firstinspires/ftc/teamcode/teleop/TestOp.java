package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "TestOp") // the name is what shows up on your phone/driver hub
public class TestOp extends LinearOpMode {

    DcMotor leftSlides;
    DcMotor rightSlides;

    @Override
    public void runOpMode() throws InterruptedException{

        leftSlides = hardwareMap.get(DcMotor.class, "leftUp");
        rightSlides = hardwareMap.get(DcMotor.class, "rightUp");

        waitForStart();
        while (opModeIsActive()) {
            rightSlides.setPower(gamepad2.left_stick_y*-1);
            leftSlides.setPower(gamepad2.left_stick_y);
        }
    }
}
