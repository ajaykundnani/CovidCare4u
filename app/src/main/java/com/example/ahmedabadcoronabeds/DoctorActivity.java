package com.example.ahmedabadcoronabeds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ahmedabadcoronabeds.Models.Hospital;
import com.example.ahmedabadcoronabeds.ViewHolder.HospitalHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DoctorActivity extends AppCompatActivity
{

    private DatabaseReference hospitalRef;
    private RecyclerView r_dr;
    RecyclerView.LayoutManager layoutManager;
    String code;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        hospitalRef= FirebaseDatabase.getInstance().getReference().child("Hospital");

        r_dr=findViewById(R.id.r_dr);
        r_dr.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        r_dr.setLayoutManager(layoutManager);


    }

    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<Hospital> options = new FirebaseRecyclerOptions.Builder<Hospital>()
                .setQuery(hospitalRef,Hospital.class)
                .build();

        FirebaseRecyclerAdapter<Hospital, HospitalHolder> adapter= new FirebaseRecyclerAdapter<Hospital, HospitalHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull HospitalHolder holder, int position, @NonNull Hospital model)
            {


                holder.dr_l_name.setText(model.getHospital_name());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        AlertDialog.Builder drDialog = new AlertDialog.Builder(DoctorActivity.this);
                        drDialog.setTitle("Enter Unique Code");


                        final EditText password = new EditText(DoctorActivity.this);
                        password.setInputType(InputType.TYPE_CLASS_TEXT);
                        password.setHint("enter here...");

                        drDialog.setView(password);
                        String name = model.getHospital_name();
                        hospitalRef = hospitalRef.child(name);

                        hospitalRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot)
                            {
                                 code = String.valueOf(snapshot.child("code").getValue());

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });




                        // Toast.makeText(ICUBedsActivity.this, "Hello", Toast.LENGTH_SHORT).show();

                        drDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                if (TextUtils.isEmpty(password.getText().toString()))
                                {
                                    Toast.makeText(DoctorActivity.this, "Enter Code", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    String mycode = password.getText().toString();

                                    if (code.equals(mycode))
                                    {
                                        Intent intent = new Intent(DoctorActivity.this,UpdateActivity.class);
                                        intent.putExtra("name",name);
                                        startActivity(intent);
                                        finish();

                                    }
                                    else
                                    {
                                        Toast.makeText(DoctorActivity.this, "Wrong Code", Toast.LENGTH_SHORT).show();
                                    }

                                }


                            }
                        });

                        drDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                dialogInterface.cancel();

                            }
                        });


                        drDialog.show();

                    }
                });









            }

            @NonNull
            @Override
            public HospitalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctorlayout,parent,false);
                HospitalHolder holder = new HospitalHolder(view);
                return  holder;
            }
        };

        r_dr.setAdapter(adapter);
        adapter.startListening();




    }
}