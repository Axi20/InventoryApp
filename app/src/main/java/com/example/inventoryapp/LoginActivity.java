package com.example.inventoryapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


public class LoginActivity extends AppCompatActivity {

    EditText login_email, login_password;
    Button login_button;
    TextView signupRedirectText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);
        login_button = findViewById(R.id.login_button);
        signupRedirectText = findViewById(R.id.signupRedirectText);
    }

    public Boolean validateEmail(){
        String val = login_email.getText().toString();
        if(val.isEmpty()){
            login_email.setError("Email cannot be empty!");
            return false;
        } else {
            login_email.setError(null);
            return true;
        }
    }

    public Boolean validatePassword(){
        String val = login_password.getText().toString();
        if(val.isEmpty()){
            login_password.setError("Password cannot be empty!");
            return false;
        } else {
            login_password.setError(null);
            return true;
        }
    }

    public void checkUser(){
        String userEmail = login_email.getText().toString().trim();
        String userPassword = login_password.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("customers");
        Query checkUserDatabase = reference.orderByChild("email").equalTo(userEmail);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                login_email.setError(null);
                String passwordFromDB = snapshot.child(userEmail).child("password").getValue(String.class);

                if(!Objects.equals(passwordFromDB, userPassword)){
                    login_email.setError(null);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    login_password.setError("Invalid credentials");
                    login_password.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}