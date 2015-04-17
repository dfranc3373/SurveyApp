package com.example.macbook.surveyapp_i1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class TakeSurvey extends ActionBarActivity {

    int answersCount = 0;

    int questionsInSurvey = 4;

    Context context = this;

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

        btnAnswerOne = (Button) findViewById(R.id.answerOne);
        btnAnswerTwo = (Button) findViewById(R.id.answerTwo);
        btnAnswerThree = (Button) findViewById(R.id.answerThree);
        btnAnswerFour = (Button) findViewById(R.id.answerFour);

        tvSurveyTitle = (TextView) findViewById(R.id.tvSurveyTitleIndividual);
        tvQuestionTitle = (TextView) findViewById(R.id.tvQuestionTitle);
        tvQuestionText = (TextView) findViewById(R.id.tvQuestionText);

        tvSurveyTitle.setText(surveyTitle);
        tvQuestionTitle.setText(questionTitle[0]);
        tvQuestionText.setText(questionText[0]);

        btnAnswerOne.setText(answers[0]);
        btnAnswerTwo.setText(answers[1]);
        btnAnswerThree.setText(answers[2]);
        btnAnswerFour.setText(answers[3]);


        btnAnswerOne.setOnClickListener(new View.OnClickListener() {
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
        });

    }


    private void checkAnswerCount(int answersCount){

        //four questions recorded
        if (answersCount == questionsInSurvey){
            //show rate survey activity

            Intent i = new Intent(TakeSurvey.this, RateSurvey.class);
            startActivity(i);
            finish();
        }
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
