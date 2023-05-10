package com.example.androidgamedevelopment;

import android.content.Context;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

/**
 * Player is the main character of the game. The user can control
 * player with a touch joystick. Player is a class extension of
 * Circle which is an extension of GameObject
 */
public class Player extends Circle{

    private static final double SPEED_PIXEL_PER_SECOND = 400.0;
    private static final double MAX_SPEED = SPEED_PIXEL_PER_SECOND / GameLoop.MAX_UPS;
    private final JoyStick joyStick;



    public Player(Context context, JoyStick joyStick, double positionX, double positionY, double radius) {
        super(context, ContextCompat.getColor(context, R.color.player), positionX, positionY, radius);
        this.joyStick = joyStick;

        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.player);
        paint.setColor(color);
    }

    public void update() {
        //update velocity based on teh actuator of the joystick
        double velocityX = joyStick.getActuatorX() * MAX_SPEED;
        double velocityY = joyStick.getActuatorY() * MAX_SPEED;

        //update the position
        positionX += velocityX;
        positionY += velocityY;
    }
}
