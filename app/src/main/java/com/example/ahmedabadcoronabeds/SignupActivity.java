package com.example.ahmedabadcoronabeds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    private List<User> users = new ArrayList<>();
    private EditText EditText_Name, EditText_MobileNo, EditText_Password;
    private Button Button_Signup;
    private DatabaseReference dataReference = FirebaseDatabase.getInstance().getReference().child("Users");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        EditText_Name = findViewById(R.id.Name);
        EditText_MobileNo = findViewById(R.id.MobileNo);
        EditText_Password = findViewById(R.id.Password);
        Button_Signup = findViewById(R.id.SignUp);



        Button_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = EditText_Name.getText().toString();
                String MobileNo = EditText_MobileNo.getText().toString();
                String Password = EditText_Password.getText().toString();

                if(IsNewUser(MobileNo)){
                    users.add(new User(EditText_Name.getText().toString(), EditText_Password.getText().toString(), "User","", EditText_MobileNo.getText().toString(),""));
                    System.out.println(users);

                    //Hash Map
                    HashMap<String, Object> Map_users = new HashMap<String, Object>();
                    for (int counter = 0; counter < users.size(); counter++) {
                        Map_users.put(Integer.toString(counter),users.get(counter));
                    }
                    dataReference.updateChildren(Map_users).addOnSuccessListener(aVoid -> {
                        Toast.makeText(getApplicationContext(),"SignUp Successfully.",Toast.LENGTH_SHORT).show();
                        EditText_Name.getText().clear();
                        EditText_Password.getText().clear();
                        EditText_MobileNo.getText().clear();
                    });
                }
            }
        });

        //DataBase Load
        LoadDatabase();
    }
    private void LoadDatabase()
    {
        dataReference.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users.clear();
                for (DataSnapshot HospitalSnapshot: snapshot.getChildren()) {
                    User user = HospitalSnapshot.getValue(User.class);
                    users.add(user);
                }
                //Bind Method Calling
                System.out.println(users);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Error",error.getMessage());
            }
        });
    }

    private boolean IsNewUser(String MobileNo){
        if(users.size() != 0){
            for (User u : users) {
                if (u.getMobileNo().equals(MobileNo)) {
                    Toast.makeText(getApplicationContext(),"User Exist Already",Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }
        return true;
    }
}