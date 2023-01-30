package com.example.inventoryapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.inventoryapp.databinding.ActivityEditCostumesBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EditCostumes extends AppCompatActivity {

    //Set up binding
    private ActivityEditCostumesBinding binding;
    //Variable what store the radiobutton value
    String selectedValue;
    String costume_name;
    String costume_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditCostumesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Radiobutton onClick
        binding.sizeXsButton.setOnClickListener(v->{
            UpdateSize();
            if(binding.sizeXsButton.isChecked()){selectedValue = binding.sizeXsButton.getText().toString();}
        });

        binding.sizeSButton.setOnClickListener(v->{
            UpdateSize();
            if(binding.sizeSButton.isChecked()){selectedValue = binding.sizeSButton.getText().toString();}
        });

        binding.sizeMButton.setOnClickListener(v->{
            UpdateSize();
            if(binding.sizeMButton.isChecked()){selectedValue = binding.sizeMButton.getText().toString();}
        });

        binding.sizeLButton.setOnClickListener(v->{
            UpdateSize();
            if(binding.sizeLButton.isChecked()){selectedValue = binding.sizeLButton.getText().toString();}
        });
        costume_name = binding.costumeName.getText().toString();

        binding.editCostumesButton.setOnClickListener(v->{
            costume_name = binding.costumeName.getText().toString();
            costume_price = binding.costumeName.getText().toString();

           if(costume_price!=null){
               UpdatePrice();
               Toast.makeText(EditCostumes.this, "Sikeres módosítás!", Toast.LENGTH_SHORT).show();
           }


           if(selectedValue!=null){
               UpdateSize();
               Toast.makeText(EditCostumes.this, "Sikeres módosítás!", Toast.LENGTH_SHORT).show();
           }
        });
    }

    public void UpdatePrice(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("costumes").child(costume_name);

        costume_price = binding.costumePrice.getText().toString();
        // Create the update map
        Map<String, Object> updateMap = new HashMap<>();
        updateMap.put("costume_price", costume_price);

        // Update the costume price field
        ref.updateChildren(updateMap)
                .addOnSuccessListener(aVoid -> {
                    // The update was successful
                    Log.d("Update Success", "The costume price was updated successfully");
                })
                .addOnFailureListener(e -> {
                    // The update failed
                    Log.e("Update Error", "The costume price update failed", e);
                });


    }

    public void UpdateSize(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("costumes").child(costume_name);

        // Create the update map
        Map<String, Object> updateMap = new HashMap<>();
        updateMap.put("costume_size", selectedValue);

        // Update the costume size field
        ref.updateChildren(updateMap)
                .addOnSuccessListener(aVoid -> {
                    // The update was successful
                    Log.d("Update Success", "The costume size was updated successfully");
                })
                .addOnFailureListener(e -> {
                    // The update failed
                    Log.e("Update Error", "The costume size update failed", e);
                });
    }
}
