package com.QuizzApp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreActivity extends AppCompatActivity {

    private TextView displayScore;
    private ImageView restart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        String score = getIntent().getStringExtra("score");
        displayScore = findViewById(R.id.textView4);
        restart =findViewById(R.id.imageView6);
        displayScore.setText(score);
        restart.setOnClickListener(v -> {
            try {
                startActivity(new Intent(ScoreActivity.this,LanguageActivity.class));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
