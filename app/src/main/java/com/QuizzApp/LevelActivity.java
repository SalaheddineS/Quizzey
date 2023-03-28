package com.QuizzApp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class LevelActivity extends AppCompatActivity {
    ImageView Level1;
    ImageView Level2;
    ImageView Level3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        Level1 =  findViewById(R.id.level1);
        Level2 =  findViewById(R.id.level2);
        Level3 =  findViewById(R.id.level3);
        Level1.setOnClickListener(v -> {
            try {
                startActivity(new Intent(LevelActivity.this, QuizzActivity.class));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
        Level2.setOnClickListener(v -> {
            try {
                startActivity(new Intent(LevelActivity.this, QuizzActivity.class));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
        Level3.setOnClickListener(v -> {
            try {
                startActivity(new Intent(LevelActivity.this, QuizzActivity.class));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
