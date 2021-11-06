package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import static android.os.SystemClock.sleep;

@TeleOp(name = "programming board TeleOp", group = "default")
public class ProgrammingBoardTeleOp extends LinearOpMode {

    private DigitalChannel touchSensor;
    private DcMotor motor;
    private double ticksPerRotation;
    private Servo servo;
    private AnalogInput pot;
    private ColorSensor colorSensor;
    private DistanceSensor distanceSensor;

    public void init(HardwareMap hwMap) {
        touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");
        touchSensor.setMode(DigitalChannel.Mode.INPUT);
        motor = hwMap.get(DcMotor.class, "motor");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ticksPerRotation = motor.getMotorType().getTicksPerRev();
        servo = hwMap.get(Servo.class, "servo");
        pot = hwMap.get(AnalogInput.class, "pot");

        colorSensor = hwMap.get(ColorSensor.class, "sensor_color_distance");
        distanceSensor = hwMap.get(DistanceSensor.class, "sensor_color_distance");
    }

    public boolean isTouchSensorPressed() {
        return !touchSensor.getState();
    }

    public void setMotorSpeed(double speed) {
        motor.setPower(speed);
    }

    public double getMotorRotations() {
        return motor.getCurrentPosition() / ticksPerRotation;
    }

    public void setServoPosition(double position) {
        servo.setPosition(position);
    }

    public double getPotAngle() {
        return Range.scale(pot.getVoltage(), 0, pot.getMaxVoltage(), 0, 270);
    }

    public int getAmountRed() {
        return colorSensor.red(); //Can change color to whatever needed later duck color
    }

    public int getAmountBlue() {
        return colorSensor.blue(); //Can change color to whatever needed later duck color
    }

    public int getAmountGreen() {
        return colorSensor.green(); //Can change color to whatever needed later duck color
    }

    public double getDistance(DistanceUnit du) {
        return distanceSensor.getDistance(du);
    }

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
        RubberDuck = hardwareMap.colorSensor.get("RD"); //small port
        telemetry.addData("Amount red", getAmountRed());
        telemetry.addData("Amount blue", getAmountBlue());
        telemetry.addData("Amount green", getAmountGreen());
        telemetry.addData("Distance (CM)", getDistance(DistanceUnit.CM));
        telemetry.addData("Distance (IN)", getDistance(DistanceUnit.INCH));

            waitForStart();

            while (opModeIsActive()) {
                if (gamepad1.x) {
                    Large.setPower(-1.0);
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