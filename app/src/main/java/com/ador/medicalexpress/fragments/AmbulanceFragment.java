package com.ador.medicalexpress.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ador.medicalexpress.models.Ambulence;
import com.ador.medicalexpress.R;
import com.ador.medicalexpress.adapters.AmbulenceRecyclerAdapter;

import java.util.ArrayList;


public class AmbulanceFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    String[] aNames,aphones;
    ArrayList<Ambulence> arrayList = new ArrayList<>();

    public AmbulanceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ambulance, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.ambulence_recyclerView);
        aNames = getResources().getStringArray(R.array.ambulenceName);
        aphones = getResources().getStringArray(R.array.ambulencePhn);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        int i = 0;
        for(String name:aNames){

            Ambulence ambulence = new Ambulence(name,aphones[i]);
            arrayList.add(ambulence);
            i++;
        }
        adapter = new AmbulenceRecyclerAdapter(arrayList,getActivity());
        recyclerView.setAdapter(adapter);

        return view;
    }

}
