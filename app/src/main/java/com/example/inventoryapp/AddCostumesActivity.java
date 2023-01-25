package com.example.inventoryapp;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.inventoryapp.databinding.ActivityAddCostumesBinding;

public class AddCostumesActivity extends AppCompatActivity {

    private ActivityAddCostumesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddCostumesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
}
