package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.component.Claw;
import org.firstinspires.ftc.teamcode.component.Lift;
import org.firstinspires.ftc.teamcode.component.Pivot;

/**
* This file represents your robot; here is where you'll put all of your components together to form
* one object that can be programmed to perform various tasks in TeleOpMode.
* */

public class Wojcik {

    public static Claw claw;
    public static Lift lift;
    public static Pivot pivot;

    public static DcMotor frontLeft;
    public static DcMotor frontRight;
    public static DcMotor backLeft;
    public static DcMotor backRight;

    public static void init(HardwareMap hwMap) {

        claw = new Claw();
        claw.init(hwMap);

        lift = new Lift();
        lift.init(hwMap);

        pivot = new Pivot();
        pivot.init(hwMap);

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
