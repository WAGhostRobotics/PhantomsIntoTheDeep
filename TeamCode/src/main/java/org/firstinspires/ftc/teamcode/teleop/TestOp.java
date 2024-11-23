package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.core.Wojcik;

@TeleOp(name = "TestOp") // the name is what shows up on your phone/driver hub
public class TestOp extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException{

        Wojcik.init(hardwareMap, true);
        waitForStart();
        Wojcik.claw.close();
        while (opModeIsActive()) {
        }
    }
}
