package com.ador.medicalexpress.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.ador.medicalexpress.models.Hospital;
import com.ador.medicalexpress.activities.HospitalClick;
import com.ador.medicalexpress.R;
import com.ador.medicalexpress.adapters.RecyclerViewAdapter;

import java.util.ArrayList;

public class HospitalFragment extends Fragment {

    AutoCompleteTextView textView;

    ArrayList<Hospital> arrayList = new ArrayList<>();
    String[] name,status,phone,open;
    int[] img = {R.drawable.unitedhospital,R.drawable.labaiddhanmondi,R.drawable.alhelalhospital,R.drawable.birdem,R.drawable.japan,R.drawable.apollo,
            R.drawable.uttara,R.drawable.central,R.drawable.sprc,R.drawable.islami,
            R.drawable.health,R.drawable.advanceeye,R.drawable.ahmedmedical,
            R.drawable.ahsaniamissioncencer,R.drawable.archihospital,
            R.drawable.alashraf,R.drawable.alarafaclinic,R.drawable.albarakhospital,
            R.drawable.almadinahospital,R.drawable.almarkazulhospital,R.drawable.almohithospital,
            R.drawable.shaheedsuhrawadyhospital,R.drawable.mirpuradunikhospital,R.drawable.alhelalhospital,
            R.drawable.aljavalenurhospital,R.drawable.alrajhihospital,R.drawable.albirunihospital,
            R.drawable.alfatehhospital,R.drawable.arogyaniketonhospital,R.drawable.ayshamemorialhospital,
            R.drawable.bdfhospital,R.drawable.bangabanduhospital,R.drawable.banglanursinghospital,
            R.drawable.nationalhospital,R.drawable.bdmhospital,R.drawable.lifecare,
            R.drawable.anwarmodernhospital,R.drawable.greenlife,R.drawable.armforce,R.drawable.populardhanmondi,
            R.drawable.ibnsina,R.drawable.medinova,R.drawable.comfort,R.drawable.kidneyandurology,
            R.drawable.digilab,R.drawable.lions,R.drawable.cityhospital,R.drawable.somitrahospital
    };
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    public HospitalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hospital, container, false);
        textView = (AutoCompleteTextView)view.findViewById(R.id.searchHospital);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
        name = getResources().getStringArray(R.array.h_name);
        status = getResources().getStringArray(R.array.h_status);
        phone = getResources().getStringArray(R.array.h_phone);
        open = getResources().getStringArray(R.array.h_open);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        int i = 0;

        for(String Name:name){

            arrayList.add(new Hospital(Name,status[i],open[i],phone[i],img[i]));
            i++;

        }
        adapter = new RecyclerViewAdapter(arrayList,getContext());
        recyclerView.setAdapter(adapter);

        ArrayAdapter<String> autoTextAdapter = new ArrayAdapter<String>(getContext(),R.layout.auto_list_row,R.id.autoCtxt,name);
        textView.setAdapter(autoTextAdapter);
        final String[] lat = getResources().getStringArray(R.array.latitude);
        final String[] lng = getResources().getStringArray(R.array.longitude);
        textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int i;
                String hname= parent.getAdapter().getItem(position).toString();
                for(i = 0 ;i < name.length;i++){
                    if(name[i].equalsIgnoreCase(hname)){
                        break;
                    }
                }
                Intent intent = new Intent(getContext(),HospitalClick.class);
                intent.putExtra("lat",lat[i]);
                intent.putExtra("lng",lng[i]);
                intent.putExtra("H_Name",hname);
                textView.setText("");
                startActivity(intent);
            }
        });

        return view;
    }

}
