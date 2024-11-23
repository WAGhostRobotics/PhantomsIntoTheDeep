package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.core.Wojcik;
import org.firstinspires.ftc.teamcode.library.DriveStyle;
import org.firstinspires.ftc.teamcode.library.DriverOrientedControl;
import org.firstinspires.ftc.teamcode.library.MecanumDrive;

@TeleOp(name = "TeleOpBoomer") // the name is what shows up on your phone/driver hub
public class TeleOpParent extends LinearOpMode {

//    DriverOrientedControl drive;
    public double movementPwr = 1;
    DriveStyle.DriveType type = DriveStyle.DriveType.MECANUMARCADE;

    boolean lastClawChange = false;
    boolean clawOpen = true;

    @Override
    public void runOpMode() throws InterruptedException{

        Wojcik.init(hardwareMap, true);

        Wojcik.claw.open();

        Wojcik.pivot.lock();

        waitForStart();

        MecanumDrive drive = new MecanumDrive(hardwareMap);

        telemetry.addData("Pos L", Wojcik.lift.getPosition()[0]);

//        DriverOrientedControl drive = new DriverOrientedControl()
        //pass args and motors

        while (opModeIsActive()) {

            if(gamepad2.a && !lastClawChange){
                if(clawOpen){
                    Wojcik.claw.close();
                }
                else if(!clawOpen){
                    Wojcik.claw.open();
                }
                clawOpen = !clawOpen;
            }

            lastClawChange = gamepad2.a;

            if(gamepad1.left_trigger>0.1){
                movementPwr = 0.25;
            }
            else{
                movementPwr = 1;
            }
            if(gamepad2.dpad_left){
                Wojcik.claw.spinLeft();
            }

            if(gamepad2.dpad_right){
                Wojcik.claw.spinRight();
            }

            if(gamepad2.y){
                Wojcik.lift.stabilizeAscension();
            }
            if(gamepad2.x){
                Wojcik.lift.stopAscension();
            }

            if(gamepad2.right_trigger-gamepad2.left_trigger<0 || Wojcik.checkExtension()) {
                Wojcik.lift.setPower(gamepad2.right_trigger - gamepad2.left_trigger);
            }
            else{
                Wojcik.lift.setPower(-1);
            }

            Wojcik.pivot.setPower(gamepad2.right_stick_y*-0.5);

            double driveTurn = Math.pow(gamepad1.right_stick_x, 3); //change to minus if broken
            double driveY = Math.pow(gamepad1.left_stick_x, 3);
            double driveX = Math.pow(gamepad1.left_stick_y, 3);
            drive.drive(Math.hypot(driveX, driveY), Math.toDegrees(Math.atan2(driveY, driveX)), driveTurn, movementPwr);
            //Use driverOrientedControl.drive passing gamepad1 and movementPwr as args

            telemetry.addData("Pos L", Wojcik.lift.getPosition()[0]);
            telemetry.addData("Pos R", Wojcik.lift.getPosition()[1]);

            telemetry.addData("Pos Piv L", Wojcik.pivot.getPosition()[0]);
            telemetry.addData("Pos Piv R", Wojcik.pivot.getPosition()[1]);

//            telemetry.addData("Piv Disengage", gamepad2.right_stick_y*0.5>0 || Wojcik.pivot.getPosition()[0]<715);

            telemetry.addData("PivMult", Math.min((900-Wojcik.pivot.getPosition()[0])/400,1));
            telemetry.update();
        }
    }
}