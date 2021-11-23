package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "FY21 TeleOp", group = "TeleOp")
public class FY21Teleop extends LinearOpMode {
    DcMotor topRight;
    DcMotor bottomRight;
    DcMotor topLeft;
    DcMotor bottomLeft;

    @Override
    public void runOpMode() throws InterruptedException {
        //Engineers using REV CoreHex motors for "spinney box" and Linear Slide
        //hardware maps
        topRight = hardwareMap.dcMotor.get("TR"); //port 0
        bottomRight = hardwareMap.dcMotor.get("BR"); //port 1
        topLeft = hardwareMap.dcMotor.get("TL"); //port 2
        bottomLeft = hardwareMap.dcMotor.get("BL"); //port 3
        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.x) {

            }
            if (gamepad1.y) {

            }
            if (gamepad1.b) {

            }
            if (gamepad1.a) {

            }


            double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
            double robotAngle = Math.atan2(gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
            double rightX = gamepad1.right_stick_x;
            final double v1 = r * Math.cos(robotAngle) + rightX;
            final double v2 = r * Math.sin(robotAngle) - rightX;
            final double v3 = r * Math.sin(robotAngle) + rightX;
            final double v4 = r * Math.cos(robotAngle) - rightX;

            topRight.setPower(v1);
            bottomRight.setPower(v2);
            topLeft.setPower(v3);
            bottomLeft.setPower(v4);
        }
    }
}