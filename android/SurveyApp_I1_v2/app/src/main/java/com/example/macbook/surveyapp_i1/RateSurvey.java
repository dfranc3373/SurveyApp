package com.example.macbook.surveyapp_i1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class RateSurvey extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rate_survey);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rate_survey, menu);
        return true;
    }

    public void rateSurvey() {

        final ProgressDialog dialog = new ProgressDialog(RateSurvey.this);

        dialog.setTitle("Sending Rating");
        dialog.setMessage("Please wait");

        dialog.show();

        Thread sendFeedback = new Thread(new Runnable() {
            @Override
            public void run() {

                API api = new API(RateSurvey.this);
                api.SurveyFeedback(1, 5);//put the rankings here

                RateSurvey.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.hide();
                        Intent backtothemainlist = new Intent(RateSurvey.this, SurveyList.class);
                        RateSurvey.this.startActivity(backtothemainlist);
                        finish();


                    }
                });

            }
        });

        sendFeedback.start();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
