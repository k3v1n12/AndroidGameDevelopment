package com.example.androidgamedevelopment;

import android.graphics.Canvas;

/**
 * GameObject is an abstract object is the foundation of all world object
 */

public abstract class GameObject {

    protected double positionX;
    protected double positionY;

    public GameObject(double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }
    public abstract void draw(Canvas canvas);
    public abstract void update();
}
