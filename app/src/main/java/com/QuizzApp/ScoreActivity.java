package com.QuizzApp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {

    private TextView displayScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        String score = getIntent().getStringExtra("score");
        displayScore = findViewById(R.id.textView4);
        displayScore.setText(score);
    }

}
