package com.example.macbook.surveyapp_i1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Coupons extends Activity {

    ListView Coupons;

    final String ATTR_NAME_TITLE = "title";
    final String ATTR_NAME_DESCRIPTION = "description";
    final String ATTR_NAME_CATEGORY = "category";
    final String ATTR_NAME_IMG = "image";


    //Test surveys data
    String[] surveyTitle = { "Coupon One", "Coupon Two", "Coupon Three",
            "Coupon Four", "Coupon Five", };

    int img = R.drawable.ic_launcher;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupons);
        Coupons = (ListView) findViewById(R.id.lv_coupons);
//maps for the list
        ArrayList<Map<String, Object>> data =
                new ArrayList<Map<String, Object>>(surveyTitle.length);
        Map<String, Object> m;

        //add data to the maps
        for (int i = 0; i < surveyTitle.length; i++) {
            m = new HashMap<String, Object>();
            m.put(ATTR_NAME_TITLE, surveyTitle[i]);
            m.put(ATTR_NAME_IMG, img);

            data.add(m);

            String[] from = {ATTR_NAME_TITLE, ATTR_NAME_CATEGORY,
                    ATTR_NAME_DESCRIPTION, ATTR_NAME_IMG};
            int[] to = {R.id.tvSurveyTitle, R.id.tvSurveyCategory,
                    R.id.tvSurveyDescription, R.id.ivSurveyRating};

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
                    startActivity(intent);
                }
            });


        }
    }
}
