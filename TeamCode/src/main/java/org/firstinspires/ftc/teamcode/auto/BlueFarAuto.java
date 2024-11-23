package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class BlueFarAuto extends AutoParent{

    @Override
    public void runOpMode() throws InterruptedException {
        super.red = false;
        super.bucket = false;
        super.runOpMode();
    }
}