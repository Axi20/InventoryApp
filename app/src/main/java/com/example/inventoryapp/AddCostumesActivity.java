package com.example.inventoryapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.inventoryapp.databinding.ActivityAddCostumesBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCostumesActivity extends AppCompatActivity {
    //Set binding
    private ActivityAddCostumesBinding binding;

    //Database set up
    FirebaseDatabase database;
    DatabaseReference reference;

    //Variable what store the radiobutton value
    String selectedValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddCostumesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Radiobutton onClick
       binding.sizeXsButton.setOnClickListener(v->{
           if(binding.sizeXsButton.isChecked()){selectedValue = binding.sizeXsButton.getText().toString();}
       });

       binding.sizeSButton.setOnClickListener(v->{
           if(binding.sizeSButton.isChecked()){selectedValue = binding.sizeSButton.getText().toString();}
       });

        binding.sizeMButton.setOnClickListener(v->{
            if(binding.sizeMButton.isChecked()){selectedValue = binding.sizeMButton.getText().toString();}
        });

        binding.sizeLButton.setOnClickListener(v->{
            if(binding.sizeLButton.isChecked()){selectedValue = binding.sizeLButton.getText().toString();}
        });

        //Save costumes into DB button
        binding.costumesToDB.setOnClickListener(v->{
            database = FirebaseDatabase.getInstance();
            reference = database.getReference("costumes");

            //Fields what will be save on the DB's costumes table
            String costume_name = binding.costumesName.getText().toString();
            String costume_size = selectedValue;
            String costume_price = binding.costumePrice.getText().toString();

            HelperClassCostumes helperClass = new HelperClassCostumes(costume_name, costume_size, costume_price);
            reference.child(costume_name).setValue(helperClass);

            Toast.makeText(AddCostumesActivity.this, "Jelmez hozz??adva!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddCostumesActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
