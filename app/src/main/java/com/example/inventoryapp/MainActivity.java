package com.example.inventoryapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventoryapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {



    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        showUsername();

        binding.newRentButton.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, NewRentActivity.class);
            startActivity(intent);
        });

        binding.rentButton.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, RentActivity.class);
            startActivity(intent);
        });

        binding.costumesButton.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, CostumesActivity.class);
            startActivity(intent);
        });

        binding.addCostumesButton.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, AddCostumesActivity.class);
            startActivity(intent);
        });

        binding.deleteCostumesButton.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, DeleteCostumes.class);
            startActivity(intent);
        });

        binding.editCostumesButton.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, EditCostumes.class);
            startActivity(intent);
        });
    }
    public void showUsername(){
        Intent intent = getIntent();
        String usernameUser = intent.getStringExtra("name");
        binding.welcomeText.setText("Üdvözöljük, " + usernameUser);
    }
}