package com.example.ahmedabadcoronabeds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ahmedabadcoronabeds.Models.Hospital;
import com.example.ahmedabadcoronabeds.admin.Admin_ae;
import com.example.ahmedabadcoronabeds.databinding.ActivityDashboardBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    //Global Veriables
    private List<Hospital> hospitals = new ArrayList<>();
    private String SelectedCategory = "AMC";
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //DataBinding
        ActivityDashboardBinding activityDashboardBinding = DataBindingUtil.setContentView(this,R.layout.activity_dashboard);

        //By Default Bind 0 to all fields
        DefaultBindToDashboard(activityDashboardBinding);

        //Category Spinner
        Spinner spinner = (Spinner)findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SelectedCategory = parent.getItemAtPosition(position).toString();
                System.out.println(SelectedCategory);
                BindToDashboard(SelectedCategory,activityDashboardBinding);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.category,R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Load firebase Database
        LoadDatabase(activityDashboardBinding);
    }


    //FireBase Database Fetching
    private void LoadDatabase(ActivityDashboardBinding acb)
    {
        DatabaseReference dataReference = FirebaseDatabase.getInstance().getReference().child("Hospitals");
        dataReference.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                hospitals.clear();
                for (DataSnapshot HospitalSnapshot: snapshot.getChildren()) {
                    Hospital hospital = HospitalSnapshot.getValue(Hospital.class);
                    hospitals.add(hospital);
                }
                //Bind Method Calling
                BindToDashboard(SelectedCategory, acb);
                System.out.println(hospitals);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Error",error.getMessage());
            }
        });
    }

    //Default Bind to dashboard
    private void DefaultBindToDashboard(ActivityDashboardBinding adb){
        Obj_dashboard obj_dashboard = new Obj_dashboard("0","0","0","0","0","0","0","0");
        adb.setDashboard(obj_dashboard);
    }

    //Binding Data to Dashboard
    private void BindToDashboard(String Category, ActivityDashboardBinding adb){
        int TO2=0,VO2=0,TIB=0,VIB=0,TICU=0,VICU=0,TICUV=0,VICUV=0;
        for(Hospital h : hospitals){
            if(h.getCategory().equals(Category)){
                TO2 += (int)Integer.parseInt(h.getTO2());
                VO2 += (int)Integer.parseInt(h.getVO2());
                TIB += (int)Integer.parseInt(h.getTIB());
                VIB += (int)Integer.parseInt(h.getVIB());
                TICU += (int)Integer.parseInt(h.getTICU());
                VICU += (int)Integer.parseInt(h.getVICU());
                TICUV += (int)Integer.parseInt(h.getTICUV());
                VICUV += (int)Integer.parseInt(h.getVICUV());
            }
        }
        Obj_dashboard obj_dashboard = new Obj_dashboard(Integer.toString(TO2),Integer.toString(VO2),Integer.toString(TIB),Integer.toString(VIB),Integer.toString(TICU),Integer.toString(VICU),Integer.toString(TICUV),Integer.toString(VICUV));
        adb.setDashboard(obj_dashboard);
    }

    //Move To Home activity
    public void MoveToHome(View view){
        Intent intent = new Intent(DashboardActivity.this,HomeActivity.class);
        startActivity(intent);
    }

    //Menu Handaling
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_menu,menu);

        sharedPreferences = getSharedPreferences("User",MODE_PRIVATE);
        if(sharedPreferences.contains("role")){
            if(sharedPreferences.getString("role","") == "SuperAdmin") {
                menu.findItem(R.id.add_new_hospital).setVisible(true);
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.signup:
                startActivity(new Intent(DashboardActivity.this, SignupActivity.class));
                break;
            case R.id.login:
                startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
                break;
            case R.id.manage:
                startActivity(new Intent(DashboardActivity.this, ManageActivity.class));
                break;

            case R.id.add_new_hospital:
                startActivity(new Intent(DashboardActivity.this, Admin_ae.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}