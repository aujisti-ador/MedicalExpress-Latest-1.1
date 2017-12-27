package com.ador.medicalexpress.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ador.medicalexpress.models.DoctorData;
import com.ador.medicalexpress.R;

import java.util.ArrayList;

/**
 * Created by DORBESH on 1/7/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>{

    ArrayList<DoctorData> arrayList = new ArrayList<>();

    public RecyclerAdapter(ArrayList<DoctorData> arrayList){

        this.arrayList = arrayList;

    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        holder.phone.setText(arrayList.get(position).getPhone());
        holder.location.setText(arrayList.get(position).getLocation());
        holder.chamber.setText(arrayList.get(position).getChamber());
        holder.designation.setText(arrayList.get(position).getDesignation());
        holder.name.setText(arrayList.get(position).getName());
        holder.qualification.setText(arrayList.get(position).getQualification());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView name,qualification,designation,chamber,location,phone,speciality,email,web;

        public RecyclerViewHolder(View v) {
            super(v);

            name = (TextView)v.findViewById(R.id.drName);
            qualification = (TextView)v.findViewById(R.id.qualification);
            designation = (TextView)v.findViewById(R.id.designation);
            chamber = (TextView)v.findViewById(R.id.chamber);
            location = (TextView)v.findViewById(R.id.location);
            phone = (TextView)v.findViewById(R.id.phone);
        }
    }
}
