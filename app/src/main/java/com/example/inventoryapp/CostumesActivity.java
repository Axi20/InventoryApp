package com.example.inventoryapp;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.inventoryapp.databinding.ActivityCostumesBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;

public class CostumesActivity extends AppCompatActivity {

    //Set binding
    private ActivityCostumesBinding binding;

    //Database set up, costumes table
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("costumes");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCostumesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Initialize the recycler view
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        //Create a list of data to be displayed in the recycler view
        List<HelperClassCostumes> data = new ArrayList<>();

        //Create an instance of the recycler view adapter and set it to the recycler view
        RecyclerAdapter adapter = new RecyclerAdapter(data);
        recyclerView.setAdapter(adapter);

        //Set the layout manager of the recycler view to a LinearLayout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
