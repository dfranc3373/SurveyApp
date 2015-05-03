package com.example.macbook.surveyapp_i1;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class RateSurvey extends ActionBarActivity {

    Context context = this;

    SharedPreferences prefs;

    Button btnSubmitRating;

    ImageButton stars[] = new ImageButton[4];

    TextView tvSkipRating;

    int rating = 0;
    int surveyID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rate_survey);

        Gson gson = new Gson();



        prefs = context.getSharedPreferences(Constants.PREF_NAME, 0);

        //read takenS from prefs


        SharedPreferences.Editor editor = prefs.edit();


        ArrayList<Integer> takenSurveys = new ArrayList<Integer>();

        takenSurveys = gson.fromJson(prefs.getString(Constants.SurveyTaken,""), new TypeToken<ArrayList<Integer>>(){}.getType());

        if(takenSurveys == null) {

            takenSurveys = new ArrayList<Integer>();

        }

        if(!takenSurveys.contains(surveyID)){

            takenSurveys.add(surveyID);
        }

        editor.putString(Constants.SurveyTaken, gson.toJson(takenSurveys));
        editor.putInt(Constants.UserID, 0);
        editor.commit();


        try {

            Intent intent = getIntent();

            surveyID = intent.getIntExtra("SurveyID", 0);

            if(surveyID == 0) {

                Intent i = new Intent(RateSurvey.this, SurveyList.class);
                startActivity(i);
                finish();

            }

        } catch (Exception e) {

            Intent i = new Intent(RateSurvey.this, SurveyList.class);
            startActivity(i);
            finish();

        }

        stars[0] = (ImageButton) findViewById(R.id.btnStarOne);
        stars[1] = (ImageButton) findViewById(R.id.btnStarTwo);
        stars[2] = (ImageButton) findViewById(R.id.btnStarThree);
        stars[3] = (ImageButton) findViewById(R.id.btnStarFour);
        btnSubmitRating = (Button) findViewById(R.id.btnSubmitRating);

        tvSkipRating = (TextView) findViewById(R.id.tvSkipRating);

        colorRatingStars(0);

        stars[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rating = 1;
                colorRatingStars(1);
            }
        });

        stars[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rating = 2;
                colorRatingStars(2);
            }
        });

        stars[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rating = 3;
                colorRatingStars(3);
            }
        });

        stars[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rating = 4;
                colorRatingStars(4);
            }
        });

        btnSubmitRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rateSurvey(surveyID, rating);

            }
        });

        tvSkipRating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ignore rating and go to survey list
                Intent backtothemainlist = new Intent(RateSurvey.this, SurveyList.class);
                RateSurvey.this.startActivity(backtothemainlist);
                finish();
            }
        });

    }

    private void colorRatingStars(int starClicked){

        Log.d("myLog", "Length of Stars " + stars.length);

        for (int i = 0; i < stars.length; i++){
            if(i<starClicked) {
                stars[i].setImageResource(R.drawable.yellow_star);
            }
            else{
                stars[i].setImageResource(R.drawable.grey_star);
            }
        }
    }

    public void rateSurvey(final int surveyID, final int rating) {

        if(rating < 1){

            Toast.makeText(context, R.string.choose_rating, Toast.LENGTH_LONG).show();

        }else {

            final ProgressDialog dialog = new ProgressDialog(RateSurvey.this);

            dialog.setTitle("Sending Rating");
            dialog.setMessage("Please wait");

            dialog.show();



            Thread sendFeedback = new Thread(new Runnable() {
                @Override
                public void run() {

                    API api = new API(RateSurvey.this);
                    api.SurveyFeedback(surveyID, rating);

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
