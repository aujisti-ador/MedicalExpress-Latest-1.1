package com.ador.medicalexpress.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ador.medicalexpress.R;
import com.ador.medicalexpress.adapters.RecyclerAdapter;
import com.ador.medicalexpress.fragments.GmapFragment;
import com.ador.medicalexpress.models.DoctorData;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;

public class HospitalClick extends AppCompatActivity implements GmapFragment.PassData, GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, LocationListener {

    TextView txtSepeciality, txtEmail, txtWeb;

    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private double currentLatitude;
    String clat, clng;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<DoctorData> arrayList = new ArrayList<>();
    String[] names, designation, qualification, chember, phone, location, speciality, email, web;
    String lat;
    String lng;
    String h_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospital_click);
        txtWeb = (TextView) findViewById(R.id.webId);
        txtEmail = (TextView) findViewById(R.id.emailId);
        txtSepeciality = (TextView) findViewById(R.id.speciality);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                // The next two lines tell the new client that “this” current class will handle connection stuff
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                //fourth line adds the LocationServices API endpoint from GooglePlayServices
                .addApi(LocationServices.API)
                .build();

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds

        Intent intent = getIntent();
        h_name = intent.getStringExtra("H_Name").trim();
        lat = intent.getStringExtra("lat");
        lng = intent.getStringExtra("lng");
        //Toast.makeText(getApplicationContext(),lat+" "+lng,Toast.LENGTH_SHORT).show();

        switch (h_name) {
            case "Uttara Cresent Hospital":
                names = getResources().getStringArray(R.array.uttaraCresentHospitalDoctorsName);
                designation = getResources().getStringArray(R.array.uttaraCresentHospitalDoctorsDesignation);
                qualification = getResources().getStringArray(R.array.uttaraCresentHospitalDoctorsQualification);
                chember = getResources().getStringArray(R.array.uttaraCresentHospitalDoctorsChember);
                phone = getResources().getStringArray(R.array.uttaraCresentHospitalDoctorsPhone);
                location = getResources().getStringArray(R.array.uttaraCresentHospitalDoctorsLocation);
                txtSepeciality.setText("Accident and Emergency, Anaesthesiology, Cardiology, Cardiothoracic Surgery, Critical Care Dentistry");
                txtWeb.setText("http://www.uttaracresent.com");
                txtEmail.setText("info@uttarahospital");
                break;
            case "Life care":
                names = getResources().getStringArray(R.array.lifecarehospitaldoctorsname);
                designation = getResources().getStringArray(R.array.lifecarehospitaldoctorsDesignation);
                qualification = getResources().getStringArray(R.array.lifecarehospitaldoctorsQualification);
                chember = getResources().getStringArray(R.array.lifecarehospitaldoctorsChamber);
                phone = getResources().getStringArray(R.array.lifecarehospitaldoctorsphone);
                location = getResources().getStringArray(R.array.lifecarehospitaldoctorsLocation);
                txtSepeciality.setText("Accident and Emergency, Anaesthesiology, Cardiology, Cardiothoracic Surgery, Critical Care Dentistry");
                txtWeb.setText("http://www.lifecare.com");
                txtEmail.setText("NA");
                break;

            case "Samorita Hospital":
                names = getResources().getStringArray(R.array.somitrahospitalDoctorsName);
                designation = getResources().getStringArray(R.array.somitrahospitalDoctorsDesignation);
                qualification = getResources().getStringArray(R.array.somitrahospitalDoctorsQualification);
                chember = getResources().getStringArray(R.array.somitrahospitalDoctorsChamber);
                phone = getResources().getStringArray(R.array.somitrahospitalDoctorsPhone);
                location = getResources().getStringArray(R.array.somitrahospitalDoctorsLocation);
                txtSepeciality.setText("ICU,Stroke units, Rehabilitation services");
                txtWeb.setText("samoritahospital.org");
                txtEmail.setText("admin@samoritahospital.org");
                break;

            case "Medinova":
                names = getResources().getStringArray(R.array.medinovahospitalDoctorsName);
                designation = getResources().getStringArray(R.array.medinovahospitalDoctorsDesignation);
                qualification = getResources().getStringArray(R.array.medinovahospitalDoctorsQualification);
                chember = getResources().getStringArray(R.array.medinovahospitalDoctorsChamber);
                phone = getResources().getStringArray(R.array.medinovahospitalDoctorsPhone);
                location = getResources().getStringArray(R.array.medinovahospitalDoctorsLocation);
                txtSepeciality.setText("Orthopaedic,Medicine etc");
                txtWeb.setText("http://www.medinova.com");
                txtEmail.setText("NA");
                break;


            case "AFMC - Armed Forces Medical College":
                names = getResources().getStringArray(R.array.armforceHospitalDOctorsName);
                designation = getResources().getStringArray(R.array.armforceHospitalDOctorsdesignation);
                qualification = getResources().getStringArray(R.array.armforceHospitalDOctorsQualification);
                chember = getResources().getStringArray(R.array.armforcedhospitalDoctorsChamber);
                phone = getResources().getStringArray(R.array.armforcedhospitalDoctorsPhone);
                location = getResources().getStringArray(R.array.armforcedhospitalDoctorsLocation);
                txtSepeciality.setText("\n" +
                        "    Community Medicine\n" +
                        "    Forensic Medicine\n" +
                        "    Microbiology\n" +
                        "    Pathology\n" +
                        "    Pharmacology\n");
                txtWeb.setText("http://www.afmcbd.com");
                txtEmail.setText("NA");
                break;

            case "Bangladesh Institute of Research and Rehabilitation in Diabetes,Endocrine and Metabolic Disorders (BIRDEM)":
                names = getResources().getStringArray(R.array.birdemhospitalDoctorsName);
                designation = getResources().getStringArray(R.array.birdemhospitalDoctorsDesignation);
                qualification = getResources().getStringArray(R.array.birdemhospitalDoctorsQualification);
                chember = getResources().getStringArray(R.array.birdemhospitalDoctorsChamber);
                phone = getResources().getStringArray(R.array.birdemhospitalDoctorsPhone);
                location = getResources().getStringArray(R.array.birdemhospitalDoctorsLocation);
                txtSepeciality.setText("\n" +
                        "    Community Medicine\n" +
                        "    Forensic Medicine\n" +
                        "    Microbiology\n" +
                        "    Pathology\n" +
                        "    Pharmacology\n");
                txtWeb.setText("www.http://hospital.bangladeshinformation.info/birdem-general-hospital/");
                txtEmail.setText("NA");
                break;

            case "Labaid Cardiac Hospital,dhanmondi":
                names = getResources().getStringArray(R.array.labaidHospitalDoctorsName);
                designation = getResources().getStringArray(R.array.labaidHospitalDoctorsDesignation);
                qualification = getResources().getStringArray(R.array.labaidHospitalDoctorsQualification);
                chember = getResources().getStringArray(R.array.labaidHospitalDoctorsChamber);
                phone = getResources().getStringArray(R.array.labaidHospitalDoctorsPhone);
                location = getResources().getStringArray(R.array.labaidHospitalDoctorsLocation);
                txtSepeciality.setText("Accident and Emergency, Cardiac Surgery, Cardiology, Cardiothoracic Surgery, Critical Care Dentistry");
                txtWeb.setText("http://www.labaidgroup.com/car_overview_intro.html");
                txtEmail.setText("info@labaidgroup.net / admin_lch@labaidgroup.com");
                break;

            case "Popular Diagnostic Centre Ltd - Dhanmondi Branch":
                names = getResources().getStringArray(R.array.popularHospitalDoctorsName);
                designation = getResources().getStringArray(R.array.popularHospitalDoctorsDesignation);
                qualification = getResources().getStringArray(R.array.popularHospitalDoctorsQualification);
                chember = getResources().getStringArray(R.array.popularHospitaldoctorsChamber);
                phone = getResources().getStringArray(R.array.popularHospitalDoctorsPhone);
                location = getResources().getStringArray(R.array.popularHospitalDoctorsLocation);
                txtSepeciality.setText("Accident and Emergency, Cardiac Surgery, Cardiology, Cardiothoracic Surgery, Critical Care Dentistry");
                txtWeb.setText("http://www.populardiagnostic.com");
                txtEmail.setText("info@populardiagnostic.com");
                break;
            case "Ibn Sina Consultation Center":
                names = getResources().getStringArray(R.array.ibasinahospitaldoctorsName);
                designation = getResources().getStringArray(R.array.ibasinaHospitaldoctorsDesignation);
                qualification = getResources().getStringArray(R.array.ibasinaHospitaldoctorsQualification);
                chember = getResources().getStringArray(R.array.ibasinaHospitaldoctorsChamber);
                phone = getResources().getStringArray(R.array.ibasinaHospitaldoctorsPhone);
                location = getResources().getStringArray(R.array.ibasinaHospitaldoctorsLocation);
                txtSepeciality.setText("Orthopaedic, Spine & Trauma Laparoscopic");
                txtWeb.setText("http://www.ibnsina.com");
                txtEmail.setText(" ibnsinadlab@yahoo.com");
                break;
            case "Kidney-Urology and General Hospital":
                names = getResources().getStringArray(R.array.kidneyAndUrologyHospitalDoctorsName);
                designation = getResources().getStringArray(R.array.kidneyAndUrologyHospitalDoctorsDesignation);
                qualification = getResources().getStringArray(R.array.kidneyAndUrologyHospitalDoctorsQualification);
                chember = getResources().getStringArray(R.array.kidneyAndUrologyHospitalDoctorsChamver);
                phone = getResources().getStringArray(R.array.kidneyAndUrologyHospitalDoctorsPhone);
                location = getResources().getStringArray(R.array.kidneyAndUrologyHospitalDoctorsLocation);
                txtSepeciality.setText("Kidney (Nephrology) and medicine");
                txtWeb.setText("http://www.ibnsina.com");
                txtEmail.setText(" ibnsinadlab@yahoo.com");
                break;

            case "Shahid Suhrawardy Hospital":
                names = getResources().getStringArray(R.array.shahidsurwahdyhospitalDoctorsName);
                designation = getResources().getStringArray(R.array.shahidsurwahdyhospitalDoctorsDesignation);
                qualification = getResources().getStringArray(R.array.shahidsurwahdyhospitalDoctorsQualification);
                chember = getResources().getStringArray(R.array.shahidsurwahdyhospitalDoctorsChamber);
                phone = getResources().getStringArray(R.array.shahidsurwahdyhospitalDoctorsPhone);
                location = getResources().getStringArray(R.array.shahidsurwahdyhospitalDoctorsLocation);
                txtSepeciality.setText("ICU, NICU,Spine & Trauma Laparoscopic etc");
                txtWeb.setText("NA");
                txtEmail.setText("www.suhrawardyhospital.gov.bd");
                break;

            case "Anwer Khan Modern Hospital Ltd":
                names = getResources().getStringArray(R.array.anwarkhanmodernHospitaldoctorsName);
                designation = getResources().getStringArray(R.array.anwarkhanmodernHospitaldoctorsDesignation);
                qualification = getResources().getStringArray(R.array.anwarkhanmodernHospitaldoctorsQualification);
                chember = getResources().getStringArray(R.array.anwarkhanhospitalDoctorsChamber);
                phone = getResources().getStringArray(R.array.anwarkhanhospitalDoctorsPhone);
                location = getResources().getStringArray(R.array.anwarkhanhospitalDoctorsLocation);
                txtSepeciality.setText("Orthopaedic, Spine & Trauma Laparoscopic,Neurosurgery,Cardiac");
                txtWeb.setText("http://www.anwarkhanhospital.com");
                txtEmail.setText(" anwarkhanhospital@yahoo.com");
                break;

            case "Green Life Hospital Limited":
                names = getResources().getStringArray(R.array.greenlifeHospitaldoctorsName);
                designation = getResources().getStringArray(R.array.greenlifeHospitaldoctorsDeesignation);
                qualification = getResources().getStringArray(R.array.greenlifeHospitaldoctorsQualification);
                chember = getResources().getStringArray(R.array.greenlifeHospitaldoctorsChamber);
                phone = getResources().getStringArray(R.array.greenHospitaldoctorsPhone);
                location = getResources().getStringArray(R.array.greenlifeHospitaldoctorsLocation);
                txtSepeciality.setText("Accident and Emergency, Cardiac Surgery, Cardiology, Cardiothoracic Surgery, Critical Care Dentistry");
                txtWeb.setText("http://www.green life hospital.com");
                txtEmail.setText("info@greenlife.com");
                break;

            case "Japan Bangladesh Friend Hospital":
                names = getResources().getStringArray(R.array.japanBangladeshHospiitalDoctorsName);
                designation = getResources().getStringArray(R.array.japanBangladeshHospiitalDoctorsDesignation);
                qualification = getResources().getStringArray(R.array.japanBangladeshHospiitalDoctorsQualification);
                chember = getResources().getStringArray(R.array.japanBangladeshHospiitalDoctorsChamber);
                phone = getResources().getStringArray(R.array.japanBangladeshHospiitalDoctorsPhone);
                location = getResources().getStringArray(R.array.japanBangladeshHospiitalDoctorsLocation);
                txtSepeciality.setText("Endoscopic,Laparoscopic, Physical Medicine, Neurology, Psychiatry, Cardiac Surgery, Pediatric");
                txtWeb.setText("http://www.jbfh.org.bd");
                txtEmail.setText("info@jbfh.org.bd");
                break;
            case "Apollo Hospital":
                names = getResources().getStringArray(R.array.apolloHospitalDoctorsName);
                designation = getResources().getStringArray(R.array.apolloHospitalDoctorsDesignation);
                qualification = getResources().getStringArray(R.array.apolloHospitalDoctorsQualification);
                chember = getResources().getStringArray(R.array.apolloHospitalDoctorsChember);
                phone = getResources().getStringArray(R.array.apolloHospitalDoctorsPhone);
                location = getResources().getStringArray(R.array.apolloHospitalDoctorsLocation);
                txtSepeciality.setText("Accident and Emergency, Anaesthesiology, Cardiology, Cardiothoracic Surgery, Critical Care Dentistry");
                txtWeb.setText("http://www.apollodhaka.com");
                txtEmail.setText("info@apollodhaka.com");
                break;
            case "Central Hospital Ltd":
                names = getResources().getStringArray(R.array.centralHospitalDoctorsName);
                designation = getResources().getStringArray(R.array.centralHospitalDoctorsDesignation);
                qualification = getResources().getStringArray(R.array.centralHospitalDoctorsQualification);
                chember = getResources().getStringArray(R.array.centralHospitalDoctorsChember);
                phone = getResources().getStringArray(R.array.centralHospitalDoctorsPhone);
                location = getResources().getStringArray(R.array.centralHospitalDoctorsLocation);
                txtSepeciality.setText("Accident and Emergency, Anaesthesiology, Cardiology, Cardiothoracic Surgery, Critical Care Dentistry");
                txtWeb.setText("http://www.centralhospitalltd.com");
                txtEmail.setText("chl@bol-online.com");
                break;
            case "Aysha Memorial Specialized Hospital":
                names = getResources().getStringArray(R.array.ayshahospitalDoctorsName);
                designation = getResources().getStringArray(R.array.ayshahospitalDoctorsDesignation);
                qualification = getResources().getStringArray(R.array.ayshahospitalDoctorsQualification);
                chember = getResources().getStringArray(R.array.ayshahospitalDoctorsChamber);
                phone = getResources().getStringArray(R.array.ayshahospitalDoctorsPhone);
                location = getResources().getStringArray(R.array.ayshahospitalDoctorsLocation);
                txtSepeciality.setText("Neonatal intensive ( NICU ), High dependency, Haemodialysis, Paediatric, ENT");
                txtWeb.setText("http://www.ayshamemorialhospital.com");
                txtEmail.setText("info@ayshamemorialhospital.com");
                break;
            case "Al-Helal Hospital,senpara":
                names = getResources().getStringArray(R.array.alhelalHospitaalDoctorsName);
                designation = getResources().getStringArray(R.array.alhelalHospitaalDoctorsDesignation);
                qualification = getResources().getStringArray(R.array.alhelalHospitaalDoctorsQualification);
                chember = getResources().getStringArray(R.array.alhelalHospitalDoctorsChamber);
                phone = getResources().getStringArray(R.array.alhelalHospitalDoctorsPhn);
                location = getResources().getStringArray(R.array.alhelalHospitalDoctorsLocation);
                txtSepeciality.setText("Department of Pediatric, Surgery, Burn  Plastic Surgery,Cardiology,Physical Medicine,Chest Surgery" +
                        "                        Gastroenterology,ENT,  Orthopedics,Urology, Neurosurgery, Neuromedicine," +
                        "Department of Skin and Sex,Department of Psychiatry," +
                        "                Intensive Care Unit (ICU),Neonatal Intensive Care Unit (NICU),Physiotherapy Centre");
                txtWeb.setText("http://www.http://alhelalhospital.com");
                txtEmail.setText("ahsh-ltd@hotmail.com, ahsh.bd@gmail.com");
                break;

            case "S.P.R.C And Neurology Hospital":
                names = getResources().getStringArray(R.array.SprcHospitalDoctorsName);
                designation = getResources().getStringArray(R.array.SprcHospitalDoctorsDesignation);
                qualification = getResources().getStringArray(R.array.SprcHospitalDoctorsQualification);
                chember = getResources().getStringArray(R.array.SprcHospitalDoctorsChember);
                phone = getResources().getStringArray(R.array.SprcHospitalDoctorsPhone);
                location = getResources().getStringArray(R.array.SprcHospitalDoctorsLocation);
                txtSepeciality.setText("Accident and Emergency, Anaesthesiology, Cardiology, Cardiothoracic Surgery, Critical Care Dentistry");
                txtWeb.setText("http://www.sprchospital.com");
                txtEmail.setText("info@sprc.com");
                break;
            case "Comfort Diagnostic Centre and Comfort Nursing Home":
                names = getResources().getStringArray(R.array.comfortHospitalDoctorsName);
                designation = getResources().getStringArray(R.array.comfortHospitalDoctorsDesignation);
                qualification = getResources().getStringArray(R.array.comfortHospitalDoctorsQualification);
                chember = getResources().getStringArray(R.array.comfortHospitalDoctorsChamber);
                phone = getResources().getStringArray(R.array.comfortHospitalDoctorsNumber);
                location = getResources().getStringArray(R.array.comfortHospitalDoctorsLocation);
                txtSepeciality.setText("Accident and Emergency, Anaesthesiology, Cardiology, Cardiothoracic Surgery, Critical Care Dentistry");
                txtWeb.setText("http://www.comforthospital.com");
                txtEmail.setText("info@comfort.com");
                break;

            case "Islami Bank Hospital":
                names = getResources().getStringArray(R.array.IslamiBankHospitalDoctorsName);
                designation = getResources().getStringArray(R.array.IslamiBankHospitalDoctorsDesignation);
                qualification = getResources().getStringArray(R.array.IslamiBankHospitalDoctorsQualification);
                chember = getResources().getStringArray(R.array.IslamiBankHospitalDoctorsChember);
                phone = getResources().getStringArray(R.array.IslamiBankHospitalDoctorsPhone);
                location = getResources().getStringArray(R.array.IslamiBankHospitalDoctorsLocation);
                txtSepeciality.setText("Accident and Emergency, Anaesthesiology, Cardiology, Cardiothoracic Surgery, Critical Care Dentistry");
                txtWeb.setText("http://www.islamichospital.com");
                txtEmail.setText("info@islamicbankhospital");
                break;
            case "Health and Hope Hospital":
                names = getResources().getStringArray(R.array.HealthAndHopedoctorDoctorsName);
                designation = getResources().getStringArray(R.array.HealthAndHopedoctorDoctorsdesignation);
                qualification = getResources().getStringArray(R.array.HealthAndHopedoctorDoctorsqualification);
                chember = getResources().getStringArray(R.array.HealthAndHopedoctorDoctorschamber);
                phone = getResources().getStringArray(R.array.HealthAndHopedoctorDoctorsphone);
                location = getResources().getStringArray(R.array.HealthAndHopedoctorDoctorslocation);
                txtSepeciality.setText("Accident and Emergency, Anaesthesiology, Cardiology, Cardiothoracic Surgery, Critical Care Dentistry");
                txtWeb.setText("http://www.healthandhope.com");
                txtEmail.setText("info@healthhopehospital");
                break;
            case "Ahmed Medical Center ( Deen Eye Care and Research Institue )":
                names = getResources().getStringArray(R.array.ahmedMedicalCenterDoctorsName);
                designation = getResources().getStringArray(R.array.ahmedMedicalCenterDoctorsDesignation);
                qualification = getResources().getStringArray(R.array.ahmedMedicalCenterDoctorsQualification);
                chember = getResources().getStringArray(R.array.ahmedMedicalCenterDoctorsChember);
                phone = getResources().getStringArray(R.array.ahmedMedicalCenterDoctorsPhone);
                location = getResources().getStringArray(R.array.ahmedMedicalCenterDoctorsLocation);
                txtSepeciality.setText("Eye ( Opthalmology )Treatment & Surgery");
                txtWeb.setText("http://www.ahmedMedicalCenter.com");
                txtEmail.setText("NA");
                break;
            case "Digilab Medical Services Ltd":
                names = getResources().getStringArray(R.array.digilabHospitalDoctorsName);
                designation = getResources().getStringArray(R.array.digilabHospitalDoctorsDesignation);
                qualification = getResources().getStringArray(R.array.digilabHospitalDoctorsQualification);
                chember = getResources().getStringArray(R.array.digilabHospitalDoctorsChamber);
                phone = getResources().getStringArray(R.array.digilabHospitalDoctorsPhone);
                location = getResources().getStringArray(R.array.digilabHospitalDoctorsLocation);
                txtSepeciality.setText("ENT,X-REY,Ultrasuno,ETT,EEG etc");
                txtWeb.setText("http://www.digilab.com.bd");
                txtEmail.setText("NA");
                break;

            case "Advanced Eye Center":
                names = getResources().getStringArray(R.array.adavanceEyeCenterDoctorsName);
                designation = getResources().getStringArray(R.array.adavanceEyeCenterDoctorsDesignation);
                qualification = getResources().getStringArray(R.array.adavanceEyeCenterDoctorsQualification);
                chember = getResources().getStringArray(R.array.adavanceEyeCenterDoctorsChamber);
                phone = getResources().getStringArray(R.array.adavanceEyeCenterDoctorsPhone);
                location = getResources().getStringArray(R.array.adavanceEyeCenterDoctorsLocation);
                txtSepeciality.setText("Eye specialist center, Eye surgery, Cataract phaco, Laser eye operation, Microphaco, IOL");
                txtWeb.setText("http://www.profnazrul.com");
                txtEmail.setText("NA");
                break;
            case "Ahsania Mission Cancer and General Hospital":
                names = getResources().getStringArray(R.array.ahsaniaMissionHospitalDoctorsName);
                designation = getResources().getStringArray(R.array.ahsaniaMissionHospitalDoctorsDesignation);
                qualification = getResources().getStringArray(R.array.ahsaniaMissionHospitalDoctorsQualification);
                chember = getResources().getStringArray(R.array.ahsaniaMissionHospitalDoctorsChamber);
                phone = getResources().getStringArray(R.array.ahsaniaMissionHospitalDoctorsPhonr);
                location = getResources().getStringArray(R.array.ahsaniaMissionHospitalDoctorsLocation);
                txtSepeciality.setText("OPD, Radiation Oncology, Nuclear Medicine, General Surgery");
                txtWeb.setText("http://www.ahsaniacancer.org.bd");
                txtEmail.setText("amcgh.mirpur@gmail.com");
                break;
            case "Al- Rajhi Hospital":
                names = getResources().getStringArray(R.array.alraziHospitalDoctorsName);
                designation = getResources().getStringArray(R.array.alraziHospitalDoctorsDesignation);
                qualification = getResources().getStringArray(R.array.alraziHospitalDoctorsQualification);
                chember = getResources().getStringArray(R.array.alraziHospitalDoctorsChamber);
                phone = getResources().getStringArray(R.array.alraziHospitalDoctorsPhone);
                location = getResources().getStringArray(R.array.alraziHospitalDoctorsLocation);
                txtSepeciality.setText("Gastroenterology , General Surgery,Medicine etc");
                txtWeb.setText("http://www.alraji.org.bd");
                txtEmail.setText("NA");
                break;

            case "City Hospital":
                names = getResources().getStringArray(R.array.cityHospitalDoctorsName);
                designation = getResources().getStringArray(R.array.cityHospitalDoctorsDesignation);
                qualification = getResources().getStringArray(R.array.cityHospitalDoctorsQualification);
                chember = getResources().getStringArray(R.array.cityHospitalDoctorsChamber);
                phone = getResources().getStringArray(R.array.cityHospitalDoctorsPhone);
                location = getResources().getStringArray(R.array.cityHospitalDoctorsLocation);
                txtSepeciality.setText("ICU , NICU ,Medicine etc");
                txtWeb.setText("http://www.cityhospitalbd.com");
                txtEmail.setText("cityhosp.bd@gmail.com");
                break;

            case "Aichi Hospital":
                names = getResources().getStringArray(R.array.aichiHospitalDoctoName);
                designation = getResources().getStringArray(R.array.aichiHospitalDoctorsDesignation);
                qualification = getResources().getStringArray(R.array.aichiHospitalDoctorsQualification);
                chember = getResources().getStringArray(R.array.aichiHospitalDoctorsChamber);
                phone = getResources().getStringArray(R.array.aichiHospitalDoctorsPhone);
                location = getResources().getStringArray(R.array.aichiHospitalDoctorsLocation);
                txtSepeciality.setText("Department of Pediatric, Surgery, Burn  Plastic Surgery,Cardiology,Physical Medicine,Chest Surgery" +
                        "                        Gastroenterology,ENT,  Orthopedics,Urology, Neurosurgery, Neuromedicine," +
                        "Department of Skin and Sex,Department of Psychiatry," +
                        "                Intensive Care Unit (ICU),Neonatal Intensive Care Unit (NICU),Physiotherapy Centre");
                txtWeb.setText("http://www.http://aichihospital.com");
                txtEmail.setText("aichi_hospital@yahoo.com");
                break;
            case "Al-Helal Specialized Hospital Ltd":
                names = getResources().getStringArray(R.array.alhelalHospitaalDoctorsName);
                designation = getResources().getStringArray(R.array.alhelalHospitaalDoctorsDesignation);
                qualification = getResources().getStringArray(R.array.alhelalHospitaalDoctorsQualification);
                chember = getResources().getStringArray(R.array.alhelalHospitalDoctorsChamber);
                phone = getResources().getStringArray(R.array.alhelalHospitalDoctorsPhn);
                location = getResources().getStringArray(R.array.alhelalHospitalDoctorsLocation);
                txtSepeciality.setText("Department of Pediatric, Surgery, Burn  Plastic Surgery,Cardiology,Physical Medicine,Chest Surgery" +
                        "                        Gastroenterology,ENT,  Orthopedics,Urology, Neurosurgery, Neuromedicine," +
                        "Department of Skin and Sex,Department of Psychiatry," +
                        "                Intensive Care Unit (ICU),Neonatal Intensive Care Unit (NICU),Physiotherapy Centre");
                txtWeb.setText("http://www.http://alhelalhospital.com");
                txtEmail.setText("ahsh-ltd@hotmail.com, ahsh.bd@gmail.com");
                break;

            case "United Hospital Limited":
                names = getResources().getStringArray(R.array.unitedHospitalDOctorsName);
                designation = getResources().getStringArray(R.array.unitedHospitalDOctorsDesignation);
                qualification = getResources().getStringArray(R.array.unitedHospitalDOctorsQualification);
                chember = getResources().getStringArray(R.array.unitedHospitaldoctorsChamber);
                phone = getResources().getStringArray(R.array.unitedHospitalDoctorsPhone);
                location = getResources().getStringArray(R.array.unitedHospitalDoctorsLocation);
                txtSepeciality.setText("Neuro center, Critical care Unit, Nuclear Medicine, Cardiology ");
                txtWeb.setText("http://www.uhlbd.com");
                txtEmail.setText("info@uhlbd.com");
                break;
            case "Lions Eye Hospital":
                names = getResources().getStringArray(R.array.lionsEyeHospitalsDoctorsName);
                designation = getResources().getStringArray(R.array.lionsEyeHospitalsDoctorsDesignation);
                qualification = getResources().getStringArray(R.array.lionsEyeHospitalsDoctorsQualification);
                chember = getResources().getStringArray(R.array.lionsEyeHospitalsDoctorsChamber);
                phone = getResources().getStringArray(R.array.lionsEyeHospitalsDoctorsPhone);
                location = getResources().getStringArray(R.array.lionsEyeHospitalsDoctorsLocation);
                txtSepeciality.setText("\n" +
                        "    Provide quality eye care service at the lowest affordable cost.\n" +
                        "    Develop Ophthalmic Manpower (Institute).\n" +
                        "    Develop a Centre of Excellence in eye care & treatment.\n");
                txtWeb.setText("http://www.lionsbd.com");
                txtEmail.setText("blf@blfbd.org");
                break;

            default:
                names = getResources().getStringArray(R.array.HealthAndHopedoctorDoctorsName);
                designation = getResources().getStringArray(R.array.HealthAndHopedoctorDoctorsdesignation);
                qualification = getResources().getStringArray(R.array.HealthAndHopedoctorDoctorsqualification);
                chember = getResources().getStringArray(R.array.HealthAndHopedoctorDoctorschamber);
                phone = getResources().getStringArray(R.array.HealthAndHopedoctorDoctorsphone);
                location = getResources().getStringArray(R.array.HealthAndHopedoctorDoctorslocation);
                txtSepeciality.setText("NA");
                txtWeb.setText("NA");
                txtEmail.setText("NA");

        }


        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        GmapFragment gmapFragment = new GmapFragment();
        fragmentTransaction.add(R.id.map_con, gmapFragment);
        fragmentTransaction.commit();
        layoutManager = new LinearLayoutManager(this, recyclerView.HORIZONTAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        int i = 0;

        for (String Name : names) {

            DoctorData doctorData = new DoctorData(Name, designation[i], qualification[i], chember[i], location[i], phone[i]);
            arrayList.add(doctorData);
            i++;
        }
        adapter = new RecyclerAdapter(arrayList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setData(String lat, String lng) {
        GmapFragment gmapFragment = (GmapFragment) getSupportFragmentManager().findFragmentById(R.id.map_con);
        // GmapFragment gmapFragment = new GmapFragment();
        lat = this.lat;
        lng = this.lng;
        //Toast.makeText(getApplicationContext(),lat+"HospitalClick"+lng,Toast.LENGTH_SHORT).show();


        gmapFragment.setLocation(lat, lng);
    }


    public void direction(View view) {
            final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {
                            startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog, final int id) {
                            dialog.cancel();
                        }
                    });
            final AlertDialog alert = builder.create();
            alert.show();

            }else {
            Intent intent = new Intent(HospitalClick.this, DirectionActivity.class);
            if (clat == null && clng == null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    checkLocationPermission();
                }
            } else {
                intent.putExtra("cLat", clat);
                intent.putExtra("cLng", clng);
                intent.putExtra("Lat", lat);
                intent.putExtra("Lng", lng);
                intent.putExtra("H_name", h_name);
                startActivity(intent);
            }
        }

        }


    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(this.getClass().getSimpleName(), "onPause()");

        //Disconnect from API onPause()
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
            mGoogleApiClient.disconnect();
        }


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (location == null) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

        } else {
            //If everything went fine lets get latitude and longitude
            clat = String.valueOf(location.getLatitude());
            clng = String.valueOf(location.getLongitude());

           // Toast.makeText(this, clat + " WORKS " + clng + "", Toast.LENGTH_LONG).show();
        }
        //Toast.makeText(this, clat + " WORKS " + clng + "", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

        clat = String.valueOf(location.getLatitude());
        clng = String.valueOf(location.getLongitude());

       // Toast.makeText(this, clat + " WORKS " + clng + "", Toast.LENGTH_LONG).show();

    }


    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {


                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    Toast.makeText(this, "permission allow", Toast.LENGTH_SHORT).show();

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

}
