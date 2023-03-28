package com.QuizzApp;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class QuizzActivity extends AppCompatActivity {
    int currentQuestionIndex = 0;
    int score = 0;
    DatabaseReference questionRef = FirebaseDatabase.getInstance().getReference("questions");

    Button PageNumberButton;
    TextView Question;
    Button answer1;
    Button answer2;
    Button answer3;
    Button answer4;
    ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);
        answer1 = findViewById(R.id.button7);
        answer2 = findViewById(R.id.button3);
        answer3 = findViewById(R.id.button6);
        answer4 = findViewById(R.id.button4);
        Question = findViewById(R.id.textView);
        image = findViewById(R.id.imageView4);
        PageNumberButton = findViewById(R.id.button2);
        Map<String,Object> Elements=new HashMap<>();
        questionRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            Elements.put(snapshot.getKey(),snapshot.getValue());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        setQuestionAndAnswers();

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(1);
            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(2);
            }
        });

        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(3);
            }
        });

        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Map<String,Object> tmp= (Map<String, Object>) Elements.get("5");
                System.out.println(tmp.get("question"));
                System.out.println(tmp.get("language"));
                System.out.println(tmp.get("answer"));
                System.out.println(tmp.get("difficulty"));
            }
        });


    }

    public void setQuestionAndAnswers(){

    }

    public void checkAnswer(int rep){

    }


}
