package com.example.inventoryapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.inventoryapp.databinding.ActivityLoginBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.loginButton.setOnClickListener(v->{
            if (!validateEmail() | !validatePassword()){

            }else{
                checkUser();
            }
        });

        binding.signupRedirectText.setOnClickListener(v->{
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }

    public Boolean validateEmail(){
        String val = binding.loginUsername.getText().toString();
        if(val.isEmpty()){
            binding.loginUsername.setError("Email cannot be empty!");
            return false;
        } else {
            binding.loginUsername.setError(null);
            return true;
        }
    }

    public Boolean validatePassword(){
        String val = binding.loginPassword.getText().toString();
        if(val.isEmpty()){
            binding.loginPassword.setError("Password cannot be empty!");
            return false;
        } else {
            binding.loginPassword.setError(null);
            return true;
        }
    }

    public void checkUser(){
        String userUsername = binding.loginUsername.getText().toString().trim();
        String userPassword = binding.loginPassword.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("customers");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    binding.loginUsername.setError(null);
                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);

                    if (!Objects.equals(passwordFromDB, userPassword)) {
                        binding.loginUsername.setError(null);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        binding.loginPassword.setError("Invalid credentials");
                        binding.loginPassword.requestFocus();
                    }
                } else {
                    binding.loginUsername.setError("User does not exist!");
                    binding.loginUsername.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}