package com.example.ahmedabadcoronabeds.Admin;

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

public class AdminActivity extends AppCompatActivity {
    private Button submit;
    private EditText hospital_name,hospital_address,hospital_contact,hospital_Code,hospital_category;
    private DatabaseReference hospitalRef,hRef;


    private String demo = "null";


    long myid=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        submit=findViewById(R.id.submit);
        hospital_name=findViewById(R.id.hospital_name);
        hospital_address=findViewById(R.id.hospital_address);
        hospital_contact=findViewById(R.id.hospital_contact);
        hospital_Code=findViewById(R.id.hospital_Code);
        hospital_category=findViewById(R.id.hospital_category);



        hospitalRef= FirebaseDatabase.getInstance().getReference().child("Hospital");




        hospitalRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if(snapshot.exists())
                {
                    myid=(snapshot.getChildrenCount());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });







        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (TextUtils.isEmpty(hospital_name.getText().toString()))
                {
                    Toast.makeText(AdminActivity.this, "Enter Hospital Name", Toast.LENGTH_SHORT).show();
                }
               else if (TextUtils.isEmpty(hospital_address.getText().toString()))
                {
                    Toast.makeText(AdminActivity.this, "Enter Address", Toast.LENGTH_SHORT).show();
                }
               else if (TextUtils.isEmpty(hospital_contact.getText().toString()))
                {
                    Toast.makeText(AdminActivity.this, "Enter Contact Number", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(hospital_Code.getText().toString()))
                {
                    Toast.makeText(AdminActivity.this, "Enter Code", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(hospital_category.getText().toString()))
                {
                    Toast.makeText(AdminActivity.this, "Enter Category", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    String name=hospital_name.getText().toString();
                    String address = hospital_address.getText().toString();
                    String num = hospital_contact.getText().toString();
                    String code = hospital_Code.getText().toString();
                    String category = hospital_category.getText().toString();


                    HashMap<String,Object> hashMap = new HashMap<>();
                    hashMap.put("hospital_name",name);
                    hashMap.put("hospital_address",address);
                    hashMap.put("hospital_contactno",num);


                    hashMap.put("available",demo);
                    hashMap.put("total_available",demo);

                    hashMap.put("time",demo);
                    hashMap.put("time_icu_b",demo);

                    hashMap.put("icu_b",demo);
                    hashMap.put("total_icu_b",demo);

                    hashMap.put("code",code);

                    /*hospitalRef.child(name).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid)
                        {
                            Toast.makeText(AdminActivity.this, "Hospital Added Successfully", Toast.LENGTH_SHORT).show();
                            hospital_name.setText("");
                            hospital_address.setText("");
                            hospital_contact.setText("");
                            hospital_Code.setText("");
                        }
                    });*/

                    hRef=FirebaseDatabase.getInstance().getReference().child(category);

                    hRef.child(name).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid)
                        {
                            Toast.makeText(AdminActivity.this, "All Record Addedd", Toast.LENGTH_SHORT).show();

                        }
                    });




                }



            }
        });



    }
}