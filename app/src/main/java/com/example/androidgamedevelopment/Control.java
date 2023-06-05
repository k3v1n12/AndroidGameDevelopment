package com.example.androidgamedevelopment;

import android.content.Context;
import android.graphics.Canvas;
import android.net.wifi.aware.DiscoverySession;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.androidgamedevelopment.gamepanel.JoyStick;

import java.io.IOException;
import java.util.Objects;

import ac.robinson.bettertogether.api.messaging.BroadcastMessage;
import ac.robinson.bettertogether.api.BasePluginActivity;

public class Control extends SurfaceView implements SurfaceHolder.Callback {
    private final JoyStick joyStick;
    private ControlActivity context;
    private int joystickPointerId = 0;
    SurfaceHolder surfaceHolder;
    public Control(Context context) throws IOException {
        super(context);
        this.context = (ControlActivity) context;
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        //initialise joystick
        joyStick = new JoyStick(275, 600, 90, 60);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //handle touch event action
        switch(event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if (joyStick.getIsPressed()) {
                    // Joystick was pressed before this event -> cast spell
                    BroadcastMessage spellMessage = new BroadcastMessage(MessageType.FIRE_SPELL, null);
                    context.sendMessage(spellMessage);
                } else if (joyStick.isPressed((double) event.getX(), (double) event.getY())) {
                    // Joystick is pressed in this event -> setIsPressed(true) and store pointer id
                    joystickPointerId = event.getPointerId(event.getActionIndex());
                    String message = String.format("%f %f",joyStick.getActuatorX (), joyStick.getActuatorY());
                    BroadcastMessage joyStickMessage = new BroadcastMessage(MessageType.JOYSTICK_DOWN, message);
                    context.sendMessage(joyStickMessage);
                    joyStick.setIsPressed(true);
                } else {
                    // Joystick was not previously, and is not pressed in this event -> cast spell
                    BroadcastMessage spellMessage = new BroadcastMessage(MessageType.FIRE_SPELL, null);
                    context.sendMessage(spellMessage);
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if (joyStick.getIsPressed()) {
                    // Joystick was pressed previously and is now moved
                    joyStick.setActuator((double) event.getX(), (double) event.getY());
                    String message = String.format("%f %f",joyStick.getActuatorX (), joyStick.getActuatorY());
                    BroadcastMessage joyStickMessage = new BroadcastMessage(MessageType.JOYSTICK_MOVE, message);
                    context.sendMessage(joyStickMessage);
                }
                return true;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (joystickPointerId == event.getPointerId(event.getActionIndex())) {
                    // joystick pointer was let go off -> setIsPressed(false) and resetActuator()
                    joyStick.setIsPressed(false);
                    joyStick.resetActuator();
                }
                return true;
        }
        return super.onTouchEvent(event);
    }
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

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
        joyStick.draw(canvas);
    }

    public void update() {
        joyStick.update();
    }

    public void drawControl() {
        Canvas canvas = null;
        //Try to update and Render
        try{
            canvas = surfaceHolder.lockCanvas();
            synchronized (surfaceHolder) {
                draw(canvas);
            }
        }catch (IllegalArgumentException e) {
            e.printStackTrace();
        }finally {
            if (canvas != null) {
                try {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
