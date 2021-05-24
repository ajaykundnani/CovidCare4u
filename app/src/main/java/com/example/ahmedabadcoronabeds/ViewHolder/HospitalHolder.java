package com.example.ahmedabadcoronabeds.ViewHolder;

import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ahmedabadcoronabeds.R;

public class HospitalHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public TextView l_name,l_available,l_time,total_available,dr_l_name,detail_l_name,detail_code;


    public HospitalHolder(@NonNull View itemView) {
        super(itemView);

        l_name=itemView.findViewById(R.id.l_name);
        l_available=itemView.findViewById(R.id.l_available);
        l_time=itemView.findViewById(R.id.l_time);
        total_available=itemView.findViewById(R.id.total_available);
        dr_l_name=itemView.findViewById(R.id.dr_l_name);
        detail_l_name=itemView.findViewById(R.id.detail_l_name);
        detail_code=itemView.findViewById(R.id.detail_code);




    }

    @Override
    public void onClick(View view)
    {


    }
}
