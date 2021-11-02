package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import static android.os.SystemClock.sleep;

@Autonomous(name = "ProgrammingBoard_Auto", group = "team")
public class FY21ProgramBoardAuto extends LinearOpMode {

    //define motors and stuff
    DcMotor Large;
    DcMotor Small;
    DcMotor Rev;
    Servo Pass;
    ColorSensor RubberDuck;
    int currentstep = 0;
    public void runOpMode(){
    //hardware maps
    Large = hardwareMap.dcMotor.get("LM"); //port 3 Andy Mark
    Small = hardwareMap.dcMotor.get("SM"); //port 0
    Rev = hardwareMap.dcMotor.get("HM"); //port 2
    Pass = hardwareMap.servo.get("S"); // medium port 3
    RubberDuck = hardwareMap.colorSensor.get("RD"); //small port 0


    waitForStart();
        while (opModeIsActive()) {

        if (currentstep == 0) {
            currentstep++;
        }

        if (currentstep == 1) {
            telemetry.addData("inside currentstep:", currentstep);
            telemetry.update();
//This is the autonomous code.

            //Use LM
            Large.setPower(0.5);
            sleep(1000);
            Large.setPower(0);
            currentstep++;
        }
        if (currentstep == 2) {
            telemetry.addData("inside currentstep:", currentstep);
            telemetry.update();
            //Use SM
            Small.setPower(0.5);
            sleep(1050);
            Small.setPower(0);
            currentstep++;
        }
            if (currentstep == 3) {
                telemetry.addData("inside currentstep:", currentstep);
                telemetry.update();
                //Use HM
                Rev.setPower(0.5);
                sleep(1500);
                Rev.setPower(0);
                currentstep++;
            }
            if (currentstep == 4) {
                telemetry.addData("inside currentstep:", currentstep);
                telemetry.update();
                //Use S
                Pass.setPosition(90);
                sleep(1080);
                Pass.setPosition(0);
            }
    }
}}

