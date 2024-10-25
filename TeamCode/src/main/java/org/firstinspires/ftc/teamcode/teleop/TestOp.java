package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "TestOp") // the name is what shows up on your phone/driver hub
public class TestOp extends LinearOpMode {

    DcMotor leftSlides;
    DcMotor rightSlides;

    DcMotor leftPivot;
    DcMotor rightPivot;

    @Override
    public void runOpMode() throws InterruptedException{

        leftSlides = hardwareMap.get(DcMotor.class, "leftUp");
        rightSlides = hardwareMap.get(DcMotor.class, "rightUp");

        leftPivot = hardwareMap.get(DcMotor.class, "leftPivot");
        rightPivot = hardwareMap.get(DcMotor.class, "rightPivot");

        leftSlides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightSlides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        while (opModeIsActive()) {
            rightSlides.setPower(gamepad2.left_stick_y*-1);
            leftSlides.setPower(gamepad2.left_stick_y);

            rightPivot.setPower(gamepad2.right_stick_y*-0.4);
            leftPivot.setPower(gamepad2.right_stick_y*0.4);
        }
    }
}
