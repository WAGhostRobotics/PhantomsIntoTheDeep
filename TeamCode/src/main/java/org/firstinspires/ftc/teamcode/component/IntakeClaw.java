package org.firstinspires.ftc.teamcode.component;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeClaw {

    private Servo claw;
    private Servo diffy1;
    private Servo diffy2;
    private double rate = 0.04;
    
    public void init(HardwareMap hardwareMap){
        claw = hardwareMap.get(Servo.class, "inclaw");
        diffy1 = hardwareMap.get(Servo.class, "indiffy1");
        diffy2 = hardwareMap.get(Servo.class, "indiffy2");
    }

    public void open(){
        claw.setPosition(1);
    }

    public void close(){
        claw.setPosition(0);
    }

    public double getClawPos(){
        return claw.getPosition();
    }

    public void setClawRot(double pos1, double pos2){
        diffy1.setPosition(pos1);
        diffy2.setPosition(pos2);
    }

//    public void spinLeft() {
//        if (degOfFreedom.getPosition() < 0.85) {
//            degOfFreedom.setPosition(degOfFreedom.getPosition() + rate);
//        }
//    }

//    public void spinRight(){degOfFreedom.setPosition(degOfFreedom.getPosition()-rate);}
//
//    public void setDOFPosition(double position){
//        degOfFreedom.setPosition(position);
//    }
//
//    public double getDOFPosition(){return degOfFreedom.getPosition();}

}
