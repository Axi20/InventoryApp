package com.example.inventoryapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.inventoryapp.databinding.ActivityDeleteCostumesBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DeleteCostumes extends AppCompatActivity {
    //Set binding
    private ActivityDeleteCostumesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeleteCostumesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Delete costumes from DB button
        binding.deleteCostumeButton.setOnClickListener(v->{
            //Costume what will be delete
            String costume_to_delete = binding.deleteCostumeField.getText().toString();

            //Connect to Database costumes table
            DatabaseReference costumesRef = FirebaseDatabase.getInstance().getReference().child("costumes");

            //Choose the costume from DB what is equal to what the user typed in
            Query costumeQuery = costumesRef.orderByChild("costume_name").equalTo(costume_to_delete);

            costumeQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(!snapshot.hasChildren()){
                        // Costume does not exist in database, display error message
                        Toast.makeText(DeleteCostumes.this, "A megadott nevű jelmez nem létezik az adatbázisban!", Toast.LENGTH_LONG).show();
                    }else{
                        // Costume exists in database, remove it
                        for(DataSnapshot costumeSnapshot : snapshot.getChildren()){
                            costumeSnapshot.getRef().removeValue();
                            Toast.makeText(DeleteCostumes.this, "Jelmez sikeresen törölve!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(DeleteCostumes.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    //Handle errors
                }
            });
        });
    }
}
