package com.example.inventoryapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventoryapp.databinding.ActivityCostumesBinding;
import com.example.inventoryapp.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CostumesActivity extends AppCompatActivity {

    private ActivityCostumesBinding binding;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("costumes");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCostumesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        List<HelperClassCostumes> data = new ArrayList<>();
        RecyclerAdapter adapter = new RecyclerAdapter(data);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

       //tManager(this);
        //recyclerView.setLayoutManager(layoutManager);


    }


}
