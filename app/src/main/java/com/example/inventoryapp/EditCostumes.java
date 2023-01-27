package com.example.inventoryapp;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.inventoryapp.databinding.ActivityEditCostumesBinding;

public class EditCostumes extends AppCompatActivity {

    //Set up binding
    private ActivityEditCostumesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditCostumesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}
