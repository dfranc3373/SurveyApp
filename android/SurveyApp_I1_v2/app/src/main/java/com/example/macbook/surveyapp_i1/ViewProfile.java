package com.example.macbook.surveyapp_i1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ViewProfile extends Activity implements View.OnClickListener {


    long id;
    DataHandler info = new DataHandler(this);

	protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        TextView tv_name = (TextView) findViewById(R.id.dbview_name);
        TextView tv_phone = (TextView) findViewById(R.id.dbview_phone);

        //Open database
        info.open();

        String name = info.getFirstLast();
        tv_name.setText(name);

        String phone = info.getPhone();

        tv_phone.setText(phone);

        //Close database for security purposes.
        info.close();
    }


    @Override
    public void onClick(View v) {

    }

}
