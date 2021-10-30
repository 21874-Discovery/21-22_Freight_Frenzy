package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import static android.os.SystemClock.sleep;

@TeleOp(name = "programming board TeleOp", group = "default")
public class ProgrammingBoardTeleOp extends LinearOpMode {

    //define motors and stuff
    DcMotor Large;
    DcMotor Small;
    DcMotor Rev;
    Servo Pass;
    ColorSensor RubberDuck;

    @Override
    public void runOpMode() throws InterruptedException {

        //hardware maps
        Large = hardwareMap.dcMotor.get("LM"); //port 3
        Small = hardwareMap.dcMotor.get("SM"); //port 0
        Rev = hardwareMap.dcMotor.get("HM"); //port 2
        Pass = hardwareMap.servo.get("S"); // medium port 3
        RubberDuck = hardwareMap.colorSensor.get("RD"); //small port 0

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.x) {
                Large.setPower(0.5);
                sleep(1000);
                Large.setPower(0);
            }
            if (gamepad1.y) {
                Pass.setPosition(90);
                sleep(1000);
                Pass.setPosition(0);
            }
            if (gamepad1.b) {
                Rev.setPower(0.5);
                sleep(1000);
                Rev.setPower(0);
            }
            if (gamepad1.a) {
                Small.setPower(0.5);
                sleep(1000);
                Small.setPower(0);
            }
        }
    }
}