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

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<HelperClassCostumes> data;
    private DatabaseReference databaseReference;

    public RecyclerAdapter(List<HelperClassCostumes> data){

        // Initialize the data list with the passed in list.
        this.data = data;

        // Getting an instance of the Firebase database and getting a reference to the "costumes" node.
        databaseReference = FirebaseDatabase.getInstance().getReference();

        // Adding a listener to the "costumes" node to retrieve data when the data changes.
        databaseReference.child("costumes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Iterating over the snapshot data of "costumes" node
                for (DataSnapshot snapshot1 : snapshot.getChildren()){

                    // Getting the data of each child of "costumes" node and converting it to HelperClassCostumes object
                    HelperClassCostumes helperClassCostumes = snapshot1.getValue(HelperClassCostumes.class);

                    // Adding the HelperClassCostumes object to the data list
                    RecyclerAdapter.this.data.add(helperClassCostumes);
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

    /**
     * ViewHolder class is used to hold the views that are displayed in each item of the RecyclerView.
     * It is used to cache the views and improve the performance of the RecyclerView.
     */
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView costume_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            costume_name = itemView.findViewById(R.id.costumes_name);
        }
    }

    /**This method is called when the RecyclerView needs a new
     RecyclerView.ViewHolder of the given type to represent an item.*/
    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflating the custom layout for the items in the recycler view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custome_design, parent, false);
        //Creating a new ViewHolder and passing the inflated layout to it
        ViewHolder holder = new ViewHolder(itemView);
        //Initializing the TextView in the inflated layout
        holder.costume_name = itemView.findViewById(R.id.costume_name);
        return holder;
    }

    /**This method binds the data in the list to the view holder.
    It is called for each item in the list and creates the view for each item*/
    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        // Get the item at the current position
        HelperClassCostumes item = data.get(position);
        // Check if the item is not null
        if(item != null){
            // Set the costume name to the TextView in the view holder
            holder.costume_name.setText(item.getCostume_name());
        }
        // Log the item for debugging purposes
        Log.d("debug", "item: "+item);
        // Set the costume name to the TextView in the view holder
        holder.costume_name.setText(item.getCostume_name());
    }

    /** This method returns the total number of items in the data set held by the adapter.
    his is used by the RecyclerView to determine how many items to display.*/
    @Override
    public int getItemCount() {
        return data.size();
    }
}
