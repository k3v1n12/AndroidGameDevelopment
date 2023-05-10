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
@Override
    public void draw(Canvas canvas) {
        canvas.drawCircle((float)positionX, (float)positionY, (float)radius, paint);
    }
}
