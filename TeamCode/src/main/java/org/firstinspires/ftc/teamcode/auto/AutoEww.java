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

        Professor.outclaw.setArmPos(0.9);

//        DriverOrientedControl drive = new DriverOrientedControl()
        //pass args and motors

        if(isStopRequested()) return;


        while(Math.abs(Professor.outlift.error)>100){
            Professor.outlift.setPosition(-1100);
            telemetry.addData("E", Professor.outlift.error);
            telemetry.addData("T", Professor.outlift.leftSlides.getCurrentPosition());
            telemetry.update();
        }

        Professor.outclaw.setArmPos(0.8);
        Professor.outclaw.close();
        Professor.outclaw.setArmPos(0.2);
        Professor.outclaw.setDofPos(0.85);

        try{
            Thread.sleep(5000);
        }
        catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }


        Professor.backLeft.setPower(0.8);
        Professor.backRight.setPower(0.8);
        Professor.frontLeft.setPower(0.8);
        Professor.frontRight.setPower(0.8);

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

        Professor.outclaw.open();

        telemetry.addData("E", Professor.outlift.error);
        telemetry.addData("T", Professor.outlift.leftSlides.getCurrentPosition());
        telemetry.update();
    }
}
