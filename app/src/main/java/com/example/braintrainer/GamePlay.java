package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class GamePlay extends AppCompatActivity {

    Button playButton;
    TextView sumTv, resultTv, scoreTv, timerTv;
    Button button0, button1, button2, button3, playAgainBtn;
    CountDownTimer countDownTimer;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions  = 0;

    public void playGame(View view){
        playButton.setVisibility(View.INVISIBLE);
    }

    public void playAgain(View view) {
        score = 0;
        numberOfQuestions  = 0;
        timerTv.setText("30s");
        resultTv.setVisibility(View.INVISIBLE);
        playAgainBtn.setVisibility(View.INVISIBLE);
        scoreTv.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestion();

        countDownTimer = new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTv.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTv.setText("Game Over ! \n Score : " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
                resultTv.setVisibility(View.VISIBLE);
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.horn);
                mediaPlayer.start();
                playAgainBtn.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void chooseAnswer(View view) {
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            resultTv.setText("Correct !");
            resultTv.setVisibility(View.VISIBLE);
            score++;
        }else {
            resultTv.setText("Wrong :(");
            resultTv.setVisibility(View.VISIBLE);
        }
        numberOfQuestions++;
        scoreTv.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        newQuestion();
    }
    public void newQuestion(){
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTv.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);
        answers.clear();

        for(int i=0; i<4; i++){
            if(i == locationOfCorrectAnswer){
                answers.add(a+b);
            }else{
                int wrongAnswer = rand.nextInt(41);
                while(wrongAnswer == a+b){
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        playButton = findViewById(R.id.playButton);
        sumTv = findViewById(R.id.sumTv);
        resultTv = findViewById(R.id.resultTv);
        scoreTv = findViewById(R.id.scoreTv);
        playAgainBtn = findViewById(R.id.playAgainBtn);
        timerTv = findViewById(R.id.timerTv);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        newQuestion();
        playAgain(button0 = findViewById(R.id.button0));

    }
}
