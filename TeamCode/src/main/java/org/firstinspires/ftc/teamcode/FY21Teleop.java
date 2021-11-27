package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "FY21 TeleOp", group = "TeleOp")
public class FY21Teleop extends LinearOpMode {
    DcMotor topRight;
    DcMotor bottomRight;
    DcMotor topLeft;
    DcMotor bottomLeft;
    DcMotor carouselSpinner;

    double speed = 1;
    double carouselSpeed = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        //Engineers using REV CoreHex motors for "spinney box" and Linear Slide
        //hardware maps
        topRight = hardwareMap.dcMotor.get("TR"); // control hub port 0
        bottomRight = hardwareMap.dcMotor.get("BR"); //control hub port 1
        topLeft = hardwareMap.dcMotor.get("TL"); //control hub port 2
        bottomLeft = hardwareMap.dcMotor.get("BL"); //control hub port 3
        carouselSpinner = hardwareMap.dcMotor.get("CS"); //expansion hub port 2
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.right_bumper) { //when held, will slow the robot down for precise driving.
                speed = 0.25;
            }
            if (!gamepad1.right_bumper) { //when released, speed will be brought back to 1. Exclamation marks are "not" functions.
                speed = 1;
            }
            if (gamepad1.x) { //when held, will spin the carousel spinner.
                carouselSpeed = 1;
            }
            if (!gamepad1.x) { //when released, will stop spinning the carousel spinner.
                carouselSpeed = 0;
            }

            /*double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = gamepad1.right_stick_x;
            final double v1 = r * Math.cos(robotAngle) + rightX;
            final double v2 = r * Math.sin(robotAngle) - rightX;
            final double v3 = r * Math.sin(robotAngle) + rightX;
            final double v4 = r * Math.cos(robotAngle) - rightX;

            topRight.setPower(v1);
            bottomRight.setPower(v2);
            topLeft.setPower(v3);
            bottomLeft.setPower(v4);*/

            float gamepad1LeftY = -gamepad1.left_stick_x;        // Sets the gamepads left sticks y position to a float so that we can easily track the stick
            float gamepad1LeftX = gamepad1.left_stick_y;       // Sets the gamepads left sticks x position to a float so that we can easily track the stick
            float gamepad1RightX = gamepad1.right_stick_x;     // Sets the gamepads right sticks x position to a float so that we can easily track the stick

            // Mechanum formulas
            double TopRightSpeed = gamepad1LeftY + gamepad1LeftX + gamepad1RightX;     // Combines the inputs of the sticks to clip their output to a value between 1 and -1
            double TopLeftSpeed = -gamepad1LeftY + gamepad1LeftX + gamepad1RightX;     // Combines the inputs of the sticks to clip their output to a value between 1 and -1
            double BottomRightSpeed = gamepad1LeftY - gamepad1LeftX + gamepad1RightX;      // Combines the inputs of the sticks to clip their output to a value between 1 and -1
            double BottomLeftSpeed = -gamepad1LeftY - gamepad1LeftX + gamepad1RightX;      // Combines the inputs of the sticks to clip their output to a value between 1 and -1

            // sets speed
            double topLeftCorrectedSpeed = Range.clip(Math.pow(TopRightSpeed, 3), -speed, speed);    // Slows down the motor and sets its max/min speed to the double "speed"
            double topRightCorrectedSpeed = Range.clip(Math.pow(TopLeftSpeed, 3), -speed, speed);      // Slows down the motor and sets its max/min speed to the double "speed"
            double bottomLeftCorrectedSpeed = Range.clip(Math.pow(BottomRightSpeed, 3), -speed, speed);      // Slows down the motor and sets its max/min speed to the double "speed"
            double bottomRightCorrectedSpeed = Range.clip(Math.pow(BottomLeftSpeed, 3), -speed, speed);        // Slows down the motor and sets its max/min speed to the double "speed"

            topRight.setPower(topRightCorrectedSpeed);
            bottomRight.setPower(bottomRightCorrectedSpeed);
            topLeft.setPower(topLeftCorrectedSpeed);
            bottomLeft.setPower(bottomLeftCorrectedSpeed);
            carouselSpinner.setPower(carouselSpeed);
        }
    }
}