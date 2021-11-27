package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;


@Autonomous(name = "FY21Auto", group = "team")

public class FY21Autonomous extends LinearOpMode {
    //define motors and stuff
    DcMotor topRight;
    DcMotor bottomRight;
    DcMotor topLeft;
    DcMotor bottomLeft;
    ColorSensor duckScanner1; //left
    ColorSensor duckScanner2; //right
    ColorSensor ColorSensor;
    //define variables
    int currentstep = 0;
    String barcode = "none";

    public void runOpMode() {
        //define hardware map
        duckScanner1 = hardwareMap.colorSensor.get("DS1");
        duckScanner2 = hardwareMap.colorSensor.get("DS2");
        topRight = hardwareMap.dcMotor.get("TR"); //port 0
        bottomRight = hardwareMap.dcMotor.get("BR"); //port 1
        topLeft = hardwareMap.dcMotor.get("TL"); //port 2
        bottomLeft = hardwareMap.dcMotor.get("BL"); //port 3
        waitForStart();
        while (opModeIsActive()) {

            if (currentstep == 0) {
                currentstep++;
            }

            if (currentstep == 1) {
                telemetry.addData("inside currentstep:", currentstep);
                telemetry.update();
                //Move Forward 0.5
                //turn 90 degrees
                currentstep++;
            }
            if (currentstep == 2) {

                //Duck Scanner 1 left side
                //Duck Scanner 2 right side
                //MOVE, SCAN, MOVE, SCAN, MOVE, SCAN

                //if duckScanner1 = yellow and duckScanner2 <> yellow set barcode=left
                //if duckScanner1 <> yellow and duckScanner2 = yellow set barcode=right
                //if duckScanner1 <> yellow and duckScanner2 <> yellow set barcode=center
                if (barcode.equals("left")) {
                    //if barcode=left then
                    //move forward 1 square
                    //slide right 1 square
                    //place freight on bottom rack
                }

                if (barcode.equals("right")) {
                    //if barcode=left then
                    //slide left 1 square
                    //move forward 1 square
                    //slide right 1 square
                    //rotate 90deg clockwise
                    //place freight on top rack
                }

                if (barcode.equals("center")) {
                    //if barcode=left then
                    //slide left 1 square
                    //move forward 1 square
                    //rotate 90deg clockwise
                    //move forward 1 square
                    //place freight on middle rack
                }

                //move back 1.5 squares
                //slide right 2 squares
                //spin carousel
                //slide left 1 square
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


    public void DCSUPERCOLOR() {
        if (duckScanner1.red() > 72 && duckScanner1.red() < 118) {
            if (duckScanner1.blue() > 68 && duckScanner1.blue() < 95) {
                if (duckScanner1.green() > 101 && duckScanner1.green() < 167) {
                    //the duck is on this spot
                }

            }
        }
    }

    public void Mechnum_drive() {


        double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
        double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
        double rightX = gamepad1.right_stick_x;
        final double v1 = r * Math.cos(robotAngle) + rightX;
        final double v2 = r * Math.sin(robotAngle) - rightX;
        final double v3 = r * Math.sin(robotAngle) + rightX;
        final double v4 = r * Math.cos(robotAngle) - rightX;

        topRight.setPower(v1);
        bottomRight.setPower(v2);
        topLeft.setPower(v3);
        bottomLeft.setPower(v4);
    }
}