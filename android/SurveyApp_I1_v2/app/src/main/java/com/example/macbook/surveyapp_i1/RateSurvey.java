package com.example.macbook.surveyapp_i1;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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



}
