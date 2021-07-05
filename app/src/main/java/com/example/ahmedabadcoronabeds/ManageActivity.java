package com.example.ahmedabadcoronabeds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ahmedabadcoronabeds.Models.Hospital;
import com.example.ahmedabadcoronabeds.Models.User;
import com.example.ahmedabadcoronabeds.ViewHolder.HospitalHolder;
import com.example.ahmedabadcoronabeds.ViewHolder.UserHolder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ManageActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    private EditText from, to;
    private Button filter;
    private List<User> users = new ArrayList<>();

    private DatabaseReference dataReference = FirebaseDatabase.getInstance().getReference().child("Users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        from = (EditText)findViewById(R.id.From);
        to = (EditText)findViewById(R.id.To);
        filter = (Button)findViewById(R.id.filter);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Hello");
                String F = from.getText().toString();
                String T = to.getText().toString();
                List<User> filtered = new ArrayList<>();
                for(User u : users){
                    if(Integer.parseInt(u.getAge()) >= Integer.parseInt(F)){
                        if (Integer.parseInt(u.getAge()) <= Integer.parseInt(T)) {
                            filtered.add(u);
                            recyclerView = findViewById(R.id.recyclerview_manage);
                            UserHolder userHolder = new UserHolder();
                            userHolder.setUsersList(filtered);
                            recyclerView.setAdapter(userHolder);
                        }
                    }
                }
            }
        });

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
                BindRecyclerView();
                System.out.println(users);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Error",error.getMessage());
            }
        });
    }
    private void BindRecyclerView(){
        //Recycler View Code
        recyclerView =findViewById(R.id.recyclerview_manage);

        UserHolder userHolder = new UserHolder();
        Collections.sort(users,new Sortbyage());
        userHolder.setUsersList(users);
        recyclerView.setAdapter(userHolder);
    }

}
class Sortbyage implements Comparator<User>
{
    public int compare(User a, User b)
    {
        return Integer.parseInt(a.getAge()) - Integer.parseInt(b.getAge());
    }

}