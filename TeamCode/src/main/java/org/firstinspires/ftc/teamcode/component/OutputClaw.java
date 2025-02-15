package org.firstinspires.ftc.teamcode.component;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class OutputClaw {

    private Servo claw;
    private Servo degOfFreedom;
    private Servo dofArm;

    private double rate = 0.005;
    
    public void init(HardwareMap hardwareMap){
        claw = hardwareMap.get(Servo.class, "outclaw");
        degOfFreedom = hardwareMap.get(Servo.class, "outdof");
        dofArm = hardwareMap.get(Servo.class, "outarm");
    }

    public void open(){
        claw.setPosition(1);
    }

    public void close(){
        claw.setPosition(0);
    }

    public void turnUp() {
            degOfFreedom.setPosition(degOfFreedom.getPosition() +0.5* rate);
    }

    public void turnDown(){
        degOfFreedom.setPosition(degOfFreedom.getPosition()-0.5*rate);
    }

    public void setDofPos(double pos){
        degOfFreedom.setPosition(pos);
    }

    public void armUp(){
        dofArm.setPosition(dofArm.getPosition() - 0.25*rate);
    }

    public void armDown(){
        dofArm.setPosition(dofArm.getPosition() + 0.25*rate);
    }

    public void setArmPos(double pos){
        dofArm.setPosition(pos);
    }

    public double getClawPos(){return claw.getPosition();}

    public double getArmPos(){
        return dofArm.getPosition();
    }

    public double getDOFPosition(){return degOfFreedom.getPosition();}

}
