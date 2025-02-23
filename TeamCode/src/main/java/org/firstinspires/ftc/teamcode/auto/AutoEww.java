package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.core.Professor;
import org.firstinspires.ftc.teamcode.library.MecanumDrive;

@Autonomous
public class AutoEww extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {

        Professor.init(hardwareMap, true);

        waitForStart();

        MecanumDrive drive = new MecanumDrive(hardwareMap);

//        DriverOrientedControl drive = new DriverOrientedControl()
        //pass args and motors

        if(isStopRequested()) return;

        Professor.outclaw.setDofPos(0.85);
        Professor.outclaw.setArmPos(0.2);

        while(Math.abs(Professor.outlift.error)>100){
            Professor.outlift.setPosition(-1100);
            telemetry.addData("E", Professor.outlift.error);
            telemetry.addData("T", Professor.outlift.leftSlides.getCurrentPosition());
            telemetry.update();
        }

        Professor.backLeft.setPower(1);
        Professor.backRight.setPower(1);
        Professor.frontLeft.setPower(1);
        Professor.frontRight.setPower(1);

        try{
            Thread.sleep(2500);
        }
        catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }

        Professor.backLeft.setPower(0);
        Professor.backRight.setPower(0);
        Professor.frontLeft.setPower(0);
        Professor.frontRight.setPower(0);

        telemetry.addData("E", Professor.outlift.error);
        telemetry.addData("T", Professor.outlift.leftSlides.getCurrentPosition());
        telemetry.update();
    }
}
