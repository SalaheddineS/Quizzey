package com.QuizzApp;

import static android.content.ContentValues.TAG;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;
import com.google.type.DateTime;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    Button goBackLogin;
    Button newEmail;
    Button newPassword;
    Button ConfirmedPassword;
    Button Submit;
    Button adresse;
    Button name;
    RadioButton Homme;
    RadioButton Femme;
    FirebaseAuth mAuth ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        goBackLogin = findViewById(R.id.button5);
        newEmail = findViewById(R.id.login);
        newPassword =findViewById(R.id.password);
        Submit = findViewById(R.id.button);
        ConfirmedPassword = findViewById(R.id.cpassword);
        adresse = findViewById(R.id.adresse);
        name = findViewById(R.id.name);
        Homme = findViewById(R.id.radio_male);
        Femme = findViewById(R.id.radio_female);
        newEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an AlertDialog with an EditText view for user input
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setTitle("Enter new email");

                final EditText input = new EditText(RegisterActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                // Set up the "OK" button to update the button text
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newEmailText = input.getText().toString();
                        newEmail.setText(newEmailText);
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
        newPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an AlertDialog with an EditText view for user input
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setTitle("Enter password");

                final EditText input = new EditText(RegisterActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                // Set up the "OK" button to update the button text
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newEmailText = input.getText().toString();
                        newPassword.setText(newEmailText);
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
        ConfirmedPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an AlertDialog with an EditText view for user input
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder.setTitle("Confirm password");

                final EditText input = new EditText(RegisterActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

                // Set up the "OK" button to update the button text
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newEmailText = input.getText().toString();
                        ConfirmedPassword.setText(newEmailText);
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
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = newEmail.getText().toString();
                String password = newPassword.getText().toString();
                String cpassword = ConfirmedPassword.getText().toString();
                String nameText = name.getText().toString();
                String adresseText = adresse.getText().toString();
                String sexe = "";
                if (Homme.isChecked()) {
                    sexe = "Homme";
                } else if (Femme.isChecked()) {
                    sexe = "Femme";
                }
                HashMap<String, Object> user = new HashMap<>();
                user.put("name", nameText);
                user.put("email", email);
                user.put("adresse", adresseText);
                user.put("sexe", sexe);
                user.put("creationDate", LocalDateTime.now());
                if (password.equals(cpassword) && !TextUtils.isEmpty(email)) {

                    mAuth = FirebaseAuth.getInstance();
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    db.collection("users").add(user)
                                            .addOnSuccessListener(documentReference -> Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId()))
                                            .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e)).addOnCompleteListener(tsk -> {
                                                if (tsk.isSuccessful()) {
                                                    Toast.makeText(RegisterActivity.this, "User created", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                                    startActivity(intent);
                                                } else {
                                                    Toast.makeText(RegisterActivity.this, "User not created", Toast.LENGTH_SHORT).show();
                                                }
                                            });


                                } else {
                                    Toast.makeText(RegisterActivity.this, "User not created", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(e -> {
                                Log.e(TAG, "Error creating user", e);
                                Toast.makeText(RegisterActivity.this, "Error creating user", Toast.LENGTH_SHORT).show();
                            });
                } else {
                    Toast.makeText(RegisterActivity.this, "Passwords do not match or one of the attributes is empty", Toast.LENGTH_SHORT).show();
                }

            }

        });
        goBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LognActivity.class);
                startActivity(intent);
            }
        });
    }
}
