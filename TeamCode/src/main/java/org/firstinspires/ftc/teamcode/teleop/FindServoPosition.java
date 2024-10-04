package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * This program displays the current servo position and allows you to adjust it \
 * by small increments using a and b on the gamepad until you find your ideal close and open
 * positions. The code in here does not need to be changed (besides possibly line 21).
 * */

@TeleOp(name="Find Servo Position")
public class FindServoPosition extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Servo servo = hardwareMap.get(Servo.class, "insert servo name here");

        // DON'T TOUCH THE LINE BELOW UNLESS YOUR SERVO IS GOING THE WRONG WAY
        // servo.setDirection(Servo.Direction.REVERSE);

        double position = 0; // initial position of the servo

        waitForStart();

        while (opModeIsActive()) {

            // adjust positions with gamepad; remember that servo position are only from 0 to 1
            if (gamepad1.a) {
                position += 0.0001;
            } else if (gamepad1.b) {
                position -= 0.0001;
            }

            servo.setPosition(position); // move servo to new position

            // displays servo position to the phone/driver hub
            telemetry.addData("Servo position", position);
            telemetry.update();
        }

    }
}
