package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Presentation;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button playButtton;

    public void playGame(View view) {
        Intent intent = new Intent(getApplicationContext(), GamePlay.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButtton = findViewById(R.id.playButton);
    }
}
