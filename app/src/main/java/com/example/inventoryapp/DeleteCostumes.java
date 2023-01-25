package com.example.inventoryapp;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.inventoryapp.databinding.ActivityDeleteCostumesBinding;

public class DeleteCostumes extends AppCompatActivity {

    private ActivityDeleteCostumesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeleteCostumesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

    }
}
