package com.example.androidgamedevelopment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

/**
 * Game manages all object in the game and is responsible for updating all states and render
 * all objects to screen
 */

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private final Player player;
    private final JoyStick joyStick;
    private final Enemy enemy;
    private GameLoop gameLoop;


    public Game(Context context) {
        super(context);
        //Get SurfaceHolder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);

        //initialise joystick
        joyStick = new JoyStick(275, 800, 90 ,60);

        //initialise enemy
        enemy = new Enemy();
        //initialise new player
        player = new Player(getContext(), joyStick, 500, 500, 30);

        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //handle touch event action
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(joyStick.isPressed((double)event.getX(), (double)event.getY())) {
                    joyStick.setIsPressed(true);
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if(joyStick.getPressed()) {
                    joyStick.setActuator((double)event.getX(), (double)event.getY());
                }
                return true;
            case MotionEvent.ACTION_UP:
                joyStick.setIsPressed(false);
                joyStick.resetActuator();
                return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {gameLoop.startLoop();

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawUPS(canvas);
        drawFPS(canvas);

        player.draw(canvas);
        joyStick.draw(canvas);
    }

    public void drawUPS(Canvas canvas) {
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("UPS: "+ averageUPS, 100, 100, paint);

    }

    public void drawFPS(Canvas canvas) {
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("FPS: "+ averageFPS, 100, 200, paint);

    }

    public void update() {
        player.update();
        joyStick.update();
    }
}
