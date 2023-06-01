package com.example.androidgamedevelopment.gameobject;

import android.content.Context;
import android.graphics.Canvas;

import androidx.core.content.ContextCompat;

import com.example.androidgamedevelopment.GameDisplay;
import com.example.androidgamedevelopment.GameLoop;
import com.example.androidgamedevelopment.gamepanel.JoyStick;
import com.example.androidgamedevelopment.R;
import com.example.androidgamedevelopment.Utils;
import com.example.androidgamedevelopment.gamepanel.HealthBar;
import com.example.androidgamedevelopment.graphics.Animator;
import com.example.androidgamedevelopment.graphics.Sprite;

/**
 * Player is the main character of the game. The user can control
 * player with a touch joystick. Player is a class extension of
 * Circle which is an extension of GameObject
 */
public class Player extends Circle{

    public static final double SPEED_PIXEL_PER_SECOND = 400.0;
    private static final double MAX_SPEED = SPEED_PIXEL_PER_SECOND / GameLoop.MAX_UPS;
    public static final int MAX_HEALTH_POINTS = 5;
    private final JoyStick joyStick;
    private HealthBar healthBar;
    private int healthPoints = MAX_HEALTH_POINTS;
    private Animator animator;
    private PlayerState playerState;

    public Player(Context context, JoyStick joyStick, double positionX, double positionY, double radius, Animator animator)  {
        super(context, ContextCompat.getColor(context, R.color.player), positionX, positionY, radius);
        this.joyStick = joyStick;
        this.healthBar = new HealthBar(context, this);
        this.animator = animator;
        this.playerState = new PlayerState(this);
    }
    @Override
    public void update() {
        //update velocity based on teh actuator of the joystick
        velocityX = joyStick.getActuatorX() * MAX_SPEED;
        velocityY = joyStick.getActuatorY() * MAX_SPEED;

        //update the position
        positionX += velocityX;
        positionY += velocityY;

        // Update direction
        if (velocityX != 0 || velocityY != 0) {
            // Normalize velocity to get direction (unit vector of velocity)
            double distance = Utils.getDistanceBetweenPoints(0, 0, velocityX, velocityY);
            directionX = velocityX/distance;
            directionY = velocityY/distance;
        }
        // Update player State
        playerState.update();
    }


    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        animator.draw(
                canvas,
                gameDisplay,
                this
        );
        healthBar.draw(canvas, gameDisplay);
    }

    public int getHealthPoint() {
        return healthPoints;
    }

    public void setHealthPoint(int healthPoints) {
        // Only allow positive values
        if (healthPoints >= 0)
            this.healthPoints = healthPoints;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }
}
