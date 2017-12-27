package com.ador.medicalexpress.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ador.medicalexpress.models.Ambulence;
import com.ador.medicalexpress.R;

import java.util.ArrayList;

/**
 * Created by DORBESH on 1/16/2017.
 */

public class AmbulenceRecyclerAdapter extends RecyclerView.Adapter<AmbulenceRecyclerAdapter.RecyclerViewHoler> {

    ArrayList<Ambulence> arrayList = new ArrayList<>();
    Context ctx;

    public AmbulenceRecyclerAdapter(ArrayList<Ambulence> arrayList, Context ctx) {
        this.arrayList = arrayList;
        this.ctx = ctx;
    }

    @Override
    public RecyclerViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ambulence_layout, parent, false);
        RecyclerViewHoler recyclerViewHoler = new RecyclerViewHoler(view);
        return recyclerViewHoler;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHoler holder, int position) {

        holder.txtAname.setText(arrayList.get(position).getAmbulenceName());
        holder.txtAphone.setText(arrayList.get(position).getAmbulenceNumber());

        holder.txtAphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = holder.txtAphone.getText().toString();
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+Uri.encode(number.trim())));
                ctx.startActivity(callIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewHoler extends RecyclerView.ViewHolder{


        TextView txtAname,txtAphone;

        public RecyclerViewHoler(View v) {
            super(v);
            txtAname = (TextView)v.findViewById(R.id.ambulenceName);
            txtAphone = (TextView)v.findViewById(R.id.ambulencePhn);
        }

    }

}
