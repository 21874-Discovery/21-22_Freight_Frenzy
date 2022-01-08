package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor.RunMode;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "PowerShotAuto1", group = "team")

public class FY20Auto extends LinearOpMode{

    DcMotor TopLeft;
    DcMotor TopRight;
    DcMotor BackRight;
    DcMotor BackLeft;
    DcMotor Launcher;
    DcMotor Pickup;
    DcMotor Angler;

    int currentstep = 0;

    public void runOpMode() {

        TopLeft = hardwareMap.dcMotor.get("TL");
        TopLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        //   TopLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        TopRight = hardwareMap.dcMotor.get("TR");
        TopRight.setDirection(DcMotorSimple.Direction.FORWARD);

        BackRight = hardwareMap.dcMotor.get("BR");
        BackRight.setDirection(DcMotorSimple.Direction.FORWARD);

        BackLeft = hardwareMap.dcMotor.get("BL");
        BackLeft.setDirection(DcMotorSimple.Direction.FORWARD);

        Launcher = hardwareMap.dcMotor.get("L");
        Launcher.setDirection(DcMotorSimple.Direction.FORWARD);

        Pickup =hardwareMap.dcMotor.get("P");
        Pickup.setDirection(DcMotorSimple.Direction.FORWARD);

        Angler = hardwareMap.dcMotor.get("A");
        Angler.setDirection(DcMotorSimple.Direction.FORWARD);

        // reset encoder counts kept by motors.
        TopLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        TopRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        BackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Launcher.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Pickup.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Angler.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // set motors to run forward for 5000 encoder counts.
        TopLeft.setTargetPosition(5000);
        TopRight.setTargetPosition(5000);
        BackLeft.setTargetPosition(5000);
        BackRight.setTargetPosition(5000);
        Launcher.setTargetPosition(5000);
        Pickup.setTargetPosition(5000);
        Angler.setTargetPosition(5000);

        waitForStart();
        while (opModeIsActive()) {

            if (currentstep == 0) {
                //Drive forward up to launch line without crossing
                TopLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                TopRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                BackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                BackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                TopLeft.setPower(0.5); TopRight.setPower(-0.5); BackLeft.setPower(0.5); BackRight.setPower(-0.5);
                //stop
                while (opModeIsActive() && TopLeft.isBusy())   //leftMotor.getCurrentPosition() < leftMotor.getTargetPosition())
                {
                    telemetry.addData("encoder-fwd-left", TopLeft.getCurrentPosition() + "  busy=" + TopLeft.isBusy());
                    telemetry.update();
                    idle();
                }
                TopLeft.setPower(0); TopRight.setPower(0); BackLeft.setPower(0); BackRight.setPower(0);
                currentstep ++;
            }

            if (currentstep == 1) {
                //Launch rings
                for (int i = 0; i < 3; i++) {
                    //reset encoder every loop
                    Launcher.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    //Launch 1 ring
                    Launcher.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    Launcher.setPower(1);
                    //stop
                    Launcher.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    Launcher.setPower(0);
                    //Move left
                    TopLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    TopRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    BackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    BackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                    TopLeft.setTargetPosition(700);
                    TopRight.setTargetPosition(700);
                    BackLeft.setTargetPosition(700);
                    BackRight.setTargetPosition(700);

                    TopLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    TopRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    BackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    BackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    TopLeft.setPower(-0.5); TopRight.setPower(-0.5); BackLeft.setPower(0.5); BackRight.setPower(0.5);
                    //stop
                    TopLeft.setPower(0); TopRight.setPower(0); BackLeft.setPower(0); BackRight.setPower(0);
                }
            }

            if (currentstep == 2) {
                //Drive onto launch line
                TopLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                TopRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                BackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                BackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                TopLeft.setTargetPosition(700);
                TopRight.setTargetPosition(700);
                BackLeft.setTargetPosition(700);
                BackRight.setTargetPosition(700);

                TopLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                TopRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                BackLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                BackRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                TopLeft.setPower(0.5); TopRight.setPower(-0.5); BackLeft.setPower(0.5); BackRight.setPower(-0.5);
                //stop
                TopLeft.setPower(0); TopRight.setPower(0); BackLeft.setPower(0); BackRight.setPower(0);
                currentstep ++;
            }
        }
    }}


