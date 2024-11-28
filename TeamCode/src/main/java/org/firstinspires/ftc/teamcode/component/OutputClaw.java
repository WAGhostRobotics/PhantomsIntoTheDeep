package org.firstinspires.ftc.teamcode.component;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class OutputClaw {

    private Servo claw;
    private Servo degOfFreedom;
    private double rate = 0.04;
    
    public void init(HardwareMap hardwareMap){
        claw = hardwareMap.get(Servo.class, "outclaw");
        degOfFreedom = hardwareMap.get(Servo.class, "outdof");
    }

    public void open(){
        claw.setPosition(1);
    }

    public void close(){
        claw.setPosition(0);
    }

    public void turnUp() {
        if (degOfFreedom.getPosition() < 0.85) {
            degOfFreedom.setPosition(degOfFreedom.getPosition() + rate);
        }
    }

    public void turnDown(){degOfFreedom.setPosition(degOfFreedom.getPosition()-rate);}

    public double getDOFPosition(){return degOfFreedom.getPosition();}

}
