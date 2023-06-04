package com.example.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class Game extends AppCompatActivity {

    TextView score;
    TextView life;
    TextView time;

    TextView question;

    EditText answer;

    Button ok;
    Button next;

    Random random = new Random();
    int number1, number2, userAnswer, realAnswer;
    int userScore = 0;
    int userLife = 3;

    int operation = 0;
    CountDownTimer timer;
    private static final long START_TIMER_IN_MILIS = 60000;
    Boolean timer_running;
    long time_left_in_milis = START_TIMER_IN_MILIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        score = findViewById(R.id.textViewScore);
        life = findViewById(R.id.textViewLife);
        time = findViewById(R.id.textViewTime);

        question = findViewById(R.id.textViewQuestion);

        answer = findViewById(R.id.editTextAnswer);

        ok = findViewById(R.id.buttonOk);
        next = findViewById(R.id.buttonNext);

        gameContinue();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswer = Integer.valueOf(answer.getText().toString());

                pauseTimer();

                if(userAnswer == realAnswer){
                    userScore+=10;
                    score.setText(""+userScore);
                    question.setText("True!");
                }else{
                    userLife--;
                    life.setText(""+userLife);
                    question.setText("False!");
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answer.setText("");
                resetTimer();

                if(userLife <= 0){
                    Toast.makeText(getApplicationContext(),"Game Over!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Game.this, Result.class);
                    intent.putExtra("score", userScore);
                    startActivity(intent);
                    finish();
                }else{
                    gameContinue();
                }
            }
        });
    }

    public void gameContinue(){
        number1 = random.nextInt(100);
        number2 = random.nextInt(100);

        Intent intent1 = getIntent();
        operation = intent1.getIntExtra("operation", 0);

        if (operation == 0){
            realAnswer = number1 + number2;

            question.setText(number1+ " + " + number2);

            startTimer();
        }else if (operation == 1){
            realAnswer = number1 - number2;

            question.setText(number1+ " - " + number2);

            startTimer();
        }else if (operation == 2){
            realAnswer = number1 * number2;

            question.setText(number1+ " * " + number2);

            startTimer();
        }

    }

    public void startTimer(){
        timer = new CountDownTimer(time_left_in_milis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time_left_in_milis = millisUntilFinished;
                updateText();
            }

            @Override
            public void onFinish() {
                timer_running = false;
                pauseTimer();
                resetTimer();
                updateText();
                userLife--;
                life.setText(""+userLife);
                question.setText("Time Is Up!");
            }
        }.start();

        timer_running = true;
    }

    public void updateText() {
        int second = (int) ((time_left_in_milis/1000)% 60);
        String time_left = String.format(Locale.getDefault(), "%02d", second);
        time.setText(time_left);
    }

    public void pauseTimer() {
        timer.cancel();
        timer_running = false;
    }

    public void resetTimer() {
        time_left_in_milis = START_TIMER_IN_MILIS;
        updateText();
    }
}