package org.firstinspires.ftc.teamcode.teleop;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.core.Bot;

/**
* This is what will run when you initiate and press play on the phone/driver hub.
* */

@TeleOp(name = "insert name here") // the name is what shows up on your phone/driver hub
public class TeleOpMode extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        // code here will run *BEFORE* you press play

        Bot.init(hardwareMap);
        GamepadEx driverOp = new GamepadEx(gamepad1); // driver

        waitForStart();

        // code below here will run *AFTER* you press play

        while (opModeIsActive()) {
            // code in here will be run over and over again until you press stop
            //TODO: program robot controls
        }
    }
}
