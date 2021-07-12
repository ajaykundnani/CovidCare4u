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

import com.example.ahmedabadcoronabeds.Models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Action_User extends AppCompatActivity {

    private List<User> users = new ArrayList<>();
    private EditText EditText_Name, EditText_MobileNo, EditText_Password,EditText_Role,EditText_HCode,EditText_Age;
    private DatabaseReference dataReference = FirebaseDatabase.getInstance().getReference().child("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action__user);


        EditText_Name = findViewById(R.id.Name);
        EditText_MobileNo = findViewById(R.id.MobileNo);
        EditText_Password = findViewById(R.id.Password);
        EditText_Role = findViewById(R.id.Role);
        EditText_HCode = findViewById(R.id.HCode);
        EditText_Age = findViewById(R.id.Age);


        Intent intent = getIntent();
        if(intent.getExtras() != null){

            User user = (User) intent.getSerializableExtra("data_user");

            EditText_Name.setText(user.getName());
            EditText_MobileNo.setText(user.getMobileNo());
            EditText_Password.setText(user.getPassword());
            EditText_Role.setText(user.getRole());
            EditText_HCode.setText(user.getHospitalCode());
            EditText_Age.setText(user.getAge());

        }
        LoadDatabase();
    }

    private void LoadDatabase()
    {
        dataReference.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users.clear();
                for (DataSnapshot UserSnapshot: snapshot.getChildren()) {
                    User user = UserSnapshot.getValue(User.class);
                    users.add(user);
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
        for (int counter = 0; counter < users.size(); counter++) {
            Map_users.put(Integer.toString(counter),users.get(counter));
        }
        dataReference.updateChildren(Map_users).
                addOnSuccessListener(aVoid -> {
            Toast.makeText(getApplicationContext(),SuccessMsg,Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(aVoid ->{
                    Toast.makeText(getApplicationContext(),FailuerMsg,Toast.LENGTH_SHORT).show();
        });
        finish();
    }



    public void Update_User(View view){

        for (User u : users) {
            if (u.getMobileNo().equals(EditText_MobileNo.getText().toString())) {

                u.setName(EditText_Name.getText().toString());
                u.setMobileNo(EditText_MobileNo.getText().toString());
                u.setPassword(EditText_Password.getText().toString());
                u.setRole(EditText_Role.getText().toString());
                u.setHospitalCode(EditText_HCode.getText().toString());
                u.setAge(EditText_Age.getText().toString());

                UpdateDatabase("Updated SuccessFully","Something Wrong,Try Later");

            }
        }
    }

    public void Delete_User(View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(Action_User.this);
        builder.setTitle("Are you Sure?");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog,int which){
                Query query = dataReference;
                for (int i = 0; i < users.size(); i++){
                    if(users.get(i).getMobileNo().matches(EditText_MobileNo.getText().toString())){
                        users.remove(i);
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