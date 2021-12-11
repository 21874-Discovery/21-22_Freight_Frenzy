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
   DcMotor carouselSpinner;
   ColorSensor duckScannerLeft; //left
   ColorSensor duckScannerRight; //right
   //ColorSensor ColorSensor;
   //define variables
   int currentstep = 0;
   String barcode = "none";

   double RobotDiameter = 20; //Max robot size is 18x18 with max diagonal width of 25.46 in)
   //Robot spins in a circle, rough diameter of robot's circle can be no more than 25.42 (diagonal)
   double RobotCircumference = RobotDiameter * 3.14;//Max circumference of Robot (d * pi) = 80 in
   double WheelSize = 4;  //diameter in inches of wheels (the engineers like 4in)
   double WheelCircumference = WheelSize * 3.14; //Circumference (d * pi) of wheel (distance wheel travels for 1 rotation)
   double RotationsPerCircle = RobotCircumference / WheelCircumference;// wheel rotations to turns in complete circle

   int DriveTicks = 480;  //1 wheel rotation = DriveTicks - based on motor and gear ratio  => 1 Tetrix DC motor 60:1 revolution = 1440 encoder ticks (20:1 = 480 ticks (divide by 60/20) or 400 ticks = 1 foot)
   //DriveTicks * RotationsPerCircle = 360 degrees
   //Rotations per degree
   int TicksPerDegree = (int) Math.round((DriveTicks * RotationsPerCircle) / 360);

   public class Function extends FY21MecanumDrive {}
   public void runOpMode() {
      //define hardware map
      duckScannerLeft = hardwareMap.colorSensor.get("DSL"); //Extension Hub I2C bus 3
      duckScannerRight = hardwareMap.colorSensor.get("DSR"); //Control Hub I2C bus 3
      topRight = hardwareMap.dcMotor.get("TR"); //Control Hub Port 0
      bottomRight = hardwareMap.dcMotor.get("BR"); //Control Hub Port 1
      topLeft = hardwareMap.dcMotor.get("TL"); //Control Hub Port 2
      bottomLeft = hardwareMap.dcMotor.get("BL"); //Control Hub Port 3
      carouselSpinner = hardwareMap.dcMotor.get("CS"); //Expansion Hub Port 2
      waitForStart();
      while (opModeIsActive()) {

         if (currentstep == 0) {
            currentstep++;
         }

         if (currentstep == 1) {
            telemetry.addData("inside currentstep:", currentstep);
            telemetry.update();
            //Move Forward 0.5
            Function.Mecanum_drive ( "Forward", 1, 1000);

            }
            //turn 90 degrees
            currentstep++;
         }
         if (currentstep == 2) {
            if (DCSUPERCOLOR(duckScannerLeft)) {
               telemetry.addData("inside currentstep:", currentstep);
               telemetry.update();
               //If duck middle
               // slide right 1
               //top- full arm exstention
               //mid- half exstention
               //bottom- lowest exstention
            }
            //movement code to slide left 1/2
            if (DCSUPERCOLOR(duckScannerLeft)) {
               //If duck left
            }
            //Slide 3/4 right
            if (DCSUPERCOLOR(duckScannerLeft)) {
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
        rightRear.setPower(v4);
   }*/


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
}