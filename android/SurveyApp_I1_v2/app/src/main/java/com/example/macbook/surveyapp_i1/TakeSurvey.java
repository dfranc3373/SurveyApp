package com.example.macbook.surveyapp_i1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import Models.QuestionAnswer;
import Models.Survey;
import Models.UserAnswer;


public class TakeSurvey extends ActionBarActivity {

    SharedPreferences prefs;

    int SurveyID;

    Survey survey;

    int answersCount = 0;

    int questionsInSurvey = 4;

    Context context = this;

    int i = 0;

    Button btnAnswerOne;
    Button btnAnswerTwo;
    Button btnAnswerThree;
    Button btnAnswerFour;

    TextView tvSurveyTitle;
    TextView tvQuestionTitle;
    TextView tvQuestionText;

    String surveyTitle = "Test survey title";

    String[] questionTitle = {"Question One", "Question Two",
            "Question Three", "Question four"};

    String[] questionText = {"Question One Text", "Question Two Text",
            "Question Three Text", "Question Four Text"};

    String[] answers = {"Answer One", "Answer Two", "Answer Three", "Answer Four"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_survey);

        prefs = context.getSharedPreferences(Constants.PREF_NAME, 0);

        LinearLayout ll = (LinearLayout) findViewById(R.id.llAnswerOptions);

        try {

            Intent intent = getIntent();

            int SurveyID = intent.getIntExtra("SurveyID", 0);

            List<Survey> surveys = (new Gson()).fromJson(prefs.getString(Constants.Surveys, ""), new TypeToken<List<Survey>>(){}.getType());

            if(surveys.size() == 0 || SurveyID == 0) {

                    Intent i = new Intent(TakeSurvey.this, SurveyList.class);
                    startActivity(i);
                    finish();

            }

            for(Survey s : surveys) {

                if(s.getSurveyID() == SurveyID) {

                    survey = s;

                    break;

                }

            }

            if(survey == null) {

                Intent i = new Intent(TakeSurvey.this, SurveyList.class);
                startActivity(i);
                finish();

            }

        } catch(Exception e) {

            Intent i = new Intent(TakeSurvey.this, SurveyList.class);
            startActivity(i);
            finish();

        }

        /*btnAnswerOne = (Button) findViewById(R.id.answerOne);
        btnAnswerTwo = (Button) findViewById(R.id.answerTwo);
        btnAnswerThree = (Button) findViewById(R.id.answerThree);
        btnAnswerFour = (Button) findViewById(R.id.answerFour);*/

        tvSurveyTitle = (TextView) findViewById(R.id.tvSurveyTitleIndividual);
        tvQuestionTitle = (TextView) findViewById(R.id.tvQuestionTitle);
        tvQuestionText = (TextView) findViewById(R.id.tvQuestionText);

        tvSurveyTitle.setText(survey.getTitle());
        tvQuestionTitle.setText(survey.getQuestion().get(0).getSurvey());
        //tvQuestionText.setText(survey.getQuestion().get(0).getDescription());

        for(final QuestionAnswer answer : survey.getQuestion().get(0).getAnswers()) {

            Button b = new Button(TakeSurvey.this);

            b.setText(answer.getAnswer());

            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    checkAnswerCount(answer.getQuestionAnswerID());
                    //show toast that the answer is recorded
                    Toast.makeText(context, "Answer recorded!", Toast.LENGTH_SHORT).show();

                }
            });

            b.setPadding(20, 50, 20, 50);

            ll.addView(b);

            i++;

        }

        //btnAnswerOne.setText(survey.getQuestion().get(0).getDescription());
        //btnAnswerTwo.setText(answers[1]);
        //btnAnswerThree.setText(answers[2]);
        //btnAnswerFour.setText(answers[3]);


        /*btnAnswerOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerCount(++answersCount);
                //show toast that the answer is recorded
                Toast.makeText(context, R.string.toast_answer_one, Toast.LENGTH_SHORT).show();
            }
        });

        btnAnswerTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerCount(++answersCount);
                //show toast that the answer is recorded
                Toast.makeText(context, R.string.toast_answer_two, Toast.LENGTH_SHORT).show();
            }
        });

        btnAnswerThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerCount(++answersCount);
                //show toast that the answer is recorded
                Toast.makeText(context, R.string.toast_answer_three, Toast.LENGTH_SHORT).show();
            }
        });

        btnAnswerFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerCount(++answersCount);
                //show toast that the answer is recorded
                Toast.makeText(context, R.string.toast_answer_four, Toast.LENGTH_SHORT).show();
            }
        });*/

    }


    private void checkAnswerCount(int answersCount){

        //four questions recorded
        //if (answersCount == questionsInSurvey){
            //show rate survey activity

        API api = new API(TakeSurvey.this);

        List<UserAnswer> answerList = new ArrayList<UserAnswer>();
        UserAnswer id = new UserAnswer();
        id.setQuestionAnswerID(answersCount);
        id.setUserID(prefs.getInt(Constants.UserID, 0));
        answerList.add(id);

        boolean answersent = api.PostAnswers(answerList);

        if(answersent) {

            Intent i = new Intent(TakeSurvey.this, RateSurvey.class);
            startActivity(i);
            finish();

        } else {

            Toast.makeText(TakeSurvey.this, "Could not send answer, please try again", Toast.LENGTH_SHORT).show();

        }
        //}
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

        Intent intent;

        switch (id){

            case R.id.menu_coupons:
                //show coupons list activity

                break;

            case R.id.menu_profile:
                //show user profile
                intent = new Intent("android.intent.action.VIEWPROFILE");
                startActivity(intent);
                break;

            case R.id.menu_logout:
                // logout user and show main menu

                SharedPreferences.Editor editor = prefs.edit();

                editor.putBoolean(Constants.LoggedIn, false);
                editor.putInt(Constants.UserID, 0);
                editor.commit();

                intent = new Intent(TakeSurvey.this, com.example.macbook.surveyapp_i1.Menu.class);
                startActivity(intent);
                finish();

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
