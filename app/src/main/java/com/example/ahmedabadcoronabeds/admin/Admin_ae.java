package com.example.ahmedabadcoronabeds.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ahmedabadcoronabeds.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Admin_ae extends AppCompatActivity {
    EditText name,code,address,category,update,mob_num,to2,vo2,tib,vib,ticu,vicu,ticuv,vicuv;
    Button submit_data;
    private DatabaseReference hosRef;
    int cnt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_ae);

        name = findViewById(R.id.name);
        code = findViewById(R.id.code);
        address = findViewById(R.id.address);

        category = findViewById(R.id.category);
        update = findViewById(R.id.update);
        mob_num = findViewById(R.id.mob_num);

        to2 = findViewById(R.id.to2);
        vo2 = findViewById(R.id.vo2);
        tib = findViewById(R.id.tib);
        vib = findViewById(R.id.vib);

        ticu = findViewById(R.id.ticu);
        vicu = findViewById(R.id.vicu);
        ticuv = findViewById(R.id.ticuv);
        vicuv = findViewById(R.id.vicuv);

        submit_data=findViewById(R.id.submit_data);

        hosRef = FirebaseDatabase.getInstance().getReference().child("Hospitals");

        hosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for (DataSnapshot ds : snapshot.getChildren())
                {
                    cnt++;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        submit_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1 =name.getText().toString();
                String code1 =code.getText().toString();
                String address1 =address.getText().toString();
                String category1 =category.getText().toString();
                String update1 =update.getText().toString();
                String mobile =mob_num.getText().toString();

                int to2_1 = Integer.parseInt(to2.getText().toString());
                int vo2_1 = Integer.parseInt(vo2.getText().toString());
                int tib_1 = Integer.parseInt(tib.getText().toString());
                int vib_1 = Integer.parseInt(vib.getText().toString());

                int ticu_1 = Integer.parseInt(ticu.getText().toString());
                int vicu_1 = Integer.parseInt(vicu.getText().toString());
                int ticuv_1 = Integer.parseInt(ticuv.getText().toString());
                int vicuv_1 = Integer.parseInt(vicuv.getText().toString());

                HashMap hashMap = new HashMap<>();

                hashMap.put("Name",name1);
                hashMap.put("Code",code1);
                hashMap.put("Address",address1);
                hashMap.put("Category",category1);
                hashMap.put("LastUpdateOn",update1);
                hashMap.put("PhoneNo",mobile);

                hashMap.put("TO2",to2_1);
                hashMap.put("VO2",vo2_1);
                hashMap.put("TIB",tib_1);
                hashMap.put("VIB",vib_1);

                hashMap.put("TICU",ticu_1);
                hashMap.put("VICU",vicu_1);
                hashMap.put("TICUV",ticuv_1);
                hashMap.put("VICUV",vicuv_1);

                cnt = cnt+1;
                Toast.makeText(Admin_ae.this, "=>"+cnt, Toast.LENGTH_SHORT).show();
                hosRef.child(String.valueOf(cnt)).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o)
                    {
                        Toast.makeText(Admin_ae.this, "Data Inserted", Toast.LENGTH_SHORT).show();

                    }
                });




            }
        });
    }
}