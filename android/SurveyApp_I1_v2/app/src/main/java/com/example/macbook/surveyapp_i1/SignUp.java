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
import android.widget.Toast;

import com.google.gson.Gson;

import Models.User;

public class SignUp extends Activity implements AdapterView.OnItemSelectedListener {

	Button Register;
    EditText Name, Password, Email, Age;
    Spinner Gender;
    String getGender;

   private SharedPreferences preferences;

    Gson gson = new Gson();

    private String[] selectGender = { "Male", "Female"};
  //  public String getAge = "Error Encountered!!!";

    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);

        preferences = SignUp.this.getSharedPreferences(Constants.PREF_NAME, 0);

        Register =(Button)findViewById(R.id.btn_register);
        Name =(EditText)findViewById(R.id.name);
        Email =(EditText)findViewById(R.id.email);
        Age =(EditText)findViewById(R.id.age);
        Password =(EditText)findViewById(R.id.password);
        Gender =(Spinner)findViewById(R.id.select_gender);

// Adapter for String[] Gender i.e. a Spinner(drop down box)
        ArrayAdapter<String> adapterAge = new ArrayAdapter<String>(
                SignUp.this, android.R.layout.simple_spinner_dropdown_item, selectGender);
       // Gender = (Spinner) findViewById(R.id.spinner_age);
        Gender.setAdapter(adapterAge);
        Gender.setOnItemSelectedListener(this);


        Register.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String getName = Name.getText().toString();
                String getEmail = Email.getText().toString();
                String getPassword = Password.getText().toString();
                String getAge = Age.getText().toString();
                String getGender = Gender.getSelectedItem().toString();

                API entry = new API(SignUp.this);
                User u = new User();
                u.setName(getName);
                u.setEmail(getEmail);
                u.setPassword(getPassword);
                u.setAge_Range(getAge);
                u.setGender(getGender);
                if(entry.CreateUser(u)) {//worked

                    Intent intent = new Intent("android.intent.action.REGISTERING");
                    startActivity(intent);

                } else {

                    Toast.makeText(SignUp.this, "Error please try and register again", Toast.LENGTH_LONG);

                }

            }
        });
	}


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int posMake = Gender.getSelectedItemPosition();
        getGender = selectGender[posMake];
       // getAge = (String) selectAge.getSelectedItem();
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
