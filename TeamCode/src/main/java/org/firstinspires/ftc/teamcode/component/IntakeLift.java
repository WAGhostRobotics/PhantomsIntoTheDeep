package org.firstinspires.ftc.teamcode.component;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeLift {

    Servo slide;

    public void init(HardwareMap hardwareMap){
        slide = hardwareMap.get(Servo.class, "horiz");
    }

    public void moveOut(){
        slide.setPosition(slide.getPosition()-0.0025);
    }

    public void moveIn(){
        slide.setPosition(slide.getPosition()+0.0025);
    }

    public void setPosition(double targetPos){
        slide.setPosition(targetPos);
    }
    public double getPosition(){
        return slide.getPosition();
    }
}
