package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.component.IntakeClaw;
import org.firstinspires.ftc.teamcode.component.IntakeLift;
import org.firstinspires.ftc.teamcode.component.OutputClaw;
import org.firstinspires.ftc.teamcode.component.OutputLift;

/**
* This file represents your robot; here is where you'll put all of your components together to form
* one object that can be programmed to perform various tasks in TeleOpMode.
* */

public class Professor {

    public static IntakeClaw inclaw;
    public static IntakeLift inlift;
    public static OutputClaw outclaw;
    public static OutputLift outlift;

    public static DcMotor frontLeft;
    public static DcMotor frontRight;
    public static DcMotor backLeft;
    public static DcMotor backRight;

    public static void init(HardwareMap hwMap, boolean teleOp) {

        inclaw = new IntakeClaw();
        inclaw.init(hwMap);

        inlift = new IntakeLift();
        inlift.init(hwMap);

        outclaw = new OutputClaw();
        outclaw.init(hwMap);

        outlift = new OutputLift();
        outlift.init(hwMap);

        if(teleOp) {
            frontLeft = hwMap.get(DcMotor.class, "leftFront");
            frontRight = hwMap.get(DcMotor.class, "rightFront");
            backLeft = hwMap.get(DcMotor.class, "leftBack");
            backRight = hwMap.get(DcMotor.class, "rightBack");

            frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
            backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            backRight.setDirection(DcMotorSimple.Direction.REVERSE);

            frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }
}
