package org.firstinspires.ftc.teamcode.component;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.concurrent.TimeUnit;

public class Pivot {

    private DcMotor leftPivot;
    private DcMotor rightPivot;

    private Servo lock;

    private int limit = 750;

    public boolean disengage = true;

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
        if((power>0 || this.getPosition()[0]<limit) && Math.abs(power)>0.1) {
            this.unlock();
            try{
                Thread.sleep(100);
            }
            catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }

            if(power>0) {
                rightPivot.setPower(power);
                leftPivot.setPower(-power);
            }
            else if (this.getPosition()[0]>300){
                rightPivot.setPower(0);
                leftPivot.setPower(0);
            }
            else{
                rightPivot.setPower(power);
                leftPivot.setPower(-power);
            }
        }
        else{
            this.lock();
            rightPivot.setPower(0);
            leftPivot.setPower(0);
            disengage = false;
        }
    }

    public double[] getPosition(){
        return new double[]{leftPivot.getCurrentPosition(), rightPivot.getCurrentPosition()};
    }

    public void lock(){
        lock.setPosition(0.28);
    }

    public void unlock(){
        lock.setPosition(0.2);
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
