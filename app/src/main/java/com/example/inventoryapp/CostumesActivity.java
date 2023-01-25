package com.example.inventoryapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.inventoryapp.databinding.ActivityCostumesBinding;
import com.example.inventoryapp.databinding.ActivityMainBinding;

public class CostumesActivity extends AppCompatActivity {

    private ActivityCostumesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCostumesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}
