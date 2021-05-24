package com.example.ahmedabadcoronabeds.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ahmedabadcoronabeds.DoctorActivity;
import com.example.ahmedabadcoronabeds.Models.Hospital;
import com.example.ahmedabadcoronabeds.R;
import com.example.ahmedabadcoronabeds.ViewHolder.HospitalHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminDeleteActivity extends AppCompatActivity
{

    private DatabaseReference hospitalRef;
    private RecyclerView r_delete;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_delete);

        hospitalRef= FirebaseDatabase.getInstance().getReference().child("Hospital");

        r_delete=findViewById(R.id.r_delete);
        r_delete.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        r_delete.setLayoutManager(layoutManager);


    }

    @Override
    protected void onStart() {
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
                        AlertDialog.Builder deletedialog = new AlertDialog.Builder(AdminDeleteActivity.this);
                        deletedialog.setTitle(model.getHospital_name());
                        deletedialog.setMessage("Want to delete this hospital");
                        deletedialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                hospitalRef = hospitalRef.child(model.getHospital_name());
                                hospitalRef.removeValue();

                            }
                        });

                        deletedialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                dialogInterface.cancel();

                            }
                        });
                        deletedialog.show();



                    }
                });


            }

            @NonNull
            @Override
            public HospitalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctorlayout,parent,false);
                HospitalHolder holder = new HospitalHolder(view);
                return  holder;            }
        };

        r_delete.setAdapter(adapter);
        adapter.startListening();
    }
}