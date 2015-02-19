package com.example.macbook.surveyapp_i1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Menu extends Activity implements OnClickListener{

    Button fb_login;
	Button sign_up;
	Button view_profile;
    Button test;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);

		fb_login = (Button) findViewById(R.id.btn_fb_login);
        sign_up = (Button) findViewById(R.id.btn_sign_up);
        view_profile = (Button) findViewById(R.id.btn_view_profile);
        test = (Button) findViewById(R.id.btn_test);
				
	      fb_login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent("android.intent.action.LOGINWITHFB");
			  //  intent.addCategory(Intent.CATEGORY_HOME);
			    startActivity(intent);
			}
		});

        sign_up.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent("android.intent.action.SIGNUP");
				  //  intent.addCategory(Intent.CATEGORY_HOME);
				    startActivity(intent);
				}
			});

        view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent("android.intent.action.VIEWPROFILE");
                //  intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            }
        });

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent("android.intent.action.MYVEHICLES");
                //  intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            }
        });
		
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}

