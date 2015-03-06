package com.example.macbook.surveyapp_i1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SurveyList extends ActionBarActivity {

    Spinner spDate;
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

    //spinners items values
    String[] spDateItems = {"Newest First", "Oldest First"};
    String[] spCategoryItems = {"Show All", "Electronics", "Home Improvement", "Garden"};

    //spinners values constants
    final int SP_DATE_NEWEST = 0;
    final int SP_DATE_OLDEST = 1;

    final int SP_CATEGORY_ALL = 0;
    final int SP_CATEGORY_ELECTRONICS = 1;
    final int SP_CATEGORY_HOME = 2;
    final int SP_CATEGORY_GARDEN = 3;

    //toasts messages
    final String TOAST_TEXT_NEWEST_FIRST = "Showing Newest Surveys First";

    Context context = this;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_list);

        setSpinners();

        //maps for the list
        ArrayList<Map<String, Object>> data =
                new ArrayList<Map<String, Object>>(surveyTitle.length);
        Map<String, Object> m;

        //add data to the maps
        for (int i=0; i<surveyTitle.length; i++){
            m = new HashMap<String, Object>();
            m.put(ATTR_NAME_TITLE, surveyTitle[i]);
            m.put(ATTR_NAME_CATEGORY, surveyCategory[i]);
            m.put(ATTR_NAME_DESCRIPTION, surveyDescription[i]);
            m.put(ATTR_NAME_IMG, img);

            data.add(m);
        }

        //source and destination for the adapter
        String[] from = {ATTR_NAME_TITLE, ATTR_NAME_CATEGORY,
                ATTR_NAME_DESCRIPTION, ATTR_NAME_IMG};
        int[]to = {R.id.tvSurveyTitle, R.id.tvSurveyCategory,
                R.id.tvSurveyDescription, R.id.ivSurveyRating};

        //create adapter and give it to the survey list
        SimpleAdapter simpleAdapter =
                new SimpleAdapter(this, data, R.layout.survey_item, from, to);
        lvSurveyList = (ListView) findViewById(R.id.lvSurveyList);
        lvSurveyList.setAdapter(simpleAdapter);

        //item in survey list selected
        lvSurveyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //call single survey activity and pass the survey id
                Log.d("myLog", "item clicked " + position);

                Intent intent = new Intent("android.intent.action.TAKESURVEY");
                startActivity(intent);
            }
        });


        btnRandom = (Button) findViewById(R.id.btnRandom);

        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //sort survey list in random order

                //show toast that random order is selected
                Toast.makeText(context, R.string.toast_show_random, Toast.LENGTH_SHORT).show();

            }
        });
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

    void setSpinners(){

        //create adapters
        ArrayAdapter<String> adapterDate =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spDateItems);
        adapterDate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapterCategory =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spCategoryItems);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spDate = (Spinner) findViewById(R.id.spFilterDate);
        spCategory = (Spinner) findViewById(R.id.spFilterCategory);

        spDate.setAdapter(adapterDate);
        spCategory.setAdapter(adapterCategory);

        //spDate.setPrompt("Choose Order");
        //spCategory.setPrompt("Choose Category");

        //spDate.setSelection(-1);
        //spCategory.setSelection(-1);


        spDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case SP_DATE_NEWEST:

                        //show surveys newest first

                        //show toast
                        Toast.makeText(context, R.string.toast_show_newest_first, Toast.LENGTH_SHORT).show();


                        break;
                    case SP_DATE_OLDEST:

                        //show surveys newest first

                        //show toast
                        Toast.makeText(context, R.string.toast_show_oldest_first, Toast.LENGTH_SHORT).show();

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case SP_CATEGORY_ALL:
                        //show all surveys

                        //show toast
                        Toast.makeText(context, R.string.toast_show_category_all, Toast.LENGTH_SHORT).show();
                        break;

                    case SP_CATEGORY_ELECTRONICS:
                        //show category

                        //show toast
                        Toast.makeText(context, R.string.toast_show_category_electronics, Toast.LENGTH_SHORT).show();

                        break;
                    case SP_CATEGORY_HOME:
                        //show category

                        //show toast
                        Toast.makeText(context, R.string.toast_show_category_home, Toast.LENGTH_SHORT).show();

                        break;
                    case SP_CATEGORY_GARDEN:
                        //show category

                        //show toast
                        Toast.makeText(context, R.string.toast_show_category_garden, Toast.LENGTH_SHORT).show();

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
