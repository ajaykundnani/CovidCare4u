package com.example.ahmedabadcoronabeds;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ahmedabadcoronabeds.Models.Hospital;
import com.example.ahmedabadcoronabeds.ViewHolder.HospitalHolder;

public class HomeActivity extends AppCompatActivity
{

    private RecyclerView recyclerView;
    private TextView main_title;
    private String title;
    private EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        main_title=findViewById(R.id.main_title);
        search=findViewById(R.id.search);
        title = getIntent().getStringExtra("title");
        main_title.setText(title);
        String category = getIntent().getStringExtra("category");

        //Recycler View Code

        //Static Data
        Hospital []hospitals = new Hospital[3];
        hospitals[0] = new Hospital("AMC Hospital","A1M2C3","M.G.Road","AMC","1/2/2021",123456789,1200,600,1200,600,1200,600,1200,600);
        hospitals[1] = new Hospital("Private Hospital","P1V2T3","M.G.Road","Private","1/2/2021",123456789,1200,600,1200,600,1200,600,1200,600);
        hospitals[2] = new Hospital("Rural Hospital","R1U2L3","M.G.Road","Rural","1/2/2021",123456789,1200,600,1200,600,1200,600,1200,600);

        recyclerView =findViewById(R.id.recyclerview_home);

        HospitalHolder hospitalHolder = new HospitalHolder();
        hospitalHolder.setHospitalList(hospitals);
        recyclerView.setAdapter(hospitalHolder);
    }

}