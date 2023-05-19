package com.example.androidgamedevelopment.objects;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

/**Circle is an abstract class which implements a draw method from
 * GameObject for drawing the object  as a circle
 */
public abstract class Circle  extends GameObject{

    protected double radius;
    protected Paint paint;
    double velocityX;
    double velocityY;

    public Circle(Context context, int color, double positionX, double positionY, double radius) {
        super(positionX, positionY);
        this.radius = radius;

        //Sets the color of the circle
        paint = new Paint();
        paint.setColor(color);
    }
    /**isColliding check if two objects collides with each other.
     * It returns a boolean value
     */
    public static boolean isColliding(Circle obj1, Circle obj2) {
        double distanceOfObject = getDistanceBetweenObject(obj1, obj2);
        double distanceToCollision = obj1.getRadius() + obj2.getRadius();
        if(distanceOfObject < distanceToCollision) {
            return true;
        }
        else {
            return false;
        }
    }

    private double getRadius() {
        return radius;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle((float)positionX, (float)positionY, (float)radius, paint);
    }
}
