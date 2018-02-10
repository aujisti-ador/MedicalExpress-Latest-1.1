package com.ador.medicalexpress;

import android.app.DatePickerDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ador.medicalexpress.adapters.ViewPagerAdapter;
import com.ador.medicalexpress.fragments.AmbulanceFragment;
import com.ador.medicalexpress.fragments.BloodFragment;
import com.ador.medicalexpress.fragments.HospitalFragment;
import com.ador.medicalexpress.fragments.NearbyFragment;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.ador.medicalexpress.R.drawable.ambulance;
import static com.ador.medicalexpress.R.drawable.blood;
import static com.ador.medicalexpress.R.drawable.hospital;
import static com.ador.medicalexpress.R.drawable.location;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    private int[] tabIcons = {
            hospital,
            location,
            ambulance,
            blood
    };

    String[] tabsTitles = {
            "Hospital",
            "Nearby",
            "Ambulance",
            "Blood"
    } ;
    //Icons


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle(tabsTitles[0]);
        setSupportActionBar(toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new HospitalFragment(),"");
        viewPagerAdapter.addFragments(new NearbyFragment(),"");
        viewPagerAdapter.addFragments(new AmbulanceFragment(),"");
        viewPagerAdapter.addFragments(new BloodFragment(),"");
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                toolbar.setTitle(tabsTitles[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout.setupWithViewPager(viewPager);

        setupTabIcons();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if (res_id==R.id.action_about)
        {
            Toast.makeText(this, "Knock us: aujisti.ador@gmail.com", Toast.LENGTH_LONG).show();
        }
        else if (res_id==R.id.action_abt_blood)
        {
            //Toast.makeText(this, "Share!", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
            final View mView = getLayoutInflater().inflate(R.layout.about_blood_request,null);
            alertBuilder.setView(mView);
            final AlertDialog dialog = alertBuilder.create();
            dialog.show();
        }

        else if (res_id==R.id.action_blood)
        {


                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
                final View mView = getLayoutInflater().inflate(R.layout.request_blood_form,null);
                final EditText name = (EditText) mView.findViewById(R.id.et_name);
                final EditText blood_group = (EditText) mView.findViewById(R.id.et_bloodGroup);
                final EditText place = (EditText) mView.findViewById(R.id.et_place);
                final EditText phone_number = (EditText) mView.findViewById(R.id.et_phone);
                final TextView date = (TextView) mView.findViewById(R.id.et_date);
                //final DatePicker date = (DatePicker) mView.findViewById(R.id.et_date);
                Button requ = (Button) mView.findViewById(R.id.btn_requ);

             //final DateFormat dateFormat = DateFormat.getDateInstance();


                alertBuilder.setView(mView);
                final AlertDialog dialog = alertBuilder.create();
                dialog.show();

                date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {

                        int day, month, year;


                        Calendar cal = Calendar.getInstance();
                        day = cal.get(Calendar.DAY_OF_MONTH);
                        month = cal.get(Calendar.MONTH);
                        year = cal.get(Calendar.YEAR);
                        month = month+1;

                        date.setText(month+"-"+day+"-"+year);

                        DatePickerDialog datePickerDialog =  new DatePickerDialog(MainActivity.this,
                                new DatePickerDialog.OnDateSetListener() {
                                    String mmonth;
                                    @Override
                                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                        month = month+1;

                                        switch (month)
                                        {
                                            case 1:
                                                mmonth = "Jan";
                                                break;
                                            case 2:
                                                mmonth = "Feb";
                                                break;
                                            case 3:
                                                mmonth = "March";
                                                break;
                                            case 4:
                                                mmonth = "April";
                                                break;
                                            case 5:
                                                mmonth = "May";
                                                break;
                                            case 6:
                                                mmonth = "June";
                                                break;
                                            case 7:
                                                mmonth = "July";
                                                break;
                                            case 8:
                                                mmonth = "Aug";
                                                break;
                                            case 9:
                                                mmonth = "Sept";
                                                break;
                                            case 10:
                                                mmonth = "Oct";
                                                break;
                                            case 11:
                                                mmonth = "Nov";
                                                break;
                                            case 12:
                                                mmonth = "Dec";
                                                break;
                                        }
                                        date.setText(mmonth+"-"+dayOfMonth+"-"+year);
                                    }
                                }, year,month,day);
                        datePickerDialog.show();


                    }

                }
                );


//            new DatePickerDialog.OnDateSetListener() {
//                @Override
//                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                    month=month+1;
//                    String mdate = month + "-" + dayOfMonth + "-" + year;
//                }
//            };


                requ.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if( name.getText().toString().length() == 0 )
                            name.setError( "Name is required!" );
                        else if( blood_group.getText().toString().length() == 0 )
                            blood_group.setError( "Blood Group is required!" );
                        else if( place.getText().toString().length() == 0 )
                            place.setError( "Place is required!" );
                        else if( phone_number.getText().toString().length() == 0 )
                            phone_number.setError( "Phone Number is required!" );
//                        else if( date.getText().toString().length() == 0 )
//                            date.setError( "Date is required!" );


                        if (name.getText().toString().length() > 0 && blood_group.getText().toString().length() > 0 && place.getText().toString().length() > 0 && phone_number.getText().toString().length() > 0 )

                        {
                            //private static final String URL_DATA = "http://fazlerabbiador.000webhostapp.com/medex/getAllEmp.php";
                            //final String URL_DATA = "http://192.168.0.103/PHP_Practice/medex/addBloodRequ.php";
                            final String URL_DATA = "http://www.momoz.shop/medex/addBloodRequ.php";



                            // Creating string request with post method.
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DATA,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String ServerResponse) {

                                            // Hiding the progress dialog after all task complete.
                                            //progressDialog.dismiss();

                                            // Showing response message coming from server.
                                            Toast.makeText(MainActivity.this, ServerResponse, Toast.LENGTH_LONG).show();
                                            dialog.dismiss();

                                        }
                                    },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError volleyError) {

                                            // Hiding the progress dialog after all task complete.
                                            //progressDialog.dismiss();

                                            // Showing error message if something goes wrong.
                                            Toast.makeText(MainActivity.this, volleyError.toString(), Toast.LENGTH_LONG).show();
                                        }
                                    }) {
                                @Override
                                protected Map<String, String> getParams() {

                                    // Creating Map String Params.
                                    Map<String, String> params = new HashMap<String, String>();

                                    // Adding All values to Params.
                                    params.put("name", name.getText().toString());
                                    params.put("blood_group", blood_group.getText().toString());
                                    params.put("place", place.getText().toString());
                                    params.put("phone_number", phone_number.getText().toString());
                                    params.put("date", date.getText().toString());
                                    //params.put("date", date.toString());

                                    return params;
                                }

                            };
                            // Creating RequestQueue.
                            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

                            // Adding the StringRequest object into requestQueue.
                            requestQueue.add(stringRequest);

                        }

                    }
                });

        }
        return super.onOptionsItemSelected(item);
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
    }


}
