package com.ador.medicalexpress.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ador.medicalexpress.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by DORBESH on 1/7/2017.
 */

public class GmapFragment extends Fragment{

    GoogleMap map;
    double lat;
    double lng;
    PassData passData;


public void setLocation(String lat,String lng){

    this.lat = Double.parseDouble(lat);
    this.lng = Double.parseDouble(lng);

}


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gmaps, container, false);
        passData.setData(String.valueOf(lat),String.valueOf(lng));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
          super.onViewCreated(view, savedInstanceState);
          SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
          mapFragment.getMapAsync(new OnMapReadyCallback() {
              @Override
              public void onMapReady(GoogleMap googleMap) {

                  map = googleMap;
                  googleMap.addMarker(new MarkerOptions().position(new LatLng(lat,lng)).title("Here"));
                  CameraPosition position = CameraPosition.builder().target(new LatLng(lat,lng)).zoom(18).bearing(0).tilt(0).build();
                  googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(position));
                 // Toast.makeText(getActivity().getApplicationContext(),lat+"Gmap"+lng,Toast.LENGTH_SHORT).show();

              }
          });
//          MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
//          mapFragment.getMapAsync(this);
//        SupportMapFragment mapFragment = (SupportMapFragment)getFragmentManager().findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);

    }

//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//
//        map = googleMap;
//        map.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(lat),Double.parseDouble(lng))).title("Here"));
//        CameraPosition position = CameraPosition.builder().target(new LatLng(Double.parseDouble(lat),Double.parseDouble(lng))).zoom(18).bearing(0).tilt(30).build();
//        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(position));
//        Toast.makeText(getActivity().getApplicationContext(),Double.parseDouble(lat)+"",Toast.LENGTH_SHORT).show();
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        passData = (PassData) activity;
    }

    public interface PassData{

        void setData(String lat, String lng);
    }

}
