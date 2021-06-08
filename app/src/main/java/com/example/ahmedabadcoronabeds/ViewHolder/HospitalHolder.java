package com.example.ahmedabadcoronabeds.ViewHolder;

import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ahmedabadcoronabeds.Models.Hospital;
import com.example.ahmedabadcoronabeds.R;

import java.util.ArrayList;
import java.util.List;

public class HospitalHolder extends RecyclerView.Adapter<HospitalHolder.ViewHolder>{


    private List<Hospital> hospitals = new ArrayList<>();

    @NonNull
    @Override
    public HospitalHolder.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View cardview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_home,viewGroup,
                false);

        return new ViewHolder(cardview);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.hospital_name.setText(hospitals.get(i).getName());
    }


    @Override
    public int getItemCount() {
        return hospitals.size();
    }

    public void setHospitalList(List<Hospital> hospitals)
    {
        this.hospitals = hospitals;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView hospital_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            hospital_name = itemView.findViewById(R.id.hospital_name);

        }
    }
}
