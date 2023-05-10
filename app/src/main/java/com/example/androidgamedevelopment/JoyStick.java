package com.example.androidgamedevelopment;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class JoyStick {

    private int innerCircleCenterPositionY;
    private int outerCircleRadius;
    private int innerCircleRadius;
    private int innerCircleCenterPositionX;
    private int outerCircleCenterPositionY;
    private int outerCircleCenterPositionX;
    private Paint outerCirclePaint;
    private Paint innerCirclePaint;

    private boolean isPressed;

    private double actuatorX;
    private double actuatorY;


    public JoyStick(int centerPositionX, int centerPositionY, int outerCircleRadius, int innerCircleRadius) {

        //position of circles
        this.outerCircleCenterPositionX = centerPositionX;
        this.outerCircleCenterPositionY = centerPositionY;
        this.innerCircleCenterPositionX = centerPositionX;
        this.innerCircleCenterPositionY = centerPositionY;

        //radii of circles
        this.outerCircleRadius = outerCircleRadius;
        this.innerCircleRadius = innerCircleRadius;

        //paint of circles
        outerCirclePaint = new Paint();
        outerCirclePaint.setColor(Color.GRAY);
        outerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        innerCirclePaint = new Paint();
        innerCirclePaint.setColor(Color.BLUE);
        innerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

    }
    public void draw(Canvas canvas) {
        canvas.drawCircle((float)outerCircleCenterPositionX, (float) outerCircleCenterPositionY, (float) outerCircleRadius, outerCirclePaint);
        canvas.drawCircle((float)innerCircleCenterPositionX, (float) innerCircleCenterPositionY, (float) innerCircleRadius, innerCirclePaint);

    }

    public void update() {
        updateInnerCirclePosition();
    }

    public void updateInnerCirclePosition() {
        innerCircleCenterPositionX = (int)(outerCircleCenterPositionX + actuatorX * outerCircleRadius);
        innerCircleCenterPositionY = (int)(outerCircleCenterPositionY + actuatorY * outerCircleRadius);
    }

    public double touchToCenterDistance(double distanceX, double distanceY) {
        return Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
    }
    public boolean isPressed(double touchPositionX, double touchPositionY) {
        double distanceX = outerCircleCenterPositionX - touchPositionX;
        double distanceY =  outerCircleCenterPositionY - touchPositionY;
        return touchToCenterDistance(distanceX, distanceY) < outerCircleRadius;
    }

    public void setIsPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }

    public boolean getPressed() {
        return isPressed;
    }

    public void setActuator(double touchPositionX, double touchPositionY) {
        double deltaX = touchPositionX - outerCircleCenterPositionX;
        double deltaY = touchPositionY - outerCircleCenterPositionY;
        double deltaDistance = touchToCenterDistance(deltaX, deltaY);
        if(deltaDistance < outerCircleRadius) {
            actuatorX = deltaX / outerCircleRadius;
            actuatorY = deltaY / outerCircleRadius;
        }
        else {
            actuatorX = deltaX / deltaDistance;
            actuatorY = deltaY / deltaDistance;
        }
    }

    public void resetActuator() {
        actuatorX = 0.0;
        actuatorY = 0.0;
    }

    public double getActuatorX() {
        return actuatorX;
    }

    public double getActuatorY() {
        return actuatorY;
    }
}
