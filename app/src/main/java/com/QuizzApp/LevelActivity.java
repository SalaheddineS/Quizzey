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


        System.out.println("");
        Level1 =  findViewById(R.id.level1);
        Level2 =  findViewById(R.id.level2);
        Level3 =  findViewById(R.id.level3);
        Level1.setOnClickListener(v -> {
            try {
                Intent intent=new Intent(LevelActivity.this,QuizzActivity.class);
                String language=getIntent().getStringExtra("language");
                intent.putExtra("level","easy");
                intent.putExtra("language",language);
                startActivity(intent);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
        Level2.setOnClickListener(v -> {
            try {
                Intent intent=new Intent(LevelActivity.this,QuizzActivity.class);
                String language=getIntent().getStringExtra("language");
                intent.putExtra("level","intermediate");
                intent.putExtra("language",language);
                startActivity(intent);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
        Level3.setOnClickListener(v -> {
            try {
                Intent intent=new Intent(LevelActivity.this,QuizzActivity.class);
                String language=getIntent().getStringExtra("language");
                intent.putExtra("level","hard");
                intent.putExtra("language",language);
                startActivity(intent);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
