package com.example.ahmedabadcoronabeds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ahmedabadcoronabeds.Models.Hospital;
import com.example.ahmedabadcoronabeds.ViewHolder.HospitalHolder;
import com.example.ahmedabadcoronabeds.databinding.ActivityDashboardBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity
{

    private RecyclerView recyclerView;
    private TextView main_title;
    private String title;
    private EditText search;
    private List<Hospital> hospitals = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        main_title=findViewById(R.id.main_title);
        search=findViewById(R.id.search);
        title = getIntent().getStringExtra("title");
        main_title.setText(title);
        String category = getIntent().getStringExtra("category");

        LoadDatabase();
    }

    private void LoadDatabase()
    {
        DatabaseReference dataReference = FirebaseDatabase.getInstance().getReference().child("Hospitals");
        dataReference.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                hospitals.clear();
                for (DataSnapshot HospitalSnapshot: snapshot.getChildren()) {
                    Hospital hospital = HospitalSnapshot.getValue(Hospital.class);
                    hospitals.add(hospital);
                }
                //Bind Method Calling
                BindRecyclerView();
                System.out.println(hospitals);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Error",error.getMessage());
            }
        });
    }

    private void BindRecyclerView(){
        //Recycler View Code
        recyclerView =findViewById(R.id.recyclerview_home);

        HospitalHolder hospitalHolder = new HospitalHolder();
        hospitalHolder.setHospitalList(hospitals);
        recyclerView.setAdapter(hospitalHolder);
    }

}