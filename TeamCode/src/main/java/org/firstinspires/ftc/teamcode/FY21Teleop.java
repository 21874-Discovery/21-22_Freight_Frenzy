package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "FY21TeleOp", group = "TeleOp")
public class FY21Teleop extends LinearOpMode {
    DcMotor topRight;
    DcMotor bottomRight;
    DcMotor topLeft;
    DcMotor bottomLeft;
    DcMotor carouselSpinner;
    DcMotor linearSlide;
    DcMotor spindle;
    Servo emergencyFlap;

    double speed = 1;
    double carouselSpeed = 0;
    double emergencyActive = 0;


    @Override
    public void runOpMode() throws InterruptedException {
        //hardware maps
        topRight = hardwareMap.dcMotor.get("TR"); // control hub port 0
        bottomRight = hardwareMap.dcMotor.get("BR"); //control hub port 1
        topLeft = hardwareMap.dcMotor.get("TL"); //control hub port 2
        bottomLeft = hardwareMap.dcMotor.get("BL"); //control hub port 3
        linearSlide = hardwareMap.dcMotor.get("LS"); //expansion hub port 0
        spindle = hardwareMap.dcMotor.get("SM"); //expansion hub port 1
        carouselSpinner = hardwareMap.dcMotor.get("CS"); //expansion hub port 2
        emergencyFlap = hardwareMap.servo.get("EF"); //expansion hub servo port 0


        emergencyFlap.setPosition(0);
        waitForStart();
        while (opModeIsActive()) {
            telemetry.addLine("Tele-Op Controls:");
            telemetry.addLine("Gamepad 1:");
            telemetry.addLine("Left Joystick: Drive/Strafe");
            telemetry.addLine("Right Joystick: Turn");
            telemetry.addLine("Right Bumper: Slow Down (Reverts when released)");
            telemetry.addLine("All 4 buttons: Emergency Pusher Toggle (3 second cooldown)");
            telemetry.addLine("Gamepad 2:");
            telemetry.addLine("Right Joystick: Linear Slide");
            telemetry.addLine("X-Button: Spin Carousel (Blue)");
            telemetry.addLine("B-Button: Spin Carousel (Red)");
            telemetry.addLine("Left Trigger: Take Freight (Spindle)");
            telemetry.addLine("Right Trigger: Drop Freight (Spindle)");
            telemetry.addLine("Please note that Spindle controls will cancel each other out if held at the same time. They, alongside the linear slide and drive controls, are also determined by force.");
            telemetry.update();
            if (gamepad1.right_bumper) { //when held, will slow the robot down for precise driving.
                speed = 0.25;
            }
            if (!gamepad1.right_bumper) { //when released, speed will be brought back to 1. Exclamation marks are "not" functions.
                speed = 1;
            }

            if (gamepad2.x && !gamepad2.b) {
                carouselSpeed = -0.75;
            }
            if (!gamepad2.x && gamepad2.b) {
                carouselSpeed = 0.75;
            }
            if (!gamepad2.x && !gamepad2.b || gamepad2.x && gamepad2.b) {
                carouselSpeed = 0;
            }

            //EMERGENCY CASE ONLY
            if (gamepad1.b) {
                if (emergencyActive == 0) {
                    emergencyFlap.setPosition(2);
                    emergencyActive = 1;
                    sleep (3000);
                }
                else {
                    emergencyFlap.setPosition(0);
                    emergencyActive = 0;
                    sleep (3000);
                }
            }

            float gamepad1LeftY = -gamepad1.left_stick_x;        // Sets the gamepads left sticks y position to a float so that we can easily track the stick
            float gamepad1LeftX = gamepad1.left_stick_y;       // Sets the gamepads left sticks x position to a float so that we can easily track the stick
            float gamepad1RightX = gamepad1.right_stick_x;     // Sets the gamepads right sticks x position to a float so that we can easily track the stick
            float gamepad2RightY = gamepad2.right_stick_y;     // Sets the 2nd gamepads right sticks x position to a float so that we can easily track the stick
            float gamepad2LTrigger = gamepad2.left_trigger;     // Sets the 2nd gamepads left trigger pushdown strength to a float
            float gamepad2RTrigger = gamepad2.right_trigger;     // Sets the 2nd gamepads left trigger pushdown strength to a float

            // Mechenum formulas
            double TopRightSpeed = gamepad1LeftY + gamepad1LeftX + gamepad1RightX;     // Combines the inputs of the sticks to clip their output to a value between 1 and -1
            double TopLeftSpeed = -gamepad1LeftY + gamepad1LeftX + gamepad1RightX;     // Combines the inputs of the sticks to clip their output to a value between 1 and -1
            double BottomRightSpeed = gamepad1LeftY - gamepad1LeftX + gamepad1RightX;      // Combines the inputs of the sticks to clip their output to a value between 1 and -1
            double BottomLeftSpeed = -gamepad1LeftY - gamepad1LeftX + gamepad1RightX;      // Combines the inputs of the sticks to clip their output to a value between 1 and -1

            // sets speed
            double topLeftCorrectedSpeed = Range.clip(Math.pow(TopRightSpeed, 3), -speed, speed);    // Slows down the motor and sets its max/min speed to the double "speed"
            double topRightCorrectedSpeed = Range.clip(Math.pow(TopLeftSpeed, 3), -speed, speed);      // Slows down the motor and sets its max/min speed to the double "speed"
            double bottomLeftCorrectedSpeed = Range.clip(Math.pow(BottomRightSpeed, 3), -speed, speed);      // Slows down the motor and sets its max/min speed to the double "speed"
            double bottomRightCorrectedSpeed = Range.clip(Math.pow(BottomLeftSpeed, 3), -speed, speed);        // Slows down the motor and sets its max/min speed to the double "speed"
            double linearSpeed = -gamepad2RightY;
            double spindleSpeed = gamepad2RTrigger - gamepad2LTrigger;

            topRight.setPower(topRightCorrectedSpeed);
            bottomRight.setPower(bottomRightCorrectedSpeed);
            topLeft.setPower(topLeftCorrectedSpeed);
            bottomLeft.setPower(bottomLeftCorrectedSpeed);
            linearSlide.setPower(linearSpeed);
            spindle.setPower(spindleSpeed);
            carouselSpinner.setPower(carouselSpeed);
        }
    }
}