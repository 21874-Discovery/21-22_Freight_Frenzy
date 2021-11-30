package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@Autonomous(name = "FY21Auto", group = "team")

public class FY21Autonomous extends LinearOpMode {
    //define motors and stuff
    DcMotor topRight;
    DcMotor bottomRight;
    DcMotor topLeft;
    DcMotor bottomLeft;
    ColorSensor duckScannerLeft; //left
    ColorSensor duckScannerRight; //right
    //ColorSensor ColorSensor;
    //define variables
    int currentstep = 0;
    String barcode = "none";

    public void runOpMode() {
        //define hardware map
        duckScannerLeft = hardwareMap.colorSensor.get("DSL"); //Extension Hub I2C bus 3
        duckScannerRight = hardwareMap.colorSensor.get("DSR"); //Control Hub I2C bus 3
        topRight = hardwareMap.dcMotor.get("TR"); //Control Hub Port 0
        bottomRight = hardwareMap.dcMotor.get("BR"); //Control Hub Port 1
        topLeft = hardwareMap.dcMotor.get("TL"); //Control Hub Port 2
        bottomLeft = hardwareMap.dcMotor.get("BL"); //Control Hub Port 3
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
                if (DCSUPERCOLOR(duckScannerLeft)){
                    //If duck middle
                    // slide right 1
                    //top- full arm exstention
                    //mid- half exstention
                    //bottom- lowest exstention
                }
                //movement code to slide left 1/2
                if (DCSUPERCOLOR(duckScannerLeft)){
                    //If duck left
                }
                //Slide 3/4 right
                if (DCSUPERCOLOR(duckScannerLeft)){
                    //If duck right
                }


                //Duck Scanner 1 left side
                //Duck Scanner 2 right side
                //MOVE, SCAN, MOVE, SCAN, MOVE, SCAN
                //if duckScanner1 <> yellow and duckScanner2 = yellow set barcode=right
                //if duckScanner1 = yellow and duckScanner2 <> yellow set barcode=left
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


    public boolean DCSUPERCOLOR(ColorSensor duck) {
        if (duck.red() > 72 && duck.red() < 118) {
            if (duck.blue() > 68 && duck.blue() < 95) {
                if (duck.green() > 101 && duck.green() < 167) {
                    telemetry.addData("Duck is here", duck.red());
                    telemetry.addData("Duck is here", duck.blue());
                    telemetry.addData("Duck is here", duck.green());
                    return true;
                    //the duck is on this spot

                }

            }
        }
        return false;
    }



    public void Mechanum_drive(String Dir, double speed, int distance) {

        topLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        topRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bottomLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bottomRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        switch (Dir){
            case "Forward":
                topLeft.setDirection(DcMotorSimple.Direction.FORWARD);
                topRight.setDirection(DcMotorSimple.Direction.REVERSE);
                bottomLeft.setDirection(DcMotorSimple.Direction.FORWARD);
                bottomRight.setDirection(DcMotorSimple.Direction.REVERSE);
                break;
            case "Backward":
                topLeft.setDirection(DcMotorSimple.Direction.REVERSE);
                topRight.setDirection(DcMotorSimple.Direction.FORWARD);
                bottomLeft.setDirection(DcMotorSimple.Direction.REVERSE);
                bottomRight.setDirection(DcMotorSimple.Direction.FORWARD);
                break;
            case "Left":
                topLeft.setDirection(DcMotorSimple.Direction.REVERSE);
                topRight.setDirection(DcMotorSimple.Direction.REVERSE);
                bottomLeft.setDirection(DcMotorSimple.Direction.FORWARD);
                bottomRight.setDirection(DcMotorSimple.Direction.FORWARD);
                break;
            case "Right":
                topLeft.setDirection(DcMotorSimple.Direction.FORWARD);
                topRight.setDirection(DcMotorSimple.Direction.FORWARD);
                bottomLeft.setDirection(DcMotorSimple.Direction.REVERSE);
                bottomRight.setDirection(DcMotorSimple.Direction.REVERSE);
                break;
        }
    }

}