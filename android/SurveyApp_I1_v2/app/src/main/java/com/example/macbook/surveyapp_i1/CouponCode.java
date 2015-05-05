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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Models.Coupon;


public class CouponCode extends ActionBarActivity {

    SharedPreferences prefs;

    Coupon coupon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coupon_code);

        prefs = CouponCode.this.getSharedPreferences(Constants.PREF_NAME, 0);

        //read takenS from prefs

        SharedPreferences.Editor editor = prefs.edit();

        try {

            Intent intent = getIntent();

            coupon = (new Gson()).fromJson(intent.getStringExtra("Coupon"), new TypeToken<Coupon>(){}.getType());

            if(coupon == null) {

                Intent i = new Intent(CouponCode.this, SurveyList.class);
                startActivity(i);
                finish();

            }

        } catch (Exception e) {

            Intent i = new Intent(CouponCode.this, SurveyList.class);
            startActivity(i);
            finish();

        }

        TextView text = (TextView) findViewById(R.id.text);

        text.setText(coupon.getCouponCode());

    }

}