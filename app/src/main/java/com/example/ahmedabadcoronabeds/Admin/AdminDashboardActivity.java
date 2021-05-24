package com.example.ahmedabadcoronabeds.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.ahmedabadcoronabeds.R;

public class AdminDashboardActivity extends AppCompatActivity
{
    ImageView admins,deleteHospital,addHospital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        addHospital = findViewById(R.id.addHospital);
        deleteHospital = findViewById(R.id.deleteHospital);
        admins = findViewById(R.id.admins);

        addHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(AdminDashboardActivity.this,AdminActivity.class));

            }
        });

        admins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(AdminDashboardActivity.this,AdminDetailActivity.class));


            }
        });

        deleteHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(AdminDashboardActivity.this,AdminDeleteActivity.class));


            }
        });




    }
}