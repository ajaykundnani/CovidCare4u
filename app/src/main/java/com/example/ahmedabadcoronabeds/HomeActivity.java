package com.example.ahmedabadcoronabeds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.content.ContentValues.TAG;

public class HomeActivity extends AppCompatActivity {

    //Dialog popd;
    private Spinner spinner;
    LinearLayout l3,l6;
    private TextView n1,n2,n3,n4,n5,n6,tv1,tv5;
    private DatabaseReference bedsRef;
    String category="Private";

    int TotalICU,TotalO2,AvailableICU,AvailableO2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        spinner=findViewById(R.id.spinner);
        n1=findViewById(R.id.n1);
        n2=findViewById(R.id.n2);
        n3=findViewById(R.id.n3);
        n4=findViewById(R.id.n4);
        n5=findViewById(R.id.n5);
        n6=findViewById(R.id.n6);
        tv1=findViewById(R.id.tv1);
        tv5=findViewById(R.id.tv5);
        l3=findViewById(R.id.l3);
        l6=findViewById(R.id.l6);


        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(HomeActivity.this,ICUBedsActivity.class);
                intent.putExtra("category",category);
                intent.putExtra("title",tv1.getText().toString());
                startActivity(intent);

            }
        });

        l6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(HomeActivity.this,ICUBedsActivity.class);
                intent.putExtra("category",category);
                intent.putExtra("title",tv5.getText().toString());
                startActivity(intent);


            }
        });





        List<String> categories = new ArrayList<>();
        categories.add("Private");
        categories.add("Rural");
        categories.add("AMC");


        ArrayAdapter<String> dataAdaptor;
        dataAdaptor=new ArrayAdapter(this, android.R.layout.simple_spinner_item,categories);

        dataAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdaptor);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                String item = adapterView.getItemAtPosition(i).toString();
                category=item;
                TotalICU=0;
                AvailableICU=0;
                TotalO2=0;
                AvailableO2=0;

                totalICUBeds();
                availableICUBeds();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        totalICUBeds();
        availableICUBeds();
        occupiedICUBeds();

      /*  popd = new Dialog(this);

        popd.setContentView(R.layout.custompopup);
        popd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popd.show();*/








    }

    private void occupiedICUBeds()
    {


    }

    private void availableICUBeds()
    {
        bedsRef = FirebaseDatabase.getInstance().getReference().child(category);
        bedsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren() )
                {
                    String beds = String.valueOf(dataSnapshot1.child("icu_b").getValue());
                    int i = Integer.parseInt(beds);
                    AvailableICU = AvailableICU+i;
                }
                n3.setText(String.valueOf(AvailableICU));

                for (DataSnapshot dataSnapshot1 : snapshot.getChildren() )
                {
                    String o2 = String.valueOf(dataSnapshot1.child("available").getValue());
                    int i = Integer.parseInt(o2);
                    AvailableO2 = AvailableO2+i;
                }
                n6.setText(String.valueOf(AvailableO2));



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void totalICUBeds()
    {
        bedsRef = FirebaseDatabase.getInstance().getReference().child(category);
        bedsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren() )
                {
                    String beds = String.valueOf(dataSnapshot1.child("total_icu_b").getValue());
                    int i = Integer.parseInt(beds);
                    TotalICU = TotalICU+i;
                }
                n1.setText(String.valueOf(TotalICU));

                for (DataSnapshot dataSnapshot2 : snapshot.getChildren())
                {
                    String o2 = String.valueOf(dataSnapshot2.child("total_available").getValue());
                    int i = Integer.parseInt(o2);
                    TotalO2=TotalO2+i;
                }
                n4.setText(String.valueOf(TotalO2));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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