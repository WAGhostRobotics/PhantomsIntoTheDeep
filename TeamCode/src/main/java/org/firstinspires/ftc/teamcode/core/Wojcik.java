package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.component.Claw;

/**
* This file represents your robot; here is where you'll put all of your components together to form
* one object that can be programmed to perform various tasks in TeleOpMode.
* */

public class Wojcik {

    public static Claw claw;

    public static DcMotor frontLeft;
    public static DcMotor frontRight;
    public static DcMotor backLeft;
    public static DcMotor backRight;

    public static void init(HardwareMap hwMap) {

        claw = new Claw();
        claw.init(hwMap);

        frontLeft = hwMap.get(DcMotor.class, "lf");
        frontRight = hwMap.get(DcMotor.class, "rf");
        backLeft = hwMap.get(DcMotor.class, "lr");
        backRight = hwMap.get(DcMotor.class, "rr");

//            frontLeft.setInverted(true);
//            frontRight.setInverted(true);
//            backLeft.setInverted(true);
//            backRight.setInverted(true);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }
}
