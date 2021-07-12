package com.example.ahmedabadcoronabeds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmedabadcoronabeds.Models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoginActivity extends AppCompatActivity {

    private DatabaseReference dataReference = FirebaseDatabase.getInstance().getReference().child("Users");
    private List<User> users = new ArrayList<>();
    private  SharedPreferences sharedPreferences;
    EditText entered_captcha, mobile, pass;
    TextView captcha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        captcha = (TextView)findViewById(R.id.captcha);
        entered_captcha = (EditText) findViewById(R.id.enter_captcha);
        mobile = (EditText)findViewById(R.id.num);
        pass = (EditText)findViewById(R.id.pass);

        Captcha randomString = new Captcha();
        String result = randomString.generateAlphaNumeric(6);
        captcha.setText(result);

        FetchUsers();
    }

    private void FetchUsers()
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

    private boolean IsValidUser(String MobileNo, String Password){
        if(users.size() != 0){
            for (User u : users) {
                if (u.getMobileNo().equals(MobileNo) && u.getPassword().equals(Password)) {
                    sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("name",u.getName());
                    editor.putString("role",u.getRole());
                    editor.putString("mobileno",u.getMobileNo());
                    editor.putString("hospitalCode",u.getHospitalCode());
                    editor.putString("password",u.getPassword());
                    editor.putString("age",u.getAge());
                    editor.apply();
                    return true;
                }
            }
        }
        return false;
    }

    public void Refresh(View view){
        Captcha randomString = new Captcha();
        String result = randomString.generateAlphaNumeric(8);
        captcha.setText(result);
    }
    public void Login(View view){
        if(entered_captcha.getText().toString().equals(captcha.getText().toString())) {
            if (IsValidUser(mobile.getText().toString(), pass.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Welcome "+sharedPreferences.getString("role",""), Toast.LENGTH_SHORT).show();
                String name=sharedPreferences.getString("role","");
                Intent i = new Intent(LoginActivity.this, DashboardActivity.class);
                i.putExtra("role",name);
                startActivity(i);

            } else {
                Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "Invalid Captcha", Toast.LENGTH_SHORT).show();
        }
    }
}


class Captcha {
    private String letters ="abcdefghijklmnopqrstuvwxyz";
    private String number ="0123456789";
    private String characters ="@&_-";
    private char[] alphanumeric =(letters + letters.toUpperCase()+ number + characters).toCharArray();

    public String generateAlphaNumeric(int length){
        StringBuilder result = new StringBuilder();
        for(int i=0;i<length;i++){
            result.append(alphanumeric[new
                    Random().nextInt(alphanumeric.length)]);
        }
        return result.toString();
    }
}
