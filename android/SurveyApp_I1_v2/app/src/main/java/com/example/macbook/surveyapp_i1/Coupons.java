package com.example.macbook.surveyapp_i1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Models.Coupon;
import Models.Survey;


public class Coupons extends Activity {

    SharedPreferences prefs;

    ListView Coupons;

    final String ATTR_NAME_TITLE = "title";
    final String ATTR_NAME_DESCRIPTION = "description";
    final String ATTR_NAME_CATEGORY = "category";
    final String ATTR_NAME_IMG = "image";

    List<Coupon> coupons = new ArrayList<Coupon>();

    //Test surveys data
    String[] couponsTitle = { "Coupon One", "Coupon Two", "Coupon Three",
            "Coupon Four", "Coupon Five", };

    int img = R.drawable.ic_launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupons);

        prefs = Coupons.this.getSharedPreferences(Constants.PREF_NAME, 0);

        ArrayList<Integer> takenSurveys = new ArrayList<Integer>();

        coupons = new ArrayList<Coupon>();

        Gson gson = new Gson();

        takenSurveys = gson.fromJson(prefs.getString(Constants.SurveyTaken,""), new TypeToken<ArrayList<Integer>>(){}.getType());

        if(takenSurveys == null) {

            takenSurveys = new ArrayList<Integer>();

        }

        List<Survey> surveys = (new Gson()).fromJson(prefs.getString(Constants.Surveys, ""), new TypeToken<List<Survey>>(){}.getType());

        Coupons = (ListView) findViewById(R.id.lv_coupons);
//maps for the list
        final ArrayList<Map<String, Object>> data =
                new ArrayList<Map<String, Object>>();
        Map<String, Object> m;

        //add data to the maps
        for (Survey s : surveys) {

            if(!takenSurveys.contains(s.getSurveyID())) {

                continue;

            }

            m = new HashMap<String, Object>();
            m.put(ATTR_NAME_TITLE, s.getCoupon().getTitle());
            m.put(ATTR_NAME_DESCRIPTION, s.getCoupon().getCouponCode());

            Coupon string = s.getCoupon();

            coupons.add(string);

            data.add(m);

            String[] from = {ATTR_NAME_TITLE, ATTR_NAME_DESCRIPTION};
            int[] to = {R.id.tvSurveyTitle, R.id.tvSurveyDescription};

            SimpleAdapter simpleAdapter =
                    new SimpleAdapter(this, data, R.layout.survey_item, from, to);
            Coupons = (ListView) findViewById(R.id.lv_coupons);
            Coupons.setAdapter(simpleAdapter);

            //item in survey list selected
            Coupons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    //call single survey activity and pass the survey id
                    Log.d("myLog", "item clicked " + position);

                    Intent intent = new Intent("android.intent.action.COUPONCODE");
                    intent.putExtra("Coupon", (new Gson().toJson(coupons.get(position))));
                    startActivity(intent);
                }
            });


        }
    }
}
