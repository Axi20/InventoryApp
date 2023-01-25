package com.example.inventoryapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    EditText signup_name, signup_username, signup_email, signup_password;
    TextView loginRedirectText;
    Button signup_button;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup_name = findViewById(R.id.signup_name);
        signup_username = findViewById(R.id.signup_username);
        signup_email = findViewById(R.id.signup_email);
        signup_password = findViewById(R.id.signup_password);
        signup_button = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);

        signup_button.setOnClickListener(v -> {
            database = FirebaseDatabase.getInstance();
            reference = database.getReference("customers");
            String name = signup_name.getText().toString();
            String username = signup_username.getText().toString();
            String email = signup_email.getText().toString();
            String password = signup_password.getText().toString();

            HelperClass helperClass = new HelperClass(name, username, email, password);
            reference.child(name).setValue(helperClass);

            Toast.makeText(SignupActivity.this, "You have signup successfully!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        loginRedirectText.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        } );
    }
}