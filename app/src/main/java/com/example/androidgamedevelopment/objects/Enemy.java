package com.example.androidgamedevelopment.objects;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.androidgamedevelopment.GameLoop;
import com.example.androidgamedevelopment.R;

public class Enemy extends Circle{
    private static final double SPEED_PIXEL_PER_SECOND = Player.SPEED_PIXEL_PER_SECOND * .6;
    private static final double MAX_SPEED = SPEED_PIXEL_PER_SECOND / GameLoop.MAX_UPS;
    private static final double SPAWN_PER_MINUTE = 20;
    private static final double SPAWN_PER_SECOND = SPAWN_PER_MINUTE / 60.0;
    private static final double UPDATES_PER_SPAWN = GameLoop.MAX_UPS/ SPAWN_PER_SECOND;
    private static double updatesUntilNextSpawn = UPDATES_PER_SPAWN;
    private final Player player;

    public Enemy(Context context,Player player, double positionX, double positionY, double radius) {
        super(context, ContextCompat.getColor(context, R.color.enemy), positionX, positionY, radius);

        this.player = player;
    }

    public Enemy(Context context, Player player) {
        super(context, ContextCompat.getColor(context, R.color.enemy),
                Math.random() * 1000, Math.random() * 1000, 30);
        this.player = player;
    }
    //***********************************************************************
    //Checks if enemy is ready to spawn and return boolean
    //***********************************************************************

    public static boolean isReadySpawn() {
        if(updatesUntilNextSpawn <= 0) {
            updatesUntilNextSpawn += UPDATES_PER_SPAWN;
            return true;
        }
        else {
            updatesUntilNextSpawn--;
            return false;
        }
    }

    @Override
    public void update() {
        //***********************************************************************
        //Update velocity of enemy so that velocity is in the direction of player
        //***********************************************************************
        //Calculate vector from enemy to player
        double distanceToPlayerX = player.getPositionX() - positionX;
        double distanceToPlayerY = player.getPositionY() - positionY;

        //calculate distance between enemy and player
        double distanceToPlayer = GameObject.getDistanceBetweenObject(this, player);

        //calculate direction from enemy to player
        double directionX = distanceToPlayerX/distanceToPlayer;
        double directionY = distanceToPlayerY/distanceToPlayer;

        //set velocity in direction of player
        velocityX = directionX * MAX_SPEED;
        velocityY = directionY * MAX_SPEED;

        positionX += velocityX;
        positionY += velocityY;


    }
}
