package com.example.inventoryapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.inventoryapp.databinding.ActivityNewRentBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewRentActivity extends AppCompatActivity {

    //Set binding
    private ActivityNewRentBinding binding;

    //Variables from xml
    String costume_name_fromXML, paying_options_fromXML;
    String price_fromDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewRentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Need to check the radio buttons value
        binding.creditCardButton.setOnClickListener(v->{
            if(binding.creditCardButton.isChecked()){paying_options_fromXML = binding.creditCardButton.getText().toString();}
        });

        binding.cashButton.setOnClickListener(v->{
            if(binding.cashButton.isChecked()){paying_options_fromXML = binding.cashButton.getText().toString();}
        });

        binding.rentCostumeName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Do nothing
                //But if u bored u can hug me :)
                //I'm sure u won't read this but if you do... just joking
                //Or not?
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Do nothing again
            }

            @Override
            public void afterTextChanged(Editable editable) {
                costume_name_fromXML = editable.toString();
                getPrice(costume_name_fromXML);
            }
        });

        //Add the details into the DB
        binding.addRentButton.setOnClickListener(v->{
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference = database.getReference("rents");

            //Fields what will be save
            String costume_toDB = costume_name_fromXML;
            String customer_toDB = binding.rentCustomerName.getText().toString();
            String paying_options_toDB = paying_options_fromXML;
            String customer_email_toDB = binding.rentCustomerEmail.getText().toString();
            int rent_days_toDB = Integer.parseInt(binding.rentDays.getText().toString());
            int price_fromDB_int = Integer.parseInt(price_fromDB);
            int price_toDB = price_fromDB_int * rent_days_toDB;

            HelperClassRents helperClass = new HelperClassRents(costume_toDB,customer_toDB,paying_options_toDB,customer_email_toDB,rent_days_toDB,price_toDB);
            reference.child(costume_toDB).setValue(helperClass);

            Toast.makeText(NewRentActivity.this, "Sikeres bérlés!", Toast.LENGTH_SHORT).show();

            //Redirect to the main menu
            Intent intent = new Intent(NewRentActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    public void getPrice( String costume_name_fromXML){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myReference = database.getReference("costumes");
        myReference.orderByChild("costume_name").equalTo(costume_name_fromXML).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        HelperClassCostumes costumes = dataSnapshot.getValue(HelperClassCostumes.class);
                        price_fromDB = costumes.costume_price;
                        //Toast.makeText(NewRentActivity.this, "Price", Toast.LENGTH_SHORT).show();
                        Log.d("Costume Price: ", "price" + price_fromDB);
                    }
                }else {
                    Log.d("Costume price", "Costume not found");
                    //Toast.makeText(NewRentActivity.this, "Not found", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Costume price", "Failed to read value", error.toException());
                //Toast.makeText(NewRentActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
