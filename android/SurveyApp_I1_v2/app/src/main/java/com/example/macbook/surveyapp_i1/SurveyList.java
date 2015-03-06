package com.example.macbook.surveyapp_i1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SurveyList extends Activity {

    Spinner spOrder;
    Spinner spCategory;
    Button btnRandom;

    TextView tvSurveyTitle;
    TextView tvSurveyDescription;
    TextView tvCategory;

    //map attributes names
    final String ATTR_NAME_TITLE = "title";
    final String ATTR_NAME_DESCRIPTION = "description";
    final String ATTR_NAME_CATEGORY = "category";
    final String ATTR_NAME_IMG = "image";

    ListView lvSurveyList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_list);

        //Test surveys data
        String[] surveyTitle = { "Survey One", "Survey Two", "Survey Three",
                "Survey Four", "Survey Five", "Survey Two", "Survey Three",
                "Survey Four", "Survey Five", "Survey Two", "Survey Three",
                "Survey Four", "Survey Five" };
        String[] surveyDescription = { "Description One", "Description Two", "Description Three",
                "Description Four", "Description Five", "Description Two", "Description Three",
                "Description Four", "Description Five", "Description Two", "Description Three",
                "Description Four", "Description Five" };
        String[] surveyCategory = { "Electronics", "Home Improvement", "Garden",
                "Garden", "Electronics", "Home Improvement", "Garden",
                "Garden", "Electronics", "Home Improvement", "Garden",
                "Garden", "Electronics"};
        int img = R.drawable.ic_launcher;

        ArrayList<Map<String, Object>> data =
                new ArrayList<Map<String, Object>>(surveyTitle.length);
        Map<String, Object> m;

        for (int i=0; i<surveyTitle.length; i++){

            m = new HashMap<String, Object>();
            m.put(ATTR_NAME_TITLE, surveyTitle[i]);
            m.put(ATTR_NAME_CATEGORY, surveyCategory[i]);
            m.put(ATTR_NAME_DESCRIPTION, surveyDescription[i]);
            m.put(ATTR_NAME_IMG, img);

            data.add(m);
        }

        String[] from = {ATTR_NAME_TITLE, ATTR_NAME_CATEGORY,
                ATTR_NAME_DESCRIPTION, ATTR_NAME_IMG};
        int[]to = {R.id.tvSurveyTitle, R.id.tvSurveyCategory,
                R.id.tvSurveyDescription, R.id.ivSurveyRating};

        SimpleAdapter simpleAdapter =
                new SimpleAdapter(this, data, R.layout.survey_item, from, to);

        lvSurveyList = (ListView) findViewById(R.id.lvSurveyList);
        lvSurveyList.setAdapter(simpleAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_survey_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){

            case R.id.menu_coupons:
                //show coupons list activity

                break;

            case R.id.menu_profile:
                //show user profile
                Intent intent = new Intent("android.intent.action.VIEWPROFILE");
                startActivity(intent);
                break;

            case R.id.menu_logout:
                // logout user and show main menu

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
