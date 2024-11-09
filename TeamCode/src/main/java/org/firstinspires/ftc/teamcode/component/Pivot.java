package org.firstinspires.ftc.teamcode.component;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Pivot {

    DcMotor leftPivot;
    DcMotor rightPivot;

    Servo lock;

    public void init(HardwareMap hardwareMap){
        leftPivot = hardwareMap.get(DcMotor.class, "leftPivot");
        rightPivot = hardwareMap.get(DcMotor.class, "rightPivot");

        lock = hardwareMap.get(Servo.class, "lock");

        leftPivot.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightPivot.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftPivot.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightPivot.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void setPower(double power){
        if(Math.abs(power)>0.1) {
            this.unlock();
            rightPivot.setPower(power);
            leftPivot.setPower(power);
        }
        else{
            this.lock();
        }
    }

    public double[] getPosition(){
        return new double[]{leftPivot.getCurrentPosition(), rightPivot.getCurrentPosition()};
    }

    public void lock(){
        lock.setPosition(1);//TODO: TUNE
    }

    public void unlock(){
        lock.setPosition(0);//TODO: TUNE
    }

//    public double kStatic() {
//        double pwr = 0;
//        if (this.getPosition()[0] > 850){
//            this.setPower(0.2);
//            pwr = 0.2;
//        }
//        else if (this.getPosition()[0] > 500){
//            this.setPower(0.1);
//            pwr = 0.1;
//        }
//        else if (this.getPosition()[0] > 300){
//            this.setPower(-0.1);
//            pwr = -0.1;
//        }
//        else if (this.getPosition()[0] > 0){
//            this.setPower(-0.2);
//            pwr = -0.2;
//        }
//        return pwr;
//    }



}
