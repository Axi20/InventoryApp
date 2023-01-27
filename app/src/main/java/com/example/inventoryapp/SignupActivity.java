package com.example.inventoryapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.inventoryapp.databinding.ActivitySignupBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    //Set binding
    private ActivitySignupBinding binding;

    //Database set up
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Sign-up button onClick
        binding.signupButton.setOnClickListener(v -> {
            database = FirebaseDatabase.getInstance();
            reference = database.getReference("customers");

            //Fields what will be saved in DB's customers table
            String name = binding.signupName.getText().toString();
            String username = binding.signupUsername.getText().toString();
            String email = binding.signupEmail.getText().toString();
            String password = binding.signupPassword.getText().toString();

            HelperClass helperClass = new HelperClass(name, username, email, password);
            reference.child(username).setValue(helperClass);

            Toast.makeText(SignupActivity.this, "You have signup successfully!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        //It redirects to login, if you already have an account
        binding.loginRedirectText.setOnClickListener(v -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        } );
    }
}