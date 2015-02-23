package com.example.macbook.surveyapp_i1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.facebook.AppEventsLogger;
import com.facebook.FacebookException;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

import java.util.Arrays;

public class Menu extends Activity implements OnClickListener{

	Button btn_sign_up;
	Button btn_view_profile;
    Button btn_show_surveys;
    private String TAG = "LoginWithFB";
    String get_age, get_name, get_gender, get_email, get_id;

    SharedPrefsHandler sph;
	
	protected void onCreate(final Bundle savedInstanceState) {
		// TODO Auto-generated method stub	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);

        btn_sign_up = (Button) findViewById(R.id.btn_sign_up);
        btn_view_profile = (Button) findViewById(R.id.btn_view_profile);
        btn_show_surveys = (Button) findViewById(R.id.btn_survey_list);

        LoginButton authButton = (LoginButton) findViewById(R.id.authButton);

        authButton.setOnErrorListener(new LoginButton.OnErrorListener() {

            @Override
            public void onError(FacebookException error) {
                Log.i(TAG, "Error " + error.getMessage());
            }
        });

        // set permission list, Don't forget to add email
        authButton.setReadPermissions(Arrays.asList("basic_info", "email"));
        // session state call back event
        authButton.setSessionStatusCallback(new Session.StatusCallback() {

            @Override
            public void call(Session session, SessionState state, Exception exception) {

                if (session.isOpened()) {
                    Log.i(TAG, "Access Token" + session.getAccessToken());

                    String fbToken = session.getAccessToken();
                    sph.saveSharedPreferences(Constants.FB_TOKEN, fbToken);

                    Request.executeMeRequestAsync(session,
                            new Request.GraphUserCallback() {
                                @Override
                                public void onCompleted(GraphUser user, Response response) {
                                    if (user != null) {
                                        Log.i(TAG, "User ID " + user.getId());
                                        Log.i(TAG, "Email " + user.asMap().get("email"));

                                        get_id = (String) user.getId();
                                        get_gender = (String) user.getProperty("gender");
                                        //get_age = (String) user.getProperty("birthday");
                                        get_email = (String) user.getProperty("email");
                                        get_name = user.getName();

                                        //Save to db
                                        DataHandler entry = new DataHandler(Menu.this);
                                        entry.open();
                                        entry.createEntry(get_name, get_email, get_name, get_gender);
                                        entry.close();

                                        //Save to sharedPreferences
                                        sph.saveSharedPreferences(Constants.FB_USER_ID, get_id);
                                        sph.saveSharedPreferences(Constants.FB_USER_NAME, get_name);
                                        sph.saveSharedPreferences(Constants.FB_USER_EMAIL, get_email);
                                        sph.saveSharedPreferences(Constants.FB_USER_GENDER, get_gender);

                                        Intent intent = new Intent(
                                                "android.intent.action.REGISTERING");
                                        startActivity(intent);

                                    } else {
                                    }
                                }
                            });
                }
            }
        });

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent("android.intent.action.SIGNUP");
                //  intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            }
        });

        btn_view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent("android.intent.action.VIEWPROFILE");
                //  intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            }
        });

        btn_show_surveys.setOnClickListener(new OnClickListener(){

            public void onClick(View v){

                Intent intent = new Intent("android.intent.action.SURVEYLIST");

                startActivity(intent);
            }

        });


	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}


    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }


    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }


    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
            Log.i(TAG, "Logged in...");
        } else if (state.isClosed()) {
            Log.i(TAG, "Logged out...");
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
    }
}

