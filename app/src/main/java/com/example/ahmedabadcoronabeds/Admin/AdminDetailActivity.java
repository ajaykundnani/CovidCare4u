package com.example.ahmedabadcoronabeds.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ahmedabadcoronabeds.Models.Hospital;
import com.example.ahmedabadcoronabeds.R;
import com.example.ahmedabadcoronabeds.ViewHolder.HospitalHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminDetailActivity extends AppCompatActivity
{
    RecyclerView r_details;
    RecyclerView.LayoutManager layoutManager;
    private DatabaseReference hospitalRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detail);

        hospitalRef= FirebaseDatabase.getInstance().getReference().child("Hospital");


        r_details=findViewById(R.id.r_details);
        r_details.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        r_details.setLayoutManager(layoutManager);


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Hospital> options = new FirebaseRecyclerOptions.Builder<Hospital>()
                .setQuery(hospitalRef,Hospital.class)
                .build();

        FirebaseRecyclerAdapter<Hospital, HospitalHolder> adapter= new FirebaseRecyclerAdapter<Hospital, HospitalHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull HospitalHolder holder, int position, @NonNull Hospital model)
            {
                holder.detail_l_name.setText(model.getHospital_name());
                holder.detail_code.setText(model.getCode());

            }

            @NonNull
            @Override
            public HospitalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_layout,parent,false);
                HospitalHolder holder = new HospitalHolder(view);
                return  holder;
            }
        };


        r_details.setAdapter(adapter);
        adapter.startListening();

    }
}