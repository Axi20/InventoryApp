package com.example.inventoryapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class RecyclerAdapterForRents extends RecyclerView.Adapter<RecyclerAdapterForRents.ViewHolder> {

    private List<HelperClassRents> data;
    private DatabaseReference databaseReference;

    public RecyclerAdapterForRents(List<HelperClassRents> data){

        // Initialize the data list with the passed in list.
        this.data = data;

        // Getting an instance of the Firebase database and getting a reference to the "rents" node.
        databaseReference = FirebaseDatabase.getInstance().getReference();

        // Adding a listener to the "costumes" node to retrieve data when the data changes.
        databaseReference.child("rents").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Iterating over the snapshot data of "rents" node
                for (DataSnapshot snapshot1 : snapshot.getChildren()){

                    // Getting the data of each child of "rents" node and converting it to HelperClassRents object
                    HelperClassRents helperClassRents = snapshot1.getValue(HelperClassRents.class);

                    // Adding the HelperClassCostumes object to the data list
                    RecyclerAdapterForRents.this.data.add(helperClassRents);
                }
                // Notifying the adapter that the data has changed
                notifyDataSetChanged();
            }

            // Overriding onCancelled method to handle any error that might occur
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Firebase Error:", "Failed to read value.", error.toException());
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView rent_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rent_name = itemView.findViewById(R.id.rent_costume_name);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflating the custom layout for the items in the recycler view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custome_design_rents, parent, false);
        //Creating a new ViewHolder and passing the inflated layout to it
        RecyclerAdapterForRents.ViewHolder holder = new RecyclerAdapterForRents.ViewHolder(itemView);
        //Initializing the TextView in the inflated layout
        holder.rent_name = itemView.findViewById(R.id.rent_name);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the item at the current position
        HelperClassRents item = data.get(position);
        // Check if the item is not null
        if(item != null){
            // Set the costume name to the TextView in the view holder
            holder.rent_name.setText(item.getRent_costume_name());
            // Log the item for debugging purposes
            Log.d("debug", "item: "+item);
            // Set the costume name to the TextView in the view holder
            holder.rent_name.setText(item.getRent_costume_name());
        }
    }

    @Override
    public int getItemCount() {return data.size();}
}
