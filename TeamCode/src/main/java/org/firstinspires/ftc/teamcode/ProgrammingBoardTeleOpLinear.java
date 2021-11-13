package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "programming board TeleOp", group = "default")
public class ProgrammingBoardTeleOpLinear extends LinearOpMode {

    //define motors
    ColorSensor RubberDuck; //left
    DcMotor Large;
    DcMotor Small;
    DcMotor Rev;
    Servo Pass;

    @Override
    public void runOpMode() throws InterruptedException {

    }
}
