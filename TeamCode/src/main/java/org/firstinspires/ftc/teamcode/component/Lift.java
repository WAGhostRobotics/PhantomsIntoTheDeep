package org.firstinspires.ftc.teamcode.component;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Lift {

    DcMotor leftSlides;
    DcMotor rightSlides;

    public void init(HardwareMap hardwareMap){
        leftSlides = hardwareMap.get(DcMotor.class, "leftUp");
        rightSlides = hardwareMap.get(DcMotor.class, "rightUp");

        leftSlides.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightSlides.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//
//        leftSlides.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        rightSlides.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void setPower(double power){
        if (power<0 && getPosition()[0]>400) {
            rightSlides.setPower(power);
            leftSlides.setPower(-power);
        }
        else if (power>0 && getPosition()[0]<7300) {
            rightSlides.setPower(power);
            leftSlides.setPower(-power);
        }
        else {
            rightSlides.setPower(0);
            leftSlides.setPower(0);
        }


    }

    public double[] getPosition(){
        return new double[]{leftSlides.getCurrentPosition(), rightSlides.getCurrentPosition()};
    }

}
