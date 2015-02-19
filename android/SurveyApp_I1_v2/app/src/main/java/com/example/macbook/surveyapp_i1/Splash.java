package com.example.macbook.surveyapp_i1;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
 
public class Splash extends Activity {

	private static int SPLASH_TIME_OUT = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);


        new Handler().postDelayed(new Runnable() {
        	 
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
 
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity



                try {
                    Intent i = new Intent(Splash.this, ViewProfile.class);
                    startActivity(i);
                } finally {
                    Intent i = new Intent(Splash.this, Menu.class);
                    startActivity(i);
                }

                       // Intent i = new Intent(Splash.this, Menu.class);
                       // startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
    protected void onPause(){
    	super.onPause();
    	finish();
    }
}