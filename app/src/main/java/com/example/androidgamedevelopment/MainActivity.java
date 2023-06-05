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
/**
 * MainActivity is the entry point to our application
 */
public class MainActivity extends BasePluginActivity {

    private Game game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity.java", "onCreate()");
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //Set Content View to game, so that object in the game class can be rendered
        game = new Game(this);
        setContentView(game);
    }
    @Override
    protected void onStart() {
        Log.d("MainActivity.java", "onStart()");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("MainActivity.java", "onResume()");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d("MainActivity.java", "onPause()");
        game.pause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d("MainActivity.java", "onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("MainActivity.java", "onDestroy()");
        super.onDestroy();
    }

    @Override
    protected void onMessageReceived(@NonNull BroadcastMessage message) {
        String[] messageString = new String[1];
        double actuatorX;
        double actuatorY;
        switch (message.getType()) {
            case MessageType.GAME_EXIT:
                finish(); // player has exited - we must finish too
                break;
            case MessageType.JOYSTICK_UP:
            case MessageType.JOYSTICK_MOVE:
            case MessageType.JOYSTICK_DOWN:
                messageString = Objects.requireNonNull(message.getMessage()).split(" ");
                actuatorX = Double.parseDouble(messageString[0]);
                actuatorY = Double.parseDouble(messageString[1]);
                game.JoyStickCoordinates(actuatorX, actuatorY);
                break;
            case MessageType.FIRE_SPELL:
                game.setNumberOfSpellsToCast(game.getNumberofSpellsToCast() + 1);
                break;
            default:
                break;
        }
    }

}