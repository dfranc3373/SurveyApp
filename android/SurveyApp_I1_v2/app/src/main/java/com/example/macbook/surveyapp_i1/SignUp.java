package com.example.macbook.surveyapp_i1;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;

public class SignUp extends Activity implements AdapterView.OnItemSelectedListener {

	Button Register;
    EditText FirstName, LastName, Age, Phone;
	DataHandler handler;
    Spinner selectAge;
   // String getAge;
   private SharedPreferences preferences;

    Gson gson = new Gson();

    private String[] ageGroup = { "Below 14", "15-21", "22-30", "31-39","40-50","50+"};
    public String getAge = "Error Encountered!!!";

    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);

        preferences = SignUp.this.getSharedPreferences(Constants.PREF_NAME, 0);

        Register =(Button)findViewById(R.id.btn_register);
        FirstName =(EditText)findViewById(R.id.first_name);
        LastName =(EditText)findViewById(R.id.last_name);
        Phone =(EditText)findViewById(R.id.phone);

// Adapter for String[] makeName i.e. a Spinner(drop down box)
        ArrayAdapter<String> adapterAge = new ArrayAdapter<String>(
                SignUp.this, android.R.layout.simple_spinner_dropdown_item, ageGroup);

        selectAge = (Spinner) findViewById(R.id.spinner_age);
        selectAge.setAdapter(adapterAge);
        selectAge.setOnItemSelectedListener(this);

        Register.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String getFirstName = FirstName.getText().toString();
                String getLastName = LastName.getText().toString();
               // String getAge = Age.getText().toString();
                String getPhone = Phone.getText().toString();

                DataHandler entry = new DataHandler(SignUp.this);
                entry.open();
                entry.createEntry(getFirstName, getLastName, getAge, getPhone, "Token");
                entry.close();

                Intent intent = new Intent("android.intent.action.REGISTERING");
                startActivity(intent);
            }
        });
	}


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int posMake = selectAge.getSelectedItemPosition();
        getAge = ageGroup[posMake];
       // getAge = (String) selectAge.getSelectedItem();
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
