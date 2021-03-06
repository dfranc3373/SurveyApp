package com.example.macbook.surveyapp_i1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import Models.Survey;

public class SurveyList extends ActionBarActivity {

    SharedPreferences prefs;

    List surveys;

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
    final String ATTR_NAME_SID = "surveryID";

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

        prefs = context.getSharedPreferences(Constants.PREF_NAME, 0);

        API entry = new API(SurveyList.this);

        if(prefs.getString(Constants.Surveys, "").equals("")) {//no surveys exist

            surveys = entry.getSurveys(0);

            SharedPreferences.Editor info = prefs.edit();

            info.putString(Constants.Surveys, (new Gson()).toJson(surveys));

            info.commit();

        } else {

            surveys = (new Gson()).fromJson(prefs.getString(Constants.Surveys, ""), new TypeToken<List<Survey>>(){}.getType());

            Survey lastSurvey = (Survey) surveys.get(surveys.size() - 1);

            List<Survey> newSurveys = entry.getSurveys(lastSurvey.getSurveyID());

            if(newSurveys.size() != 0) {

                for(Survey info : newSurveys) {

                    surveys.add(info);

                }

            }

            SharedPreferences.Editor info = prefs.edit();

            info.putString(Constants.Surveys, (new Gson()).toJson(surveys));

            info.commit();

        }

        Log.d("myLog", "Number of surveys: " +  surveys.size() );

        setSpinners();

        updateListView(surveys);

        //item in survey list selected
        lvSurveyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView tvSurveyID = (TextView) view.findViewById(R.id.tvSurveyID);

                String SurveyIDString = tvSurveyID.getText().toString();

                int SurveyID = Integer.parseInt(SurveyIDString);

                //call single survey activity and pass the survey id
                Log.d("myLog", "item clicked " + position + " " + SurveyID);

                Intent intent = new Intent("android.intent.action.TAKESURVEY");
                intent.putExtra("SurveyID", SurveyID);
                startActivity(intent);
            }
        });


        btnRandom = (Button) findViewById(R.id.btnRandom);

        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Survey> random = new ArrayList<Survey>();

                List<Survey> current = new ArrayList<Survey>(surveys);

                for(int i = 0; i < surveys.size(); i ++) {

                    Random r = new Random();

                    int i1 = 0;

                    try {

                        i1 = r.nextInt((current.size() - 1) - 0) + 0;

                    } catch(Exception e) {

                        i1 = current.size() - 1;

                    }

                    random.add(current.get(i1));
                    current.remove(i1);

                }

                //sort survey list in random order

                //tmp solution to show original surveys list
                updateListView(random);

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

            case R.id.menu_refresh:

                final ProgressDialog loading = new ProgressDialog(SurveyList.this);
                loading.setMessage("Loading");
                loading.show();

                setSpinners();

                (new Thread(new Runnable() {
                    @Override
                    public void run() {

                        API api = new API(SurveyList.this);

                        surveys = (new Gson()).fromJson(prefs.getString(Constants.Surveys, ""), new TypeToken<List<Survey>>(){}.getType());

                        Survey lastSurvey = (Survey) surveys.get(surveys.size() - 1);

                        List<Survey> newSurveys = api.getSurveys(lastSurvey.getSurveyID());

                        if(newSurveys.size() != 0) {

                            for(Survey info : newSurveys) {

                                surveys.add(info);

                            }

                        }

                        SharedPreferences.Editor info = prefs.edit();

                        info.putString(Constants.Surveys, (new Gson()).toJson(surveys));

                        info.commit();

                        Log.d("myLog", "Number of surveys: " +  surveys.size() );

                        SurveyList.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                updateListView(surveys);

                                loading.hide();

                            }
                        });

                    }
                })).start();

                break;

            case R.id.menu_coupons:
                //show coupons list activity
                Intent intentC = new Intent("android.intent.action.COUPONS");
                startActivity(intentC);
                break;

            case R.id.menu_profile:
                //show user profile
                Intent intent = new Intent("android.intent.action.VIEWPROFILE");
                startActivity(intent);
                break;

            case R.id.menu_logout:
                // logout user and show main menu
                SharedPreferences.Editor editor = prefs.edit();

                editor.putBoolean(Constants.LoggedIn, false);
                editor.putInt(Constants.UserID, 0);
                editor.commit();

                intent = new Intent(SurveyList.this, com.example.macbook.surveyapp_i1.Menu.class);
                startActivity(intent);
                finish();

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void setSpinners() {

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

            List <Survey> tmpSurveys = new ArrayList<Survey>(surveys);

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case SP_DATE_NEWEST:

                        tmpSurveys = new ArrayList<Survey>(surveys);

                        //show surveys newest first

                        //sort tmpSurveys by date ascending
                        Collections.sort(tmpSurveys, new Comparator() {
                            @Override
                            public int compare(Object lhs, Object rhs) {
                                Survey sur1 = (Survey) lhs;
                                Survey sur2 = (Survey) rhs;

                                return sur1.compareTo(sur2);
                            }
                        });

                        updateListView(tmpSurveys);

                        //show toast
                        Toast.makeText(context, R.string.toast_show_newest_first, Toast.LENGTH_SHORT).show();


                        break;
                    case SP_DATE_OLDEST:

                        tmpSurveys = new ArrayList<Survey>(surveys);

                        //show surveys oldest first

                        //sort tmpSurveys by date descending
                        Collections.sort(tmpSurveys, new Comparator() {
                            @Override
                            public int compare(Object lhs, Object rhs) {
                                Survey sur1 = (Survey) lhs;
                                Survey sur2 = (Survey) rhs;

                                return sur2.compareTo(sur1);
                            }
                        });

                        updateListView(tmpSurveys);

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

            List <Survey> tmpSurveys = new ArrayList<Survey>(surveys);

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case SP_CATEGORY_ALL:

                        //show original surveys
                        updateListView(surveys);

                        //show toast
                        Toast.makeText(context, R.string.toast_show_category_all, Toast.LENGTH_SHORT).show();
                        break;

                    case SP_CATEGORY_ELECTRONICS:

                        tmpSurveys = new ArrayList<Survey>(surveys);

                        //filter the category and reload data in list view
                        updateListView(filterCategory(SP_CATEGORY_ELECTRONICS));

                        //show toast
                        Toast.makeText(context, R.string.toast_show_category_electronics, Toast.LENGTH_SHORT).show();

                        break;
                    case SP_CATEGORY_HOME:

                        tmpSurveys = new ArrayList<Survey>(surveys);

                        //filter the category and reload data in list view
                        updateListView(filterCategory(SP_CATEGORY_HOME));

                        //show toast
                        Toast.makeText(context, R.string.toast_show_category_home, Toast.LENGTH_SHORT).show();

                        break;
                    case SP_CATEGORY_GARDEN:

                        tmpSurveys = new ArrayList<Survey>(surveys);

                        //filter the category and reload data in list view
                        updateListView(filterCategory(SP_CATEGORY_GARDEN));

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

    private List filterCategory(int category){

        List<Survey> t = new ArrayList<Survey>(surveys);

        List<Survey> list = new ArrayList<Survey>();

        for (Survey sur : t) {

            if ((sur.getCategory() == surveyCategory[category])) {

                list.add(sur);

            }

        }

        return list;
    }


    private void updateListView(List surveys){

        //maps for the list
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(surveys.size());
        Map<String, Object> m;

        //add data to the maps
        for (int i=0; i<surveys.size(); i++){

            Survey currentSurvey = (Survey)surveys.get(i);

            //Log.d("myLog",currentSurvey.getTitle() );

            m = new HashMap<String, Object>();
            m.put(ATTR_NAME_TITLE, currentSurvey.getTitle());
            m.put(ATTR_NAME_CATEGORY, currentSurvey.getCategory());
            m.put(ATTR_NAME_DESCRIPTION, currentSurvey.getDescription());
            m.put(ATTR_NAME_SID, currentSurvey.getSurveyID());
            //m.put(ATTR_NAME_IMG, img);

            ArrayList<Integer> takenSurveys = new ArrayList<Integer>();

            Gson gson = new Gson();

            takenSurveys = gson.fromJson(prefs.getString(Constants.SurveyTaken,""), new TypeToken<ArrayList<Integer>>(){}.getType());

            if(takenSurveys == null) {

                takenSurveys = new ArrayList<Integer>();

            }

            if(!takenSurveys.contains(currentSurvey.getSurveyID())) {

                data.add(m);
            }
        }

        //source and destination for the adapter
        String[] from = {ATTR_NAME_TITLE, ATTR_NAME_CATEGORY,
                ATTR_NAME_DESCRIPTION, ATTR_NAME_SID};
        int[]to = {R.id.tvSurveyTitle, R.id.tvSurveyCategory,
                R.id.tvSurveyDescription, R.id.tvSurveyID};

        //create adapter and give it to the survey list
        SimpleAdapter simpleAdapter =
                new SimpleAdapter(this, data, R.layout.survey_item, from, to);
        lvSurveyList = (ListView) findViewById(R.id.lvSurveyList);
        lvSurveyList.setAdapter(simpleAdapter);
    }
}
