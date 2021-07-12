package com.example.ahmedabadcoronabeds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ahmedabadcoronabeds.Models.Hospital;
import com.example.ahmedabadcoronabeds.Models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Action_hospital extends AppCompatActivity {

    private List<Hospital> hospitals = new ArrayList<>();
    private EditText EditText_Name, EditText_Code,EditText_Address,EditText_Category,EditText_LUO,EditText_MobileNo,
            EditText_TO2,EditText_VO2,EditText_TIB,EditText_VIB,EditText_TICU,EditText_VICU,EditText_TICUV,
            EditText_VICUV;
    Button remove_hospital,update_hospital;
    private DatabaseReference dataReference = FirebaseDatabase.getInstance().getReference().child("Hospitals");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_action_hospital);

        remove_hospital = findViewById(R.id.remove_hospital);
        update_hospital = findViewById(R.id.update_hospital);
        String role = getIntent().getStringExtra("role");
        if (role.equals("User"))
        {
            remove_hospital.setVisibility(View.INVISIBLE);
            update_hospital.setVisibility(View.INVISIBLE);
            remove_hospital.setEnabled(false);
            update_hospital.setEnabled(false);
        }
        else {
            remove_hospital.setVisibility(View.VISIBLE);
            update_hospital.setVisibility(View.VISIBLE);
            remove_hospital.setEnabled(true);
            update_hospital.setEnabled(true);
        }

        EditText_Name = (EditText) findViewById(R.id.Hname);
        EditText_Code = (EditText) findViewById(R.id.Hcode);
        EditText_Address = (EditText) findViewById(R.id.address);
        EditText_Category = (EditText) findViewById(R.id.category);
        EditText_LUO = (EditText) findViewById(R.id.lastUpdate);
        EditText_MobileNo = (EditText) findViewById(R.id.mob_num);
        EditText_TO2 = (EditText) findViewById(R.id.to2);
        EditText_VO2 = (EditText) findViewById(R.id.vo2);
        EditText_TIB = (EditText) findViewById(R.id.tib);
        EditText_VIB = (EditText) findViewById(R.id.vib);
        EditText_TICU = (EditText) findViewById(R.id.ticu);
        EditText_VICU = (EditText) findViewById(R.id.vicu);
        EditText_TICUV = (EditText) findViewById(R.id.ticuv);
        EditText_VICUV = (EditText) findViewById(R.id.vicuv);

        Intent intent = getIntent();
        Hospital hospital;
        if (!intent.getExtras().isEmpty()) {
            hospital = (Hospital) intent.getSerializableExtra("data_hospital");
            EditText_Name.setText(hospital.getName());
            EditText_Code.setText(hospital.getCode());
            EditText_Address.setText(hospital.getAddress());
            EditText_Category.setText(hospital.getCategory());
            EditText_LUO.setText(hospital.getLastUpdateOn());
            EditText_MobileNo.setText(hospital.getPhoneNo());
            EditText_TO2.setText(hospital.getTO2());
            EditText_VO2.setText(hospital.getVO2());
            EditText_TIB.setText(hospital.getTIB());
            EditText_VIB.setText(hospital.getVIB());
            EditText_TICU.setText(hospital.getTICU());
            EditText_VICU.setText(hospital.getVICU());
            EditText_TICUV.setText(hospital.getTICUV());
            EditText_VICUV.setText(hospital.getVICUV());
        }

        LoadDatabase();
    }

    private void LoadDatabase()
    {
        dataReference.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                hospitals.clear();
                for (DataSnapshot HospitalSnapshot: snapshot.getChildren()) {
                    Hospital hospital = HospitalSnapshot.getValue(Hospital.class);
                    hospitals.add(hospital);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Error",error.getMessage());
            }
        });
    }

    private void UpdateDatabase(String SuccessMsg,String FailuerMsg){
        //Hash Map
        HashMap<String, Object> Map_users = new HashMap<String, Object>();
        for (int counter = 0; counter < hospitals.size(); counter++) {
            Map_users.put(Integer.toString(counter),hospitals.get(counter));
        }
        dataReference.updateChildren(Map_users).
                addOnSuccessListener(aVoid -> {
                    Toast.makeText(getApplicationContext(),SuccessMsg,Toast.LENGTH_SHORT).show();
                }).addOnFailureListener(aVoid ->{
            Toast.makeText(getApplicationContext(),FailuerMsg,Toast.LENGTH_SHORT).show();
        });
        finish();
    }


    public void Update_Hospital(View view){

        for (Hospital h : hospitals) {
            if (h.getCode().equals(EditText_Code.getText().toString())) {

                h.setName(EditText_Name.getText().toString());
                h.setAddress(EditText_Address.getText().toString());
                h.setCategory(EditText_Category.getText().toString());
                h.setPhoneNo(EditText_MobileNo.getText().toString());
                h.setLastUpdateOn(EditText_LUO.getText().toString());
                h.setTO2(EditText_TO2.getText().toString());
                h.setVO2(EditText_VO2.getText().toString());
                h.setTIB(EditText_TIB.getText().toString());
                h.setVIB(EditText_VIB.getText().toString());
                h.setTICU(EditText_TICU.getText().toString());
                h.setVICU(EditText_VICU.getText().toString());
                h.setTICUV(EditText_TICUV.getText().toString());
                h.setVICUV(EditText_VICUV.getText().toString());


                UpdateDatabase("Updated SuccessFully","Something Wrong,Try Later");

            }
        }
    }

    public void Delete_Hospital(View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(Action_hospital.this);
        builder.setTitle("Are you Sure?");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog,int which){
                Query query = dataReference;
                for (int i = 0; i < hospitals.size(); i++){
                    if(hospitals.get(i).getCode().matches(EditText_Code.getText().toString())){
                        hospitals.remove(i);
                        query.equalTo(i);
                    }
                }

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                            snapshot.getRef().removeValue();
                        }
                        Toast.makeText(getApplicationContext(),"Deleted Successfully",Toast.LENGTH_SHORT).show();
                        UpdateDatabase("","");
                        finish();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(),"Failuer",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog,int which){
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
