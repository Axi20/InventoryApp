package com.example.inventoryapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inventoryapp.databinding.ActivityNewRentBinding;

public class NewRentActivity extends AppCompatActivity {

    //Set binding
    private ActivityNewRentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewRentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}
