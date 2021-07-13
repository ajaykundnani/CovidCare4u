package com.example.ahmedabadcoronabeds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmedabadcoronabeds.Models.Hospital;
import com.example.ahmedabadcoronabeds.Models.User;
import com.example.ahmedabadcoronabeds.ViewHolder.HospitalHolder;
import com.example.ahmedabadcoronabeds.ViewHolder.UserHolder;
import com.example.ahmedabadcoronabeds.databinding.ActivityDashboardBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class HomeActivity extends AppCompatActivity  implements HospitalHolder.SelectedHospital
{

    private RecyclerView recyclerView;
    private TextView main_title;
    private String title,Category;
    private EditText search;
    private List<Hospital> hospitals = new ArrayList<>();
    String role;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        main_title=findViewById(R.id.main_title);
        search=findViewById(R.id.search);
        title = getIntent().getStringExtra("title");
        main_title.setText(title);
        Category = getIntent().getStringExtra("Category");

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

        List<Hospital> temp = new ArrayList<>();
        temp.clear();
        for(Hospital h :hospitals){
            if(h.getCategory().matches(Category)){
                temp.add(h);
            }
        }


        HospitalHolder hospitalHolder = new HospitalHolder();
        hospitalHolder.setHospitalList(temp,this);

        recyclerView.setAdapter(hospitalHolder);
    }

    @Override
    public void selectedHospital(Hospital hospital) {
        startActivity(new Intent(HomeActivity.this,Action_hospital.class).putExtra("data_hospital",hospital).putExtra("role",role));
    }
}