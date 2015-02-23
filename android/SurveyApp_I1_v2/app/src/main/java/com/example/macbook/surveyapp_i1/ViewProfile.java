package com.example.macbook.surveyapp_i1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ViewProfile extends Activity implements View.OnClickListener {

    DataHandler info = new DataHandler(this);

	protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        TextView tv_name = (TextView) findViewById(R.id.dbview_name);
        TextView tv_phone = (TextView) findViewById(R.id.dbview_phone);

        //read from db
        info.open();
        tv_name.setText(info.getFirstLast());
        tv_phone.setText(info.getPhone());
        info.close();
    }


    @Override
    public void onClick(View v) {

    }
}
