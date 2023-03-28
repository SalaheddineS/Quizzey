package com.QuizzApp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class LanguageActivity extends AppCompatActivity {
    ImageView English;
    ImageView French;
    ImageView Spanish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        English = findViewById(R.id.level2);
        French = findViewById(R.id.level1);
        Spanish = findViewById(R.id.level3);
        English.setOnClickListener(v -> {
            try {
                startActivity(new Intent(LanguageActivity.this, LevelActivity.class));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
        French.setOnClickListener(v -> {
            try {
                startActivity(new Intent(LanguageActivity.this, LevelActivity.class));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
        Spanish.setOnClickListener(v -> {
            try {
                startActivity(new Intent(LanguageActivity.this, LevelActivity.class));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        })

        ;
    }


}
