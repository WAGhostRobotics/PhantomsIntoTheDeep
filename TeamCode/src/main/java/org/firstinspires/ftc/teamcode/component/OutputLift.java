package org.firstinspires.ftc.teamcode.component;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class OutputLift {

    DcMotor leftSlides;
    DcMotor rightSlides;

    public void init(HardwareMap hardwareMap){
        leftSlides = hardwareMap.get(DcMotor.class, "leftVert");
        rightSlides = hardwareMap.get(DcMotor.class, "rightVert");

        leftSlides.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightSlides.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

//        leftSlides.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        rightSlides.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void setPower(double power){
        if ((power < 0 && getPosition()[0] > 300)|| (power > 0 && getPosition()[0] < 7000)) {
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
