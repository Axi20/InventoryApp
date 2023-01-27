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

public class LoginActivity extends AppCompatActivity {

    //Set binding
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Login button
        binding.loginButton.setOnClickListener(v->{
            if (!validateEmail() | !validatePassword()){
            }else{
                checkUser();
            }
        });

        //Redirecting the user to the sign up page if it have no account
        binding.signupRedirectText.setOnClickListener(v->{
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }

    //Email validation function
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

    //Password validation function
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

                    if (passwordFromDB.equals(userPassword)) {
                        binding.loginUsername.setError(null);

                        String nameFromDB = snapshot.child(userUsername).child("name").getValue(String.class);
                        String emailFromDB = snapshot.child(userUsername).child("email").getValue(String.class);
                        String usernameFromDB = snapshot.child(userUsername).child("username").getValue(String.class);

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        //Add profile data to the intent to pass
                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("password", passwordFromDB );
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
                //Handle errors
            }
        });
    }
}