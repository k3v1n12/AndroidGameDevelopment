package com.example.androidgamedevelopment.objects;

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

    protected static double getDistanceBetweenObject(GameObject obj1, GameObject obj2) {
        return Math.sqrt(Math.pow(obj1.getPositionX() - obj2.getPositionX(), 2) +
                Math.pow(obj1.getPositionY()-obj2.getPositionY(), 2));
    }

    public abstract void draw(Canvas canvas);
    public abstract void update();

    protected double getPositionX() {
        return positionX;
    }
    protected double getPositionY() {
        return positionY;
    }
}
