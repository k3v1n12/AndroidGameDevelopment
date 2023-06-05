package com.example.androidgamedevelopment;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Objects;

import ac.robinson.bettertogether.api.BasePluginActivity;
import ac.robinson.bettertogether.api.messaging.BroadcastMessage;
import ac.robinson.bettertogether.api.messaging.PluginConnectionDelegate;

public class ControlActivity  extends BasePluginActivity {
    private Control control;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity.java", "onCreate()");
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //Set Content View to game, so that object in the game class can be rendered
        try {
            control = new Control(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setContentView(control);
    }

    @Override
    protected void onMessageReceived(@NonNull BroadcastMessage message) {
        switch (message.getType()) {
            case MessageType.GAME_EXIT:
                finish(); // player has exited - we must finish too
                break;
            case MessageType.JOYSTICK_UPDATE:
                control.update();
                break;
            case MessageType.JOYSTICK_DRAW:
                control.drawControl();
                break;
            default:
                break;
        }
    }

}
