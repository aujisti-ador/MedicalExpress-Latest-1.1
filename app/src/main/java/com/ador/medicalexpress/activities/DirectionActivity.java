package com.ador.medicalexpress.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.ador.medicalexpress.HttpConnection;
import com.ador.medicalexpress.PathJSONParser;
import com.ador.medicalexpress.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DirectionActivity extends FragmentActivity {

    GoogleMap googleMap;
    LatLng LOWER_MANHATTAN;
    LatLng BROOKLYN_BRIDGE;

    final String TAG = "PathGoogleMapActivity";


    double cLat;
    double cLng;
    double dLat, dLng;
    String h_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction);
        SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);


            Intent intent = getIntent();
            dLat = Double.parseDouble(intent.getStringExtra("Lat"));
            dLng = Double.parseDouble(intent.getStringExtra("Lng"));
            cLat = Double.parseDouble(intent.getStringExtra("cLat"));
            cLng = Double.parseDouble(intent.getStringExtra("cLng"));
            h_name = intent.getStringExtra("H_name");

            LOWER_MANHATTAN = new LatLng(dLat, dLng);
            BROOKLYN_BRIDGE = new LatLng(cLat, cLng);
            //Toast.makeText(getBaseContext(), dLat + " " + dLng+"\n"+cLat+" "+cLng, Toast.LENGTH_SHORT).show();

            fm.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap gMap) {
                    googleMap = gMap;
                    MarkerOptions options = new MarkerOptions();
                    options.position(LOWER_MANHATTAN);
                    options.position(BROOKLYN_BRIDGE);
                    options.position(BROOKLYN_BRIDGE);
                    googleMap.addMarker(options);
                    String url = getMapsApiDirectionsUrl();
                    ReadTask downloadTask = new ReadTask();
                    downloadTask.execute(url);

                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(BROOKLYN_BRIDGE,
                            13));
                    addMarkers();
                }
            });

    }


    private String getMapsApiDirectionsUrl() {

            String waypoints = "waypoints=optimize:true|"
                    + LOWER_MANHATTAN.latitude + "," + LOWER_MANHATTAN.longitude
                    + "|" + "|" + BROOKLYN_BRIDGE.latitude + ","
                    + BROOKLYN_BRIDGE.longitude + "|" + BROOKLYN_BRIDGE.latitude + ","
                    + BROOKLYN_BRIDGE.longitude;

            String sensor = "sensor=false";
            // String params = waypoints + "&" + sensor;
            String output = "json";
            String origin = "origin=" + LOWER_MANHATTAN.latitude + "," + LOWER_MANHATTAN.longitude;
            String destination = "destination=" + BROOKLYN_BRIDGE.latitude + "," + BROOKLYN_BRIDGE.longitude;
            String params = origin + "&" + destination + "&" + waypoints + "&" + sensor;
            String url = "https://maps.googleapis.com/maps/api/directions/"
                    + output + "?" + params;

            return url;
    }

    private void addMarkers() {

            if (googleMap != null) {
                googleMap.addMarker(new MarkerOptions().position(BROOKLYN_BRIDGE)
                        .title("Your are here"));
                googleMap.addMarker(new MarkerOptions().position(LOWER_MANHATTAN)
                        .title(h_name));
                googleMap.addMarker(new MarkerOptions().position(BROOKLYN_BRIDGE)
                        .title("Your are here"));
            }
        }


    private class ReadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... url) {
            String data = "";
            try {
                HttpConnection http = new HttpConnection();
                data = http.readUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            new ParserTask().execute(result);
        }
    }

    private class ParserTask extends
            AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        @Override
        protected List<List<HashMap<String, String>>> doInBackground(
                String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                PathJSONParser parser = new PathJSONParser();
                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> routes) {


            if (isConnected() && isOnline()) {

                ArrayList<LatLng> points = null;
                PolylineOptions polyLineOptions = null;

                for (int i = 0; i < routes.size(); i++) {
                    points = new ArrayList<LatLng>();
                    polyLineOptions = new PolylineOptions();
                    List<HashMap<String, String>> path = routes.get(i);

                    for (int j = 0; j < path.size(); j++) {
                        HashMap<String, String> point = path.get(j);

                        double lat = Double.parseDouble(point.get("lat"));
                        double lng = Double.parseDouble(point.get("lng"));
                        LatLng position = new LatLng(lat, lng);

                        points.add(position);
                    }

                    polyLineOptions.addAll(points);
                    polyLineOptions.width(6);
                    polyLineOptions.color(Color.RED);
                }
                if(polyLineOptions != null) {
                    googleMap.addPolyline(polyLineOptions);
                }
            }else{
                Toast.makeText(getApplication(),"no internet connection",Toast.LENGTH_SHORT).show();
            }
        }

        public boolean isConnected(){
            ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected())
                return true;
            else
                return false;
        }
        public boolean isOnline() {

            Runtime runtime = Runtime.getRuntime();
            try {

                Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
                int     exitValue = ipProcess.waitFor();
                return (exitValue == 0);

            } catch (IOException e){
                e.printStackTrace();

            } catch (InterruptedException e){
                e.printStackTrace();
            }

            return false;
        }

    }

}