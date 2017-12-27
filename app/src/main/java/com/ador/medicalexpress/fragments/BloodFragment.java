package com.ador.medicalexpress.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ador.medicalexpress.models.BloodRequestClass;
import com.ador.medicalexpress.R;
import com.ador.medicalexpress.adapters.BloodAdapter;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BloodFragment extends Fragment {

    private static final String URL_DATA = "https://fazlerabbiador.000webhostapp.com/medex/getBloodRequ.php";
    //private static final String URL_DATA = "http://192.168.0.103/PHP_Practice/medex/getBloodRequ.php";
    //ListView listView;
    //List<BloodRequestClass> listItems;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<BloodRequestClass> listItems = new ArrayList<>();


    public BloodFragment() {
        // Required empty public constructor
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //return inflater.inflate(R.layout.fragment_blood, container, false);

        View view = inflater.inflate(R.layout.fragment_blood, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.blood_recyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(layoutManager);


        loadRecyclarViewData();

        return view;


    }

    @Override
    public void onPause() {
        super.onPause();
        loadRecyclarViewData();

    }


    private void loadRecyclarViewData()
    {
        //final ProgressDialog progressDialog = new ProgressDialog(getContext());
        //progressDialog.setMessage("Loading data...");
        //progressDialog.show();

        listItems = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("result");

                            for (int i = 0; i<jsonArray.length(); i++)
                            {
                                jsonObject = jsonArray.getJSONObject(i);
                                BloodRequestClass bloodRequestClass = new BloodRequestClass(jsonObject.getString("name"), jsonObject.getString("blood_group"), jsonObject.getString("place"),jsonObject.getString("phone_number"),jsonObject.getString("date"));

                                listItems.add(bloodRequestClass);
                            }
                            adapter = new BloodAdapter(listItems, getContext());
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

}
