package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.core.Professor;

@Config
@TeleOp(name = "TestOp") // the name is what shows up on your phone/driver hub
public class TestOp extends LinearOpMode {

    public static double P, I, D;
    public static int targetPos = 0;
    public static int error = 50;
    PIDController armController = new PIDController(P, I, D);


    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor leftSlides = hardwareMap.get(DcMotor.class, "leftVert");
        DcMotor rightSlides = hardwareMap.get(DcMotor.class, "rightVert");
        waitForStart();
        while (opModeIsActive()) {
            armController.setPID(P, I, D);

            error = targetPos+leftSlides.getCurrentPosition();

            leftSlides.setPower( Range.clip(armController.calculate(0, error), -1, 1));
            rightSlides.setPower( Range.clip(armController.calculate(0, -error), -1, 1));

            telemetry.addData("power", Range.clip(armController.calculate(0, error), -1, 1));
            telemetry.addData("error", error);

            telemetry.update();
            telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        }
    }
}