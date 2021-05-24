package com.example.ahmedabadcoronabeds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class UpdateActivity extends AppCompatActivity
{
    ImageView oxygen_update,icu_update;
    private String saveCurrentDate,saveCurrentTime,FireTime;
    Button Update;
    TextView update_hospital_name;

    EditText e_total_update,e_available_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        oxygen_update = findViewById(R.id.oxygen_update);
        icu_update = findViewById(R.id.icu_update);
        Update = findViewById(R.id.update);
        e_total_update = findViewById(R.id.e_total_update);
        e_available_update = findViewById(R.id.e_available_update);

        update_hospital_name=findViewById(R.id.update_hospital_name);

        String name = getIntent().getStringExtra("name");

        update_hospital_name.setText(name);

        GetIcuInfo(e_total_update,e_available_update);



        oxygen_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                icu_update.setVisibility(View.VISIBLE);
                oxygen_update.setVisibility(View.INVISIBLE);
                Update.setText("UPDATE Oxygen");

                GetOxygenInfo(e_total_update,e_available_update);



            }
        });

        icu_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {


                icu_update.setVisibility(View.INVISIBLE);
                oxygen_update.setVisibility(View.VISIBLE);
                Update.setText("UPDATE ICU Beds");

                GetIcuInfo(e_total_update,e_available_update);



            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (TextUtils.isEmpty(e_total_update.getText().toString()))
                {
                    Toast.makeText(UpdateActivity.this, "Enter Total", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(e_available_update.getText().toString()))
                {
                    Toast.makeText(UpdateActivity.this, "Enter Available", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    UpdateInfo(e_total_update,e_available_update);


                }

            }
        });
    }

    private void UpdateInfo(EditText e_total_update, EditText e_available_update)
    {
        DatabaseReference UpdateRef = FirebaseDatabase.getInstance().getReference().child("Hospital").child(update_hospital_name.getText().toString());
        String check = Update.getText().toString();

        if (check.equals("UPDATE ICU Beds"))
        {
            Calendar calendar = Calendar.getInstance();

            SimpleDateFormat currentDate = new SimpleDateFormat("MM dd,yyyy");
            saveCurrentDate=currentDate.format(calendar.getTime());

            SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
            saveCurrentTime=currentTime.format(calendar.getTime());

            FireTime = saveCurrentDate+saveCurrentTime;



            HashMap<String,Object> hashMap1 = new HashMap<>();
            hashMap1.put("total_icu_b",e_total_update.getText().toString());
            hashMap1.put("icu_b",e_available_update.getText().toString());
            hashMap1.put("time_icu_b",FireTime);

            UpdateRef.updateChildren(hashMap1).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid)
                {
                    Toast.makeText(UpdateActivity.this, "Update Successfully", Toast.LENGTH_SHORT).show();

                }
            });

        }
        else
        {
            Calendar calendar = Calendar.getInstance();

            SimpleDateFormat currentDate = new SimpleDateFormat("MM dd,yyyy");
            saveCurrentDate=currentDate.format(calendar.getTime());

            SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
            saveCurrentTime=currentTime.format(calendar.getTime());

            FireTime = saveCurrentDate+saveCurrentTime;

            HashMap<String,Object> hashMap2 = new HashMap<>();
            hashMap2.put("total_available",e_total_update.getText().toString());
            hashMap2.put("available",e_available_update.getText().toString());
            hashMap2.put("time",FireTime);


            UpdateRef.updateChildren(hashMap2).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid)
                {
                    Toast.makeText(UpdateActivity.this, "Update Successfully", Toast.LENGTH_SHORT).show();

                }
            });


        }

    }

    private void GetOxygenInfo(EditText e_total_update, EditText e_available_update)
    {
        DatabaseReference oxygenRef = FirebaseDatabase.getInstance().getReference().child("Hospital").child(update_hospital_name.getText().toString());

        oxygenRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                String totaloxygen = String.valueOf(snapshot.child("total_available").getValue());
                String availableoxygen = String.valueOf(snapshot.child("available").getValue());

                e_total_update.setText(totaloxygen);
                e_available_update.setText(availableoxygen);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void GetIcuInfo(EditText e_total_update, EditText e_available_update)
    {
        DatabaseReference ICURef = FirebaseDatabase.getInstance().getReference().child("Hospital").child(update_hospital_name.getText().toString());

        ICURef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                String totalbeds = String.valueOf(snapshot.child("total_icu_b").getValue());
                String availabebeds = String.valueOf(snapshot.child("icu_b").getValue());

                e_total_update.setText(totalbeds);
                e_available_update.setText(availabebeds);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}