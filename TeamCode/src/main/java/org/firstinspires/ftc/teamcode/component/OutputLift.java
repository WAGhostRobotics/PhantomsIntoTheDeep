package org.firstinspires.ftc.teamcode.component;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

public class OutputLift {

    DcMotor leftSlides;
    DcMotor rightSlides;

    PIDController armController = new PIDController(0.00225, 0.001, 0);
    double error = 0;

    public void init(HardwareMap hardwareMap){
        leftSlides = hardwareMap.get(DcMotor.class, "leftVert");
        rightSlides = hardwareMap.get(DcMotor.class, "rightVert");

        leftSlides.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightSlides.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftSlides.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSlides.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void setPosition(double targetPos){
        armController.setPID(0.00225, 0.001, 0);

        error = targetPos-(leftSlides.getCurrentPosition());

        leftSlides.setPower( Range.clip(armController.calculate(0, error), -1, 1));
        rightSlides.setPower( Range.clip(armController.calculate(0, -error), -1, 1));
    }

    public void setPower(double power){
        leftSlides.setPower(power);
    }

    public double[] getPosition(){
        return new double[]{leftSlides.getCurrentPosition(), rightSlides.getCurrentPosition()};
    }

    public boolean atTarget(){
        return armController.atSetPoint();
    }
}
