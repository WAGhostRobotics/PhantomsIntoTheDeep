package org.firstinspires.ftc.teamcode.component;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeLift {

    Servo slide;

    public void init(HardwareMap hardwareMap){
        slide = hardwareMap.get(Servo.class, "horiz");
    }

//    public void setPower(double power){
//        if ((power < 0 && getPosition()[0] > 300)|| (power > 0 && getPosition()[0] < 7000)) {
//            rightSlides.setPower(power);
//            leftSlides.setPower(-power);
//        }
//        else {
//            rightSlides.setPower(0);
//            leftSlides.setPower(0);
//        }
//    }
//
//    public double[] getPosition(){
//        return new double[]{leftSlides.getCurrentPosition(), rightSlides.getCurrentPosition()};
//    }

    public void setPosition(double targetPos){
        slide.setPosition(targetPos);
    }
    public double getPosition(){
        return slide.getPosition();
    }
}
