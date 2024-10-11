package org.firstinspires.ftc.teamcode.component;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Lift {

    DcMotor leftSlides;
    DcMotor rightSlides;

    public void init(HardwareMap hardwareMap){
        leftSlides = hardwareMap.get(DcMotor.class, "leftUp");
        rightSlides = hardwareMap.get(DcMotor.class, "rightUp");
    }

    public void setPosition(double power){
        rightSlides.setPower(power*-1);
        leftSlides.setPower(power);
    }

}
