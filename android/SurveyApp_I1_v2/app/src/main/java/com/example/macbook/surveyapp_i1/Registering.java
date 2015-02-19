package com.example.macbook.surveyapp_i1;

// This class is the transition between register and navigating users to their profile page.



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Registering extends Activity {

    private static int SPLASH_TIME_OUT = 3500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashregistering);

        new Handler().postDelayed(new Runnable() {
        	 
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
 
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                Intent i = new Intent(Registering.this, Menu.class);
                startActivity(i);
 
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }


    protected void onPause(){
    	super.onPause();
    	finish();
    }
}