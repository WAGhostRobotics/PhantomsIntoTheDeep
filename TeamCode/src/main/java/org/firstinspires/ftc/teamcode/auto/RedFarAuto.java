package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class RedFarAuto extends AutoParent{

    @Override
    public void runOpMode() throws InterruptedException {
        super.red = true;
        super.bucket = false;
        super.runOpMode();
    }
}