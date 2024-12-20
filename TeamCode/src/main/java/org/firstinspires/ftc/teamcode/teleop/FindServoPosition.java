package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="Find Servo Position")
public class FindServoPosition extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Servo servo = hardwareMap.get(Servo.class, "insert servo name here");

        double position; // initial position of the servo

        waitForStart();

        while (opModeIsActive()) {

            position = gamepad1.left_stick_x;

            servo.setPosition(position); // move servo to new position

            // displays servo position to the phone/driver hub
            telemetry.addData("Servo position", position);
            telemetry.update();
        }

    }
}
