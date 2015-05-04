package com.example.macbook.surveyapp_i1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Sandesh on 5/3/15.
 */
public class Login extends Activity {

    EditText Email, Password;
    Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Login = (Button) findViewById(R.id.btn_login);
        Email =(EditText)findViewById(R.id.login_email);
        Password =(EditText)findViewById(R.id.login_password);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog dialog = new ProgressDialog(Login.this);

                dialog.setTitle("Logging In");
                dialog.setMessage("Please wait");

                dialog.show();

                Thread login = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        API login = new API(Login.this);
                        String getEmail = Email.getText().toString();
                        String getPassword = Password.getText().toString();
                        if(login.Login(getEmail, getPassword)) {

                            Login.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    dialog.hide();

                                    Intent i = new Intent(Login.this, SurveyList.class);
                                    Login.this.startActivity(i);
                                    finish();
                                }
                            });

                        } else {

                            Login.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    dialog.hide();

                                    Toast.makeText(Login.this, "Error cold not login check your credentials and try again", Toast.LENGTH_LONG).show();

                                }
                            });

                        }

                    }
                });

                login.start();

            }
        });

    }
}
