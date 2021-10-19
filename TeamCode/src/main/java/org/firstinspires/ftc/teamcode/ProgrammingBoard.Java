package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import static android.os.SystemClock.sleep;
@TeleOp(name = "{ProgrammingBoard_Code", group = "team")
public class FY21ProgramBoard extends OpMode {

    //define motors and stuff
    DcMotor Large;
    DcMotor Small;
    DcMotor Rev;
    Servo Pass;
    ColorSensor RubberDuck;

    public void init() {
    //hardware maps
    Large = hardwareMap.dcMotor.get("LM"); //port 3
    Small = hardwareMap.dcMotor.get("SM"); //port 0
    Rev = hardwareMap.dcMotor.get("HM"); //port 2
    Pass = hardwareMap.servo.get("S"); // mdeium port 3
    RubberDuck = hardwareMap.colorSensor.get("RD"); //small port 0
    }

    public void loop() {
        if (gamepad1.dpad_up) {
            Large.setPower(0.5);
            sleep(1000);
            Large.setPower(0);
        }
    }
}