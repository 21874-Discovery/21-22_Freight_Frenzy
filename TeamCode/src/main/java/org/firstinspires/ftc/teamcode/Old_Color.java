package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by ftcteam on 10/24/17.
 */

@Autonomous(name = "whobot_b1", group= "team")
public class Old_Color extends LinearOpMode {

    static int POSITION_ONE = 360;

    // Defines the hardware.

    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor clawMotor;
    DcMotor liftMotor;
    Servo tailServo;
    Servo elbowservo;
    ColorSensor sensorColor;

    // Sets base speed for the hardware.

    double currentSpeed = 1.0;
    double leftMotorPower = 1.0;
    double rightMotorPower = 1.0;

    double clawArm = 0.75;
    double clawArmPower = 0.75;

    // hsvValues is an array that will hold the hue, saturation, and value information.

    float hsvValues[] = {0F, 0F, 0F};

    // values is a reference to the hsvValues array.

    final float values[] = hsvValues;

    // sometimes it helps to multiply the raw RGB values with a scale factor
    // to amplify/attenuate the measured values.

    final double SCALE_FACTOR = 255;

    int currentStep = 1;
    final double PI = Math.PI;

    boolean foundRed = false;
    boolean foundBlue = false;

    public void runOpMode(){

        tailServo= hardwareMap.servo.get("tailServo");

        /*
         * Set motors to use encoders
         *
         *  1440 encoders ticks/units is equal to one full motor rotation or 360 degrees
         *
         *  Alternatively, one encoder tick/unit is equal to 1/4 of a degree.
         */

        // Initilizes the hardware.

        leftMotor = hardwareMap.dcMotor.get("leftMotor");
        leftMotor.setDirection(DcMotor.Direction.FORWARD);
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        rightMotor = hardwareMap.dcMotor.get("rightMotor");
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        clawMotor = hardwareMap.dcMotor.get("clawMotor");
        clawMotor.setDirection(DcMotor.Direction.FORWARD);

        liftMotor = hardwareMap.dcMotor.get ("liftMotor");
        liftMotor.setDirection(DcMotor.Direction.FORWARD);

        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        sensorColor = hardwareMap.get(ColorSensor.class, "colorSensor");


        tailServo= hardwareMap.servo.get("tailServo");
        tailServo.setDirection(Servo.Direction.FORWARD);
        tailServo.setPosition(1.0);

        elbowservo= hardwareMap.servo.get("elbowservo");
        elbowservo.setDirection(Servo.Direction.FORWARD);
        elbowservo.setPosition(0.0);


        waitForStart();

        while(!isStopRequested() ){

            telemetry.addData("Current step ", currentStep);
            telemetry.addData("Left Motor position ", leftMotor.getCurrentPosition());
            telemetry.addData("Right Motor position ", rightMotor.getCurrentPosition());
            telemetry.update();
            if(currentStep == 1){

                // Moves the tail to starting position (Up).

                tailServo.setPosition(1.0);
                elbowservo.setPosition(0.45);
                currentStep=3;
            }

            //Moves the robot to position to lower tail.

           /* else if(currentStep == 2) {
                leftMotor.setPower(0.25);
                rightMotor.setPower(0.25);

                if ((leftMotor.getCurrentPosition() >= POSITION_ONE) && (rightMotor.getCurrentPosition() >= POSITION_ONE)) {
                    leftMotor.setPower(0.0);
                    rightMotor.setPower(0.0);

                    leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                    leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

                    currentStep++;
                }
            }
            */

            //Lowers tail.

            else if(currentStep == 3) {
                sleep(1000);
                tailServo.setPosition(0.45);
                currentStep++;
            }

            //Detects color of the Jewel, then turns left if the Jewel is blue, and turns right if the Jewel is red.

            else if (currentStep == 4) {
                sleep(1000);
                telemetry.addData("Red Value ", sensorColor.red());
                telemetry.addData("Blue Value ", sensorColor.blue());
                telemetry.update();
                sleep(2000);
                if (sensorColor.blue() > sensorColor.red())
                {
                    //telemetry.addData("Color is Red: ", sensorColor.red());
                    //telemetry.addData("Turn Robot ", "right");

                    leftMotor.setPower(-0.1);
                    rightMotor.setPower(0.1);
                    foundRed = true;
                }
                else {
                    //telemetry.addData("Color is Blue: ", sensorColor.blue());
                    //telemetry.addData("Turn Robot ", "left");

                    leftMotor.setPower(0.1);
                    rightMotor.setPower(-0.1);
                    foundBlue = true;
                }
                currentStep++;

            }

            //Stops the robot.

            else if (currentStep == 5) {

                if (foundRed) {
                    if (rightMotor.getCurrentPosition() >= 1600) {
                        leftMotor.setPower(0.0);
                        rightMotor.setPower(0.0);
                        currentStep++;
                    }
                }
                if (foundBlue) {
                    if (leftMotor.getCurrentPosition() >= 1600) {
                        leftMotor.setPower(0.0);
                        rightMotor.setPower(0.0);
                        currentStep++;
                    }
                }
            }

            //Moves the tail up.

            else if (currentStep == 6) {
                tailServo.setPosition(1.0);
                elbowservo.setPosition(0.0);
                currentStep++;
            }

            //Moves the robot forward or backwards, based on the color of the jewel.

            else if (currentStep == 7) {
                sleep(1000);
                if (foundRed) {
                    leftMotor.setPower(0.25);
                    rightMotor.setPower(0.25);
                }
                if (foundBlue) {
                    leftMotor.setPower(-0.25);
                    rightMotor.setPower(-0.25);
                }
                currentStep++;
            }

            //Stops the robot.

            else if (currentStep == 8) {
                if (foundRed) {
                    if (rightMotor.getCurrentPosition() >= 3200) {
                        leftMotor.setPower(0.0);
                        rightMotor.setPower(0.0);
                        currentStep++;
                    }
                }
                if (foundBlue) {
                    if (leftMotor.getCurrentPosition() >= 3200) {
                        leftMotor.setPower(0.0);
                        rightMotor.setPower(0.0);
                        currentStep++;
                    }
                }
            }
            telemetry.update();
        }
    }
}
