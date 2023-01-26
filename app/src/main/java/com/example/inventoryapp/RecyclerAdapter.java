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

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<HelperClassCostumes> data;
    private DatabaseReference databaseReference;

    public RecyclerAdapter(List<HelperClassCostumes> data){
        this.data = data;
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("costumes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    HelperClassCostumes helperClassCostumes = snapshot1.getValue(HelperClassCostumes.class);
                    RecyclerAdapter.this.data.add(helperClassCostumes);
                }
                notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("Firebase Error:", "Failed to read value.", error.toException());
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView costume_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            costume_name = itemView.findViewById(R.id.costumes_name);
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custome_design, parent, false);
        ViewHolder holder = new ViewHolder(itemView);
        holder.costume_name = itemView.findViewById(R.id.costume_name);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        HelperClassCostumes item = data.get(position);
        if(item != null){
            holder.costume_name.setText(item.getCostume_name());
        }
        Log.d("debug", "item: "+item);
        holder.costume_name.setText(item.getCostume_name());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
