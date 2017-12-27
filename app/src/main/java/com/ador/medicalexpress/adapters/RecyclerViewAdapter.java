package com.ador.medicalexpress.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ador.medicalexpress.models.Hospital;
import com.ador.medicalexpress.activities.HospitalClick;
import com.ador.medicalexpress.R;

import java.util.ArrayList;

/**
 * Created by DORBESH on 12/27/2016.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>{

    ArrayList<Hospital> arrayList = new ArrayList<>();
    Context ctx;
    public RecyclerViewAdapter(ArrayList<Hospital>arrayList, Context ctx){
        this.arrayList = arrayList;
        this.ctx = ctx;

    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_row_layout,parent,false);
        return new RecyclerViewHolder(view,ctx,arrayList);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        holder.h_status.setText(arrayList.get(position).getH_status());
        holder.h_phone.setText(arrayList.get(position).getH_phone());
        holder.h_open.setText(arrayList.get(position).getH_open());
        holder.h_name.setText(arrayList.get(position).getH_name());
        holder.h_img.setImageResource(arrayList.get(position).getH__img());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public static  class RecyclerViewHolder extends RecyclerView.ViewHolder{

        TextView h_name,h_phone,h_status,h_open;
        ImageView h_img;
        ArrayList<Hospital> arrayList;
        CardView cardView;
        Context context;
        String[] lat,lng;
        public RecyclerViewHolder(View v, final Context context, final ArrayList<Hospital>arrayList) {
            super(v);
            h_img = (ImageView)v.findViewById(R.id.img_id);
            h_name = (TextView)v.findViewById(R.id.name);
            h_open = (TextView)v.findViewById(R.id.open);
            h_phone = (TextView)v.findViewById(R.id.phone);
            h_status = (TextView)v.findViewById(R.id.status);
            this.context = context;
            this.arrayList = arrayList;

            cardView = (CardView) v.findViewById(R.id.cardview);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    lat = context.getResources().getStringArray(R.array.latitude);
                    lng = context.getResources().getStringArray(R.array.longitude);

                    String latitude,longitude;
                    latitude = lat[getAdapterPosition()];
                    longitude = lng[getAdapterPosition()];
                    String h_name = arrayList.get(getAdapterPosition()).getH_name();
                    Intent intent = new Intent(context,HospitalClick.class);
                    intent.putExtra("lat",latitude);
                    intent.putExtra("lng",longitude);
                    intent.putExtra("H_Name",h_name);


                    context.startActivity(intent);
                }
            });
        }
    }
}
