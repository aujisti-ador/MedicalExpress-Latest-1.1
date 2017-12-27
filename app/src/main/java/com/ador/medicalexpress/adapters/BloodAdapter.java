package com.ador.medicalexpress.adapters;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ador.medicalexpress.models.BloodRequestClass;
import com.ador.medicalexpress.R;

import java.util.ArrayList;

/**
 * Created by ADOR'S Lappy on 11-Oct-17.
 */

public class BloodAdapter extends RecyclerView.Adapter<BloodAdapter.Viewholder> {
    ArrayList<BloodRequestClass> arrayList = new ArrayList<>();
    Context ctx;

    public BloodAdapter(ArrayList<BloodRequestClass> arrayList, Context ctx) {
        this.arrayList = arrayList;
        this.ctx = ctx;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_blood_req, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(final Viewholder holder, int position) {

        holder.tv_name.setText(arrayList.get(position).getName());
        holder.tv_location.setText(arrayList.get(position).getLocation());
        holder.tv_phoneNumber.setText(arrayList.get(position).getPhoneNumber());
        holder.tv_date.setText(arrayList.get(position).getDate());
        holder.tv_bloodGroup.setText(arrayList.get(position).getBloodGroup());

        holder.btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = holder.tv_phoneNumber.getText().toString();
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

    public class Viewholder extends RecyclerView.ViewHolder{

        public TextView tv_name;
        public TextView tv_location;
        public TextView tv_phoneNumber;
        public TextView tv_date;
        public TextView tv_bloodGroup;
        public Button btn_call;


        public Viewholder(View itemView) {
            super(itemView);

            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_location = (TextView) itemView.findViewById(R.id.tv_location);
            tv_phoneNumber = (TextView) itemView.findViewById(R.id.tv_phoneNumber);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            tv_bloodGroup = (TextView) itemView.findViewById(R.id.tv_bloodGroup);
            btn_call = (Button) itemView.findViewById(R.id.btn_call);
        }
    }
}
