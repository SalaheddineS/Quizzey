package com.QuizzApp;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Random;


public class QuizzActivity extends AppCompatActivity {
    String level;
    String language;
    int CurrentPage = 1;
    int currentQuestionIndex = 0;
    int score = 0;
    DatabaseReference questionRef = FirebaseDatabase.getInstance().getReference("questions");
    ArrayList<Integer> finishedQuestions = new ArrayList<>();

    Button PageNumberButton;
    TextView Question;
    Button answer1;
    Button answer2;
    Button answer3;
    Button answer4;
    ImageView image;

    Map<String, Object> Elements = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        language=getIntent().getStringExtra("language");
        level = getIntent().getStringExtra("level");
        questionRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                Elements.put(snapshot.getKey(), snapshot.getValue());

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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setContentView(R.layout.activity_quizz);
                answer1 = findViewById(R.id.button7);
                answer2 = findViewById(R.id.button3);
                answer3 = findViewById(R.id.button6);
                answer4 = findViewById(R.id.button4);
                Question = findViewById(R.id.textView);
                image = findViewById(R.id.imageView4);
                PageNumberButton = findViewById(R.id.button2);
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
                        checkAnswer(4);
                    }
                });

            }
        }, 2000);


    }

    public void setQuestionAndAnswers() {

        Random rand = new Random();
        currentQuestionIndex = rand.nextInt(Elements.size());
        String index = String.valueOf(currentQuestionIndex);
        Map<String, Object> tmp = (Map<String, Object>) Elements.get(index);
        while (!(language.equals((String) tmp.get("language")))||!(level.equals((String) tmp.get("difficulty")))||finishedQuestions.contains(currentQuestionIndex)){
            currentQuestionIndex = rand.nextInt(Elements.size());
             index = String.valueOf(currentQuestionIndex);
             tmp = (Map<String, Object>) Elements.get(index);

                System.out.println("level: "+level+" "+((String) tmp.get("difficulty")));
        }
        String question = (String) tmp.get("question");
        Question.setText(question);
        Object[] optionsArray = ((List<Object>) tmp.get("options")).toArray();
        answer1.setText((CharSequence) optionsArray[0]);
        answer2.setText((CharSequence) optionsArray[1]);
        answer3.setText((CharSequence) optionsArray[2]);
        answer4.setText((CharSequence) optionsArray[3]);
        PageNumberButton.setText(""+CurrentPage);
        CurrentPage++;
        finishedQuestions.add(currentQuestionIndex);
        if(CurrentPage==7){
            answer1.setVisibility(View.INVISIBLE);
            answer2.setVisibility(View.INVISIBLE);
            answer3.setVisibility(View.INVISIBLE);
            answer4.setVisibility(View.INVISIBLE);
            Question.setText("Your score is "+score);
            PageNumberButton.setText("Finish");
        }
    }

    public void checkAnswer(int rep) {
        String index = String.valueOf(currentQuestionIndex);
        Map<String, Object> tmp = (Map<String, Object>) Elements.get(index);

        String correctAnswer = (String) tmp.get("answer");
        String rep1 = answer1.getText().toString();
        String rep2 = answer2.getText().toString();
        String rep3 = answer3.getText().toString();
        String rep4 = answer4.getText().toString();
        if (rep == 1 && rep1.equals(correctAnswer)) {
            score++;
        } else if (rep == 2 && rep2.equals(correctAnswer)) {
            score++;
        } else if (rep == 3 && rep3.equals(correctAnswer)) {
            score++;
        } else if (rep == 4 && rep4.equals(correctAnswer)) {
            score++;


        }

        setQuestionAndAnswers();
    }

    public void save() {
        Map<String, Object> tmp = (Map<String, Object>) Elements.get("5");
        System.out.println(tmp.get("language"));
        System.out.println(tmp.get("question"));
        System.out.println(tmp.get("answer"));
        System.out.println(tmp.get("difficulty"));
        Object[] optionsArray = ((List<Object>) tmp.get("options")).toArray();
        for (Object o : optionsArray) {
            System.out.println(o);
        }
    }
}
