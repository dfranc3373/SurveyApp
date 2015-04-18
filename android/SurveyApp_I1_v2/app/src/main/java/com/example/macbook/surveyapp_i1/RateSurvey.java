package com.example.macbook.surveyapp_i1;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;


public class RateSurvey extends ActionBarActivity {

    ImageButton btnRateOne;
    ImageButton btnRateTwo;
    ImageButton btnRateThree;
    ImageButton btnRateFour;
    ImageButton btnSubmitRating;

    TextView tvSkipRating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rate_survey);

        btnRateOne = (ImageButton) findViewById(R.id.btnStarOne);
        btnRateTwo = (ImageButton) findViewById(R.id.btnStarTwo);
        btnRateThree = (ImageButton) findViewById(R.id.btnStarThree);
        btnRateFour = (ImageButton) findViewById(R.id.btnStarFour);
        btnSubmitRating = (ImageButton) findViewById(R.id.btnSubmitRating);

        tvSkipRating = (TextView) findViewById(R.id.tvSkipRating);

        btnRateOne.setImageResource(R.drawable.grey_star);
        btnRateTwo.setImageResource(R.drawable.grey_star);
        btnRateThree.setImageResource(R.drawable.grey_star);
        btnRateFour.setImageResource(R.drawable.grey_star);

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
