package com.QuizzApp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

public class LognActivity extends AppCompatActivity {
    Button Register;
    Button Submit;
    Button Email;
    Button Password;

    FirebaseAuth mAuth ;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Register = findViewById(R.id.button5);
        Submit = findViewById(R.id.button);
        Email = findViewById(R.id.login);
        Password = findViewById(R.id.password);
        Register.setOnClickListener(v -> {
            try {
                startActivity(new Intent(LognActivity.this, RegisterActivity.class));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
        Email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an AlertDialog with an EditText view for user input
                AlertDialog.Builder builder = new AlertDialog.Builder(LognActivity.this);
                builder.setTitle("Enter Email");

                final EditText input = new EditText(LognActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                // Set up the "OK" button to update the button text
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newEmailText = input.getText().toString();
                        Email.setText(newEmailText);
                    }
                });

                // Set up the "Cancel" button to dismiss the dialog
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                // Show the AlertDialog
                builder.show();
            }
        });
        Password.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Create an AlertDialog with an EditText view for user input
            AlertDialog.Builder builder = new AlertDialog.Builder(LognActivity.this);
            builder.setTitle("Enter Password");

            final EditText input = new EditText(LognActivity.this);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            // Set up the "OK" button to update the button text
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String newEmailText = input.getText().toString();
                    Password.setText(newEmailText);
                }
            });

            // Set up the "Cancel" button to dismiss the dialog
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            // Show the AlertDialog
            builder.show();
        }
    });
        Submit.setOnClickListener(v -> {
        mAuth = FirebaseAuth.getInstance();
        String email = Email.getText().toString();
        String password = Password.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(LognActivity.this, "Welcome " + user.getEmail(),
                                Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LognActivity.this, LanguageActivity.class));
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(LognActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    });
}

}

