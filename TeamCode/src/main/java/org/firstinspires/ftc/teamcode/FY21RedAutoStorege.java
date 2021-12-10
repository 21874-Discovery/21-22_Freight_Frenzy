package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;


@Autonomous(name = "FY21AutoStorage", group = "team")

public class FY21RedAutoStorege extends LinearOpMode {
    //define motors and stuff
    DcMotor topRight;
    DcMotor bottomRight;
    DcMotor topLeft;
    DcMotor bottomLeft;
    DcMotor carouselSpinner;
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
        carouselSpinner = hardwareMap.dcMotor.get("CS"); //Expansion Hub Port 2++
        waitForStart();
        while (opModeIsActive()) {

            if (currentstep == 0) {
                currentstep++;
            }

            if (currentstep == 1) {
                telemetry.addData("inside currentstep:", currentstep);
                telemetry.update();
                //Move Forward 0.5
                Mecanum_Drive("Forward",0.5,1000);
                //turn 90 degrees
                Mecanum_Turn("Left",1.0,90);
                currentstep++;
            }
            if (currentstep == 2) {
                if (DCSUPERCOLOR(duckScannerLeft)){
                    telemetry.addData("inside currentstep:", currentstep);
                    telemetry.update();
                    //If duck middle
                    // slide right 1
                    Mecanum_Drive("Left",1.0,2000);
                    //Drop freight
                    //top- full arm exstention
                    //mid- half exstention
                    //bottom- lowest exstention
                }
                //movement code to slide left 1/2
                Mecanum_Drive("Right",1.0,1000);
                if (DCSUPERCOLOR(duckScannerLeft)){
                    //If duck left
                }
                //Slide 3/4 right
                Mecanum_Drive("Left",1.0,1500);
                if (DCSUPERCOLOR(duckScannerLeft)){
                    //If duck right
                }


                //Duck Scanner 1 left side
                //Duck Scanner 2 right side
                //if duckScanner1 <> yellow and duckScanner2 = yellow set barcode=right
                //if duckScanner1 = yellow and duckScanner2 <> yellow set barcode=left
                //if duckScanner1 <> yellow and duckScanner2 <> yellow set barcode=center
                if (barcode.equals("Left")) {
                    //if barcode=left then
                    //move forward 1 square
                    Mecanum_Drive("Forward",1.0,2000);
                    //slide right 1 square
                    Mecanum_Drive("Left",1.0,2000);
                    //place freight on bottom rack
                }

                if (barcode.equals("right")) {
                    //if barcode=left then
                    //slide left 1 square
                    Mecanum_Drive("Right",1.0,2000);
                    //move forward 1 square
                    Mecanum_Drive("Forward",1.0,2000);
                    //slide right 1 square
                    Mecanum_Drive("Left",1.0,2000);
                    //rotate 90deg clockwise
                    Mecanum_Turn("Left",1.0,90);
                    //place freight on top rack
                }

                if (barcode.equals("center")) {
                    //if barcode=left then
                    //slide left 1 square
                    Mecanum_Drive("Right",1.0,2000);
                    //move forward 1 square
                    Mecanum_Drive("Forward",1.0,2000);
                    //rotate 90deg clockwise
                    Mecanum_Turn("Left",1.0,2000);
                    //move forward 1 square
                    Mecanum_Drive("Forward",1.0,2000);
                    //place freight on middle rack
                }

                //move back 1.5 squares
                Mecanum_Drive("Backward",1.0,1500);
                //slide right 2 squares
                Mecanum_Drive("Left",1.0,4000);
                carouselSpinner.setPower (1);
                sleep (2000);
                carouselSpinner.setPower (0);
                //slide left 1 square
                Mecanum_Drive("Right",1.0,2000);
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



    public void Mecanum_Drive(String Dir, double Spd, int Dist) {

        topLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        topRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bottomLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bottomRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        switch (Dir) {
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
        Dist = Math.abs(Dist);
        topLeft.setTargetPosition(Dist);
        topRight.setTargetPosition(Dist);
        bottomLeft.setTargetPosition(Dist);
        bottomRight.setTargetPosition(Dist);

        topLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        topRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bottomLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bottomRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        Spd = Range.clip(Spd, 0, 1);
        topLeft.setPower(Spd);
        topRight.setPower(Spd);
        bottomLeft.setPower(Spd);
        bottomRight.setPower(Spd);


        while (opModeIsActive() && topLeft.isBusy())
        //leftMotor.getCurrentPosition() < leftMotor.getTargetPosition())
        {
            telemetry.addData("encoder-fwd-left", topLeft.getCurrentPosition() + "busy=" + topLeft.isBusy());
            telemetry.addData("encoder-fwd-right", topRight.getCurrentPosition() + "busy=" + topRight.isBusy());
            telemetry.update();
            idle();
        }
        //stop
        topLeft.setPower(0);
        topRight.setPower(0);
        bottomLeft.setPower(0);
        bottomRight.setPower(0);
    }

    public void Mecanum_Turn(String DirT, double SpdT, int Deg) {
        double RobotDiameter = 20; //Max robot size is 18x18 with max diagonal width of 25.46 in)
        //Robot spins in a circle, rough diameter of robot's circle can be no more than 25.42 (diagonal)
        double RobotCircumference = RobotDiameter * 3.14;//Max circumference of Robot (d * pi) = 80 in
        double WheelSize = 4;  //diameter in inches of wheels (the engineers like 4in)
        double WheelCircumference = WheelSize*3.14; //Circumference (d * pi) of wheel (distance wheel travels for 1 rotation)
        double RotationsPerCircle = RobotCircumference/WheelCircumference;// wheel rotations to turns in complete circle

        int DriveTicks = 480;  //1 wheel rotation = DriveTicks - based on motor and gear ratio  => 1 Tetrix DC motor 60:1 revolution = 1440 encoder ticks (20:1 = 480 ticks (divide by 60/20) or 400 ticks = 1 foot)
        //DriveTicks * RotationsPerCircle = 360 degrees
        //Rotations per degree
        int TicksPerDegree = (int) Math.round((DriveTicks * RotationsPerCircle)/360);

        int Rotate = (int) Math.round(Deg * TicksPerDegree);
        telemetry.addData("Rotating", Rotate + "ticks or " + Deg + " degrees");
        telemetry.update();

        topLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        topRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bottomLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bottomRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        if (DirT.equals("Left")) {
            topLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            topRight.setDirection(DcMotorSimple.Direction.REVERSE);
            bottomLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            bottomRight.setDirection(DcMotorSimple.Direction.REVERSE);
        }
        if (DirT.equals("Right")) {
            topLeft.setDirection(DcMotorSimple.Direction.FORWARD);
            topRight.setDirection(DcMotorSimple.Direction.FORWARD);
            bottomLeft.setDirection(DcMotorSimple.Direction.FORWARD);
            bottomRight.setDirection(DcMotorSimple.Direction.FORWARD);
        }
        Rotate = Math.abs(Rotate);
        topLeft.setTargetPosition(Rotate);
        topRight.setTargetPosition(Rotate);
        bottomLeft.setTargetPosition(Rotate);
        bottomRight.setTargetPosition(Rotate);

        topLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        topRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bottomLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bottomRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        SpdT = Range.clip(SpdT, 0, 1);
        topLeft.setPower(SpdT);
        topRight.setPower(SpdT);
        bottomLeft.setPower(SpdT);
        bottomRight.setPower(SpdT);


        while (opModeIsActive() && topLeft.isBusy())
        //leftMotor.getCurrentPosition() < leftMotor.getTargetPosition())
        {
            telemetry.addData("encoder-fwd-left", topLeft.getCurrentPosition() + "busy=" + topLeft.isBusy());
            telemetry.addData("encoder-fwd-right", topRight.getCurrentPosition() + "busy=" + topRight.isBusy());
            telemetry.update();
            idle();
        }
        //stop
        topLeft.setPower(0);
        topRight.setPower(0);
        bottomLeft.setPower(0);
        bottomRight.setPower(0);
    }

}