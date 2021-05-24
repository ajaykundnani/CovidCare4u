package com.example.ahmedabadcoronabeds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ahmedabadcoronabeds.Admin.AdminActivity;
import com.example.ahmedabadcoronabeds.Admin.AdminDashboardActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    //Dialog popd;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        spinner=findViewById(R.id.spinner);

        List<String> categories = new ArrayList<>();
        categories.add("AMC");
        categories.add("Private");
        categories.add("Rural");

        ArrayAdapter<String> dataAdaptor;
        dataAdaptor=new ArrayAdapter(this, android.R.layout.simple_spinner_item,categories);

        dataAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdaptor);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(HomeActivity.this, "You Slected"+item, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



      /*  popd = new Dialog(this);

        popd.setContentView(R.layout.custompopup);
        popd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popd.show();*/








    }


    public void Onpop(View view)
    {
        TextView closepop;
        closepop = (TextView) findViewById(R.id.closepop);
        closepop.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
             {

               // popd.dismiss();
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.oxygen2:
                Intent intent=new Intent(HomeActivity.this,OxygenActivity.class);
                startActivity(intent);
                break;
            case R.id.icu2:
                Intent intent2=new Intent(HomeActivity.this,ICUBedsActivity.class);
                startActivity(intent2);
                break;

            case R.id.pri2:
                startActivity(new Intent(HomeActivity.this,DoctorActivity.class));
                break;
            case R.id.dev2:
                AlertDialog.Builder mydialog = new AlertDialog.Builder(HomeActivity.this);
                mydialog.setTitle("Enter Code");

                final EditText password = new EditText(HomeActivity.this);
                password.setInputType(InputType.TYPE_CLASS_PHONE);

                mydialog.setView(password);

                mydialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String mypass = password.getText().toString();

                        if (mypass.equals("123"))
                        {
                            startActivity(new Intent(HomeActivity.this, AdminDashboardActivity.class));
                        }
                        else
                        {
                            Toast.makeText(HomeActivity.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                mydialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.cancel();

                    }
                });

                mydialog.show();

                break;

        }
        return super.onOptionsItemSelected(item);
    }
}