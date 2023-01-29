package com.example.inventoryapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventoryapp.databinding.ActivityRentBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class RentActivity extends AppCompatActivity {

    //Set binding
    private ActivityRentBinding binding;

    //Database set up, rents table
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("rents");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Initialize the recycler view
        RecyclerView recyclerView = findViewById(R.id.recycler_view_rent);

        //Create a list of data to be displayed in the recycler view
        List<HelperClassRents> data = new ArrayList<>();

        //Create an instance of the recycler view adapter and set it to the recycler view
        RecyclerAdapterForRents adapter = new RecyclerAdapterForRents(data);
        recyclerView.setAdapter(adapter);

        //Set the layout manager of the recycler view to a LinearLayout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
