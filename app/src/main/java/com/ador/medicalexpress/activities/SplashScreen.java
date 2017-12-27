package com.ador.medicalexpress.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import com.ador.medicalexpress.MainActivity;
import com.ador.medicalexpress.R;

public class SplashScreen extends Activity {
    protected boolean _active = true;
    protected int _splashTime = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (_active && (waited < _splashTime)) {
                        sleep(100);
                        if (_active) {
                            waited += 100;
                        }
                    }
                } catch (Exception e) {

                } finally {

                    startActivity(new Intent(SplashScreen.this,
                            MainActivity.class));
                    finish();
                }
            };
        };
        splashTread.start();
    }





}
