package org.firstinspires.ftc.teamcode;

//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

//@Autonomous(name = "FY21Mecanum", group = "team")

public class FY21MecanumDrive {
   //define motors
   static DcMotor topRight;
   static DcMotor bottomRight;
   static DcMotor topLeft;
   static DcMotor bottomLeft;






   public static void Mecanum_drive(String Dir, double Spd, int Dist) {

      topRight = hardwareMap.dcMotor.get("TR"); //Control Hub Port 0
      bottomRight = hardwareMap.dcMotor.get("BR"); //Control Hub Port 1
      topLeft = hardwareMap.dcMotor.get("TL"); //Control Hub Port 2
      bottomLeft = hardwareMap.dcMotor.get("BL"); //Control Hub Port 3

      topLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      topRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      bottomLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
      bottomRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

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

   public static void Mecanum_Turn(String DirT, double SpdT, int Deg) {
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
      /*telemetry.addData("Rotating", Rotate + "ticks or " + Deg + " degrees");
      telemetry.update();*/

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