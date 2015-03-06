package com.example.macbook.surveyapp_i1;

import android.app.Activity;
import android.app.ProgressDialog;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu extends Activity implements OnClickListener{

	Button btn_sign_up;
	Button btn_view_profile;
    Button btn_show_surveys;
    private String TAG = "LoginWithFB";
//<<<<<<< Updated upstream
    String get_id;

    ProgressDialog dialog;

    SharedPrefsHandler sph;
	
//	protected void onCreate(final Bundle savedInstanceState) {
//=======
    String get_age="unknown";
    String get_name="unknown";
    String get_gender="unknown";
    String get_email="unknown";

	protected void onCreate(Bundle savedInstanceState) {
//>>>>>>> Stashed changes
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

                    final String fbToken = session.getAccessToken();
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

                                        //Art: Added fbToken to params to avoid error,
                                        //not sure if it is the token we want to use
                                        entry.createEntry(get_name, get_email, get_name, get_gender, fbToken);
                                        entry.close();

                                        //Save to sharedPreferences
                                        sph.saveSharedPreferences(Constants.FB_USER_ID, get_id);
                                        sph.saveSharedPreferences(Constants.FB_USER_NAME, get_name);
                                        sph.saveSharedPreferences(Constants.FB_USER_EMAIL, get_email);
                                        sph.saveSharedPreferences(Constants.FB_USER_GENDER, get_gender);

                                        dialog = ProgressDialog.show(Menu.this, "", "Loading...", true);

                                        Thread t = new Thread(new Runnable() {
                                            @Override
                                            public void run() {


                                                postData(get_email, get_id);

                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {

                                                        dialog.hide();

                                                    }
                                                });

                                            }
                                        });

                                        t.start();

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

    public void postData(String email, String password) {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://survey-app-texastech.appspot.com/login");

        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("email", email));
            nameValuePairs.add(new BasicNameValuePair("password", "df121234"));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);

            String json = getResponseBody(response);

            if(json.length() != 0) {

                Gson gson = new Gson();

                Models.Response r = gson.fromJson(json, new TypeToken<Models.Response>() {}.getType());

                if(r.Success) {

                    Log.i("Log User in", "User data has been verified");

                }

            }

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block

            Log.i("Error", e.getMessage());

        } catch (IOException e) {
            // TODO Auto-generated catch block

            Log.i("Error", e.getMessage());
        }
    }

    public static String getResponseBody(HttpResponse response) {

        String response_text = null;
        HttpEntity entity = null;
        try {
            entity = response.getEntity();
            response_text = _getResponseBody(entity);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            if (entity != null) {
                try {
                    entity.consumeContent();
                } catch (IOException e1) {
                }
            }
        }
        return response_text;
    }

    public static String _getResponseBody(final HttpEntity entity) throws IOException, ParseException {

        if (entity == null) {
            throw new IllegalArgumentException("HTTP entity may not be null");
        }

        InputStream instream = entity.getContent();

        if (instream == null) {
            return "";
        }

        if (entity.getContentLength() > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(

                    "HTTP entity too large to be buffered in memory");
        }

        String charset = getContentCharSet(entity);

        if (charset == null) {

            charset = HTTP.DEFAULT_CONTENT_CHARSET;

        }

        Reader reader = new InputStreamReader(instream, charset);

        StringBuilder buffer = new StringBuilder();

        try {

            char[] tmp = new char[1024];

            int l;

            while ((l = reader.read(tmp)) != -1) {

                buffer.append(tmp, 0, l);

            }

        } finally {

            reader.close();

        }

        return buffer.toString();

    }

    public static String getContentCharSet(final HttpEntity entity) throws ParseException {

        if (entity == null) {
            throw new IllegalArgumentException("HTTP entity may not be null");
        }

        String charset = null;

        if (entity.getContentType() != null) {

            HeaderElement values[] = entity.getContentType().getElements();

            if (values.length > 0) {

                NameValuePair param = values[0].getParameterByName("charset");

                if (param != null) {

                    charset = param.getValue();

                }

            }

        }

        return charset;

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

