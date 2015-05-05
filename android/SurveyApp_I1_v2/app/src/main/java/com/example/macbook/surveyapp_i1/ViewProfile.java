package com.example.macbook.surveyapp_i1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ViewProfile extends Activity implements View.OnClickListener {

    DataHandler info = new DataHandler(this);

	protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        TextView coupon = (TextView) findViewById(R.id.view);

        coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentC = new Intent("android.intent.action.COUPONS");
                startActivity(intentC);

            }
        });

    }


    @Override
    public void onClick(View v) {

    }
}
