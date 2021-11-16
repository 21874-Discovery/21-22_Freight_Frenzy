package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous(name = "PgmBrdAuto_New", group = "team")

public class NewPgmBrdAuto extends LinearOpMode {
    //define motors
    ColorSensor RubberDuck; //left
    DcMotor Large;
    DcMotor Small;
    DcMotor Rev;
    Servo Pass;
    ColorSensor sensorColor;
    //define variables
    int currentstep = 0;
    String barcode = "none";
    public void runOpMode() {
        //define hardware map
        Large = hardwareMap.dcMotor.get("LM"); //port 3 Andy Mark
        Large.setDirection(DcMotorSimple.Direction.FORWARD);
        Small = hardwareMap.dcMotor.get("SM"); //port 0
        Small.setDirection(DcMotorSimple.Direction.FORWARD);
        Rev = hardwareMap.dcMotor.get("HM"); //port 2
        Rev.setDirection(DcMotorSimple.Direction.FORWARD);
        Pass = hardwareMap.servo.get("S"); // medium port 3
        Pass.setDirection(Servo.Direction.FORWARD);
        sensorColor = hardwareMap.get(ColorSensor.class, "RD");
        waitForStart();
        while (opModeIsActive()) {
            if (currentstep == 0) {
                currentstep++;
            }

            if (currentstep == 1) {
                telemetry.addData("inside currentstep:", currentstep);
                telemetry.update();
//This is the autonomous code.

                //Move Forward 1.5 squares


                //Use LM
                Large.setPower(0.5);
                sleep(1000);
                Large.setPower(0);
                currentstep++;
            }
            if (currentstep == 2) {
                telemetry.addData("inside currentstep:", currentstep);
                telemetry.update();

                //Read barcode, choose left/center/right
                //Duck Scanner 1 left side
                //Duck Scanner 2 right side
                //Use SM
                Small.setPower(0.5);
                sleep(1000);
                Small.setPower(0);
                currentstep++;
            }
            if (currentstep == 3) {
                telemetry.addData("inside currentstep:", currentstep);
                telemetry.update();
                //Use HM
                Rev.setPower(0.5);
                sleep(1000);
                Rev.setPower(0);
                currentstep++;
            }
            if (currentstep == 4) {
                telemetry.addData("inside currentstep:", currentstep);
                telemetry.update();
                //Use Servo
                Pass.setPosition(90);
                sleep(1000);
                Pass.setPosition(0);
                currentstep++;
                if (currentstep == 5) {
                    sleep(1000);
                    telemetry.addData("Red Value ", sensorColor.red());
                    telemetry.addData("Blue Value ", sensorColor.blue());
                    telemetry.update();
                    sleep(2000);
                }
            }
        }
/*
        double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
        double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
        double rightX = gamepad1.right_stick_x;
        final double v1 = r * Math.cos(robotAngle) + rightX;
        final double v2 = r * Math.sin(robotAngle) - rightX;
        final double v3 = r * Math.sin(robotAngle) + rightX;
        final double v4 = r * Math.cos(robotAngle) - rightX;

        leftFront.setPower(v1);
        rightFront.setPower(v2);
        leftRear.setPower(v3);
        rightRear.setPower(v4);*/
    }
}