package com.example.ahmedabadcoronabeds;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class LoginActivity extends AppCompatActivity {

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
        String result = randomString.generateAlphaNumeric(8);
        captcha.setText(result);
    }

    public void Refresh(View view){
        Captcha randomString = new Captcha();
        String result = randomString.generateAlphaNumeric(8);
        captcha.setText(result);
    }
    public void Login(View view){
        if(mobile.getText().toString().equals("0123")&&pass.getText().toString().equals("456")&&
                entered_captcha.getText().toString().equals(captcha.getText().toString())) {

            Toast.makeText(getApplicationContext(),"login success",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(LoginActivity.this,ManageActivity.class);
            startActivity(i);
        }
        else{
            Toast.makeText(getApplicationContext(),"invalid login",Toast.LENGTH_SHORT).show();
        }
    }
}

class Captcha {
    private String letters ="abcdefghijklmnopqrstuvwxyz";
    private String number ="0123456789";
    private String characters ="'@','_','-','&'";
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
