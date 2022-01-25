package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;


@Autonomous(name = "FY21AutoRedWarehouse", group = "team")

public class FY21RedAutoStorege extends LinearOpMode {
    //define motors and stuff
    DcMotor topRight;
    DcMotor bottomRight;
    DcMotor topLeft;
    DcMotor bottomLeft;
    DcMotor carouselSpinner;
    ColorSensor duckScannerLeft; //left
    ColorSensor duckScannerRight; //right
    DcMotor linearSlide;
    DcMotor spindle;
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
        carouselSpinner = hardwareMap.dcMotor.get("CS"); //Expansion Hub Port 2
        linearSlide = hardwareMap.dcMotor.get("LS"); //expansion hub port 0
        spindle = hardwareMap.dcMotor.get("SM"); //expansion hub port 1


        topLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        topRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bottomLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bottomRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        topLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        topRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bottomLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bottomRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
      /*
      topLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
       topRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      bottomLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      bottomRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
*/

        waitForStart();
        while (opModeIsActive()) {

            if (currentstep == 0) {
                currentstep++;
            }

            if (currentstep == 1) {

                telemetry.addData("inside currentstep:", currentstep);
                telemetry.update();
                //Mecanum_drive("Forward", 0.5, 2375);
                spindle.setPower(-1);
                sleep(2000);
                spindle.setPower(0);
                linearSlide.setPower(1);
                sleep(8000);
                linearSlide.setPower(0);
                spindle.setPower(1);
                sleep(5000);
                spindle.setPower(0);
//it has been saved :)
                //The code bellow is just for the duck park red carousel
                /*Mecanum_drive("Backward", 0.5, 625);
                carouselSpinner.setPower(0.5);
                //sleep(3500), this is if the shield does not get put on
                sleep(3800);
                carouselSpinner.setPower(0);
                Mecanum_drive("Forward", 0.5, 625);
                Mecanum_Turn("Left", 1, 408);
                Mecanum_drive("Forward", 0.5, 665);
                Mecanum_Turn("Left", 1, 408);
                Mecanum_drive("Forward", 0.5, 810);*/
                currentstep++;
            }
        }
    }


    public void Mecanum_drive(String Dir, double Spd, long Slp) {
      /*
      topRight = hardwareMap.dcMotor.get("TR"); //Control Hub Port 0
      bottomRight = hardwareMap.dcMotor.get("BR"); //Control Hub Port 1
      topLeft = hardwareMap.dcMotor.get("TL"); //Control Hub Port 2
      bottomLeft = hardwareMap.dcMotor.get("BL"); //Control Hub Port 3
      */

        switch (Dir) {
            case "Forward":
                topLeft.setDirection(DcMotorSimple.Direction.REVERSE);
                topRight.setDirection(DcMotorSimple.Direction.REVERSE);
                bottomLeft.setDirection(DcMotorSimple.Direction.FORWARD);
                bottomRight.setDirection(DcMotorSimple.Direction.FORWARD);
                break;
            case "Backward":
                topLeft.setDirection(DcMotorSimple.Direction.FORWARD);
                topRight.setDirection(DcMotorSimple.Direction.FORWARD);
                bottomLeft.setDirection(DcMotorSimple.Direction.REVERSE);
                bottomRight.setDirection(DcMotorSimple.Direction.REVERSE);
                break;
            case "Left":
                topLeft.setDirection(DcMotorSimple.Direction.FORWARD);
                topRight.setDirection(DcMotorSimple.Direction.REVERSE);
                bottomLeft.setDirection(DcMotorSimple.Direction.FORWARD);
                bottomRight.setDirection(DcMotorSimple.Direction.REVERSE);
                break;
            case "Right":
                topLeft.setDirection(DcMotorSimple.Direction.REVERSE);
                topRight.setDirection(DcMotorSimple.Direction.FORWARD);
                bottomLeft.setDirection(DcMotorSimple.Direction.REVERSE);
                bottomRight.setDirection(DcMotorSimple.Direction.FORWARD);
                break;
        }
      /*Dist = Math.abs(Dist);
      topLeft.setTargetPosition(Dist+topLeft.getCurrentPosition());
      topRight.setTargetPosition(Dist+topRight.getCurrentPosition());
      bottomLeft.setTargetPosition(Dist+bottomLeft.getCurrentPosition());
      bottomRight.setTargetPosition(Dist+bottomRight.getCurrentPosition());

      topLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      topRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      bottomLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      bottomRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
*/
        Spd = Range.clip(Spd, 0, 1);
        topLeft.setPower(Spd);
        topRight.setPower(Spd);
        bottomLeft.setPower(Spd);
        bottomRight.setPower(Spd);


        // while (opModeIsActive() && topLeft.isBusy())
        //leftMotor.getCurrentPosition() < leftMotor.getTargetPosition())
        // {
        telemetry.addData("encoder-fwd-left", topLeft.getCurrentPosition() + "busy=" + topLeft.isBusy());
        telemetry.addData("encoder-fwd-right", topRight.getCurrentPosition() + "busy=" + topRight.isBusy());
        telemetry.addData("encoder-fwd-BL", bottomLeft.getCurrentPosition() + "busy=" + bottomLeft.isBusy());
        telemetry.addData("encoder-fwd-BR", bottomRight.getCurrentPosition() + "busy=" + bottomRight.isBusy());
        telemetry.addData("Dir:", Dir + "Spd:" + Spd + "Slp:" + Slp);
        telemetry.update();
        //    idle();
        // }
        //wait (run motors) for Slp number of milliseconds
        sleep(Slp);

        //stop motors
        topLeft.setPower(0);
        topRight.setPower(0);
        bottomLeft.setPower(0);
        bottomRight.setPower(0);
    }

    public void Mecanum_Turn(String DirT, double SpdT, long SlpT) {
    /*  double RobotDiameter = 20; //Max robot size is 18x18 with max diagonal width of 25.46 in)
      //Robot spins in a circle, rough diameter of robot's circle can be no more than 25.42 (diagonal)
      double RobotCircumference = RobotDiameter * 3.14;//Max circumference of Robot (d * pi) = 80 in
      double WheelSize = 4;  //diameter in inches of wheels (the engineers like 4in)
      double WheelCircumference = WheelSize*3.14; //Circumference (d * pi) of wheel (distance wheel travels for 1 rotation)
      double RotationsPerCircle = RobotCircumference/WheelCircumference;// wheel rotations to turns in complete circle

      int DriveTicks = 1440;  //1 wheel rotation = DriveTicks - based on motor and gear ratio  => 1 Tetrix DC motor 60:1 revolution = 1440 encoder ticks (20:1 = 480 ticks (divide by 60/20) or 400 ticks = 1 foot)
      //DriveTicks * RotationsPerCircle = 360 degrees
      //Rotations per degree
      int TicksPerDegree = (int) Math.round((DriveTicks * RotationsPerCircle)/360);
      int Rotate = (int) Math.round(Deg * TicksPerDegree);
      */

      /*telemetry.addData("Rotating", Rotate + "ticks or " + Deg + " degrees");
      telemetry.update();*/

      /*topLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      topRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      bottomLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      bottomRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);*/

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
     /* Rotate = Math.abs(Rotate);
      topLeft.setTargetPosition(Rotate);
      topRight.setTargetPosition(Rotate);
      bottomLeft.setTargetPosition(Rotate);
      bottomRight.setTargetPosition(Rotate);

      topLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      topRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      bottomLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      bottomRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
*/
        SpdT = Range.clip(SpdT, 0, 1);
        topLeft.setPower(SpdT);
        topRight.setPower(SpdT);
        bottomLeft.setPower(SpdT);
        bottomRight.setPower(SpdT);

        sleep(SlpT);

      /*while (opModeIsActive() && topLeft.isBusy())
      //leftMotor.getCurrentPosition() < leftMotor.getTargetPosition())
      {
         telemetry.addData("encoder-fwd-left", topLeft.getCurrentPosition() + "busy=" + topLeft.isBusy());
         telemetry.addData("encoder-fwd-right", topRight.getCurrentPosition() + "busy=" + topRight.isBusy());
         telemetry.update();
         idle();
      }
       */
        //stop
        topLeft.setPower(0);
        topRight.setPower(0);
        bottomLeft.setPower(0);
        bottomRight.setPower(0);
    }

}