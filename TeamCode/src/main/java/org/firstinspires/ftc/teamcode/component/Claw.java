package org.firstinspires.ftc.teamcode.component;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * This file will represent the claw you will create and all of its functions.
 */

public class Claw {

    Servo claw;
    
    public void init(HardwareMap hardwareMap){
        claw = hardwareMap.get(Servo.class, "claw");
    }

    public void open(){
        claw.setPosition(1);
    }

    public void close(){
        claw.setPosition(0);
    }

}
