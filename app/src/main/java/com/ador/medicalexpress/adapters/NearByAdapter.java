package com.ador.medicalexpress.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ador.medicalexpress.NearBy;
import com.ador.medicalexpress.R;

import java.util.ArrayList;

/**
 * Created by DORBESH on 1/28/2017.
 */

public class NearByAdapter extends RecyclerView.Adapter<NearByAdapter.NearbyViewHolder>{

     ArrayList<NearBy>arrayList = new ArrayList<>();

    public NearByAdapter(ArrayList<NearBy>arrayList){
        this.arrayList = arrayList;
    }
    @Override
    public NearbyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nearby_layour,parent,false);
        NearbyViewHolder viewHolder = new NearbyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NearbyViewHolder holder, int position) {

        holder.txtName.setText(arrayList.get(position).getName());
        holder.txtVincity.setText(arrayList.get(position).getVincity());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class NearbyViewHolder extends RecyclerView.ViewHolder{

        TextView txtName,txtVincity;

        public NearbyViewHolder(View v) {
            super(v);
            txtName = (TextView)v.findViewById(R.id.PlaceName);
            txtVincity = (TextView)v.findViewById(R.id.vincity);
        }
    }
}
