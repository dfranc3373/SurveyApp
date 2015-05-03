package com.example.macbook.surveyapp_i1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import Models.User;

/**
 * Created by macbookuser on 5/3/15.
 */
public class Login extends Activity {

    EditText Email, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Email =(EditText)findViewById(R.id.login_email);
        Password =(EditText)findViewById(R.id.login_password);


        API login = new API(Login.this);
        String getEmail = Email.getText().toString();
        String getPassword = Password.getText().toString();
        login.Login(getEmail, getPassword);

    }
}
