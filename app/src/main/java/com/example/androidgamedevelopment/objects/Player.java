package com.example.androidgamedevelopment.objects;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.androidgamedevelopment.GameLoop;
import com.example.androidgamedevelopment.JoyStick;
import com.example.androidgamedevelopment.R;

/**
 * Player is the main character of the game. The user can control
 * player with a touch joystick. Player is a class extension of
 * Circle which is an extension of GameObject
 */
public class Player extends Circle{

    public static final double SPEED_PIXEL_PER_SECOND = 400.0;
    private static final double MAX_SPEED = SPEED_PIXEL_PER_SECOND / GameLoop.MAX_UPS;
    private final JoyStick joyStick;


    public Player(Context context, JoyStick joyStick, double positionX, double positionY, double radius) {
        super(context, ContextCompat.getColor(context, R.color.player), positionX, positionY, radius);
        this.joyStick = joyStick;
    }
    @Override
    public void update() {
        //update velocity based on teh actuator of the joystick
        velocityX = joyStick.getActuatorX() * MAX_SPEED;
        velocityY = joyStick.getActuatorY() * MAX_SPEED;

        //update the position
        positionX += velocityX;
        positionY += velocityY;
    }
}
