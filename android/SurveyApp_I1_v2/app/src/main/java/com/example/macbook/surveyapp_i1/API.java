package com.example.macbook.surveyapp_i1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import Models.Survey;
import Models.User;
import Models.UserAnswer;

/**
 * Created by administrator on 3/3/15.
 */
public class API {

    private Context context;

    private SharedPreferences preferences;

    public API(Context activity) {

        Gson gson = new Gson();

        this.context = activity;

        preferences = context.getSharedPreferences(Constants.PREF_NAME, 0);

    }

    public boolean Login(String Email, String Password) {

        Gson gson = new Gson();

        String url = "http://survey-app-texastech.appspot.com/login";

        List<NameValuePair> values = new ArrayList<NameValuePair>();

        values.add(new BasicNameValuePair("email", Email));
        values.add(new BasicNameValuePair("Password", Password));

        sendRequest r = new sendRequest(url, values);

        r.execute();

        String response = null;

        try {

            response = r.get();

        } catch (InterruptedException e) {

            e.printStackTrace();

        } catch (ExecutionException e) {

            e.printStackTrace();

        }

        try {

            Models.Response authentication = gson.fromJson(response, new TypeToken<Models.Response>() {}.getType());

            if(authentication.getSuccess() == true) {

                User member = ((User) authentication.getModel());

                SharedPreferences.Editor edit = preferences.edit();

                edit.putString(Constants.LoggedIn, "true");
                edit.putString(Constants.APP_TOKEN, member.getToken());
                edit.putString(Constants.Email, member.getEmail());
                edit.putString(Constants.UserID, String.valueOf(member.getUserID()));
                edit.putString(Constants.FB, String.valueOf(member.isFB()));

                edit.commit();

                return true;

            } else {

                return false;

            }

        } catch(Exception ex) {

            return false;

        }

    }

    public List<Survey> getSurveys(int LastSurveyIDStored) {

        return getSurveysFunction(LastSurveyIDStored);

    }

    public List<Survey> getSurveys() {

        return getSurveysFunction(0);

    }

    public List<Survey> getSurveysFunction(int LastSurveyIDStored) {

        Gson gson = new Gson();

        String url = "http://survey-app-texastech.appspot.com/get_surveys";

        List<NameValuePair> values = new ArrayList<NameValuePair>();

        values.add(new BasicNameValuePair("last_survey", String.valueOf(LastSurveyIDStored)));

        sendRequest r = new sendRequest(url, values);

        r.execute();

        String response = null;

        try {

            response = r.get();

        } catch (InterruptedException e) {

            e.printStackTrace();

        } catch (ExecutionException e) {

            e.printStackTrace();

        }

        try {

            Models.Response authentication = gson.fromJson(response, new TypeToken<Models.Response>() {}.getType());

            if(authentication.getSuccess() == true) {

                List<Survey> surveys = gson.fromJson(gson.toJson(authentication.getModel()), new TypeToken<List<Survey>>(){}.getType());

                return surveys;

            }

        } catch(Exception ex) {

            return new ArrayList<Survey>();

        }

        return new ArrayList<Survey>();

    }

    public boolean CreateUser(User user) {

        Gson gson = new Gson();

        String url = "http://survey-app-texastech.appspot.com/register_user";

        List<NameValuePair> values = new ArrayList<NameValuePair>();

        values.add(new BasicNameValuePair("name", user.getName()));
        values.add(new BasicNameValuePair("email", user.getEmail()));
        values.add(new BasicNameValuePair("password", user.getPassword()));
        values.add(new BasicNameValuePair("FB", (user.isFB() ? "true" : "false")));
        values.add(new BasicNameValuePair("gender", String.valueOf(user.getGender())));
        values.add(new BasicNameValuePair("age_range", String.valueOf(user.getAge_Range())));
        values.add(new BasicNameValuePair("token", ""));

        sendRequest r = new sendRequest(url, values);

        r.execute();

        String response = null;

        try {

            response = r.get();

        } catch (InterruptedException e) {

            e.printStackTrace();

        } catch (ExecutionException e) {

            e.printStackTrace();

        }

        try {

            Models.Response authentication = gson.fromJson(response, new TypeToken<Models.Response>() {}.getType());

            if(authentication.getSuccess() == true) {

                User member = ((User) authentication.getModel());

                SharedPreferences.Editor edit = preferences.edit();

                edit.putString(Constants.LoggedIn, "true");
                edit.putString(Constants.APP_TOKEN, member.getToken());
                edit.putString(Constants.Email, member.getEmail());
                edit.putString(Constants.UserID, String.valueOf(member.getUserID()));
                edit.putString(Constants.FB, String.valueOf(member.isFB()));

                edit.commit();

                return true;

            } else {

                return false;

            }

        } catch(Exception ex) {

            return false;

        }

    }

    public boolean PostAnswers(List<UserAnswer> answers) {

        Gson gson = new Gson();

        String url = "http://survey-app-texastech.appspot.com/submit_useranswers";

        List<NameValuePair> values = new ArrayList<NameValuePair>();

        values.add(new BasicNameValuePair("question_answers", gson.toJson(answers)));
        values.add(new BasicNameValuePair("user_id", preferences.getString(Constants.UserID, "")));

        sendRequest r = new sendRequest(url, values);

        r.execute();

        String response = null;

        try {

            response = r.get();

        } catch (InterruptedException e) {

            e.printStackTrace();

        } catch (ExecutionException e) {

            e.printStackTrace();

        }

        try {

            Models.Response authentication = gson.fromJson(response, new TypeToken<Models.Response>() {}.getType());

            if(authentication.getSuccess() == true) {

                return true;

            } else {

                return false;

            }

        } catch(Exception ex) {

            return false;

        }

    }

    public boolean SurveyFeedback(int SurveyID, int rating) {

        Gson gson = new Gson();

        String url = "http://survey-app-texastech.appspot.com/SurveyFeedback";

        List<NameValuePair> values = new ArrayList<NameValuePair>();

        values.add(new BasicNameValuePair("SurveyID", String.valueOf(SurveyID)));
        values.add(new BasicNameValuePair("rating", String.valueOf(rating)));

        sendRequest r = new sendRequest(url, values);

        r.execute();

        String response = null;

        try {

            response = r.get();

        } catch (InterruptedException e) {

            e.printStackTrace();

        } catch (ExecutionException e) {

            e.printStackTrace();

        }

        try {

            Models.Response authentication = gson.fromJson(response, new TypeToken<Models.Response>() {}.getType());

            if(authentication.getSuccess() == true) {


                return true;

            } else {

                return false;

            }

        } catch(Exception ex) {

            return false;

        }

    }

    public String GetSurveyCode(int SurveyID) {

        Gson gson = new Gson();

        String url = "http://survey-app-texastech.appspot.com/GetSurveyCode";

        List<NameValuePair> values = new ArrayList<NameValuePair>();

        values.add(new BasicNameValuePair("SurveyID", String.valueOf(SurveyID)));

        sendRequest r = new sendRequest(url, values);

        r.execute();

        String response = null;

        try {

            response = r.get();

        } catch (InterruptedException e) {

            e.printStackTrace();

        } catch (ExecutionException e) {

            e.printStackTrace();

        }

        try {

            Models.Response authentication = gson.fromJson(response, new TypeToken<Models.Response>() {}.getType());

            if(authentication.getSuccess() == true) {


                String code = ((String) authentication.getModel());

                return code;

            } else {

                return "";

            }

        } catch(Exception ex) {

            return "";

        }

    }

    private class sendRequest extends AsyncTask<Void, String, String> {

        private String URL;

        private List<NameValuePair> Values;

        private String Token;

        public sendRequest(String url, List<NameValuePair> values) {

            this.URL = url;

            this.Values = values;

        }
        @Override
        protected String doInBackground(Void... params) {

            try {

                HttpClient client = new DefaultHttpClient();
                HttpPost request = new HttpPost(URL);

                AbstractHttpEntity values = new UrlEncodedFormEntity(Values, HTTP.UTF_8);
                values.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
                values.setContentEncoding("UTF-8");

                Header[] headers = null;

                    headers = new Header[] {
                            new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                    };

                request.setHeaders(headers);

                request.setEntity(values);

                HttpResponse response = client.execute(request);
                HttpEntity resEntity = response.getEntity();

                String requestcharacter = request.toString();

                if (resEntity != null) {
                    //parse response.

                    //Log.e("Response", EntityUtils.toString(resEntity));

                    String check = EntityUtils.toString(resEntity);

                    return check;

                } else {

                    return null;

                }

            } catch (Exception e) {

                e.printStackTrace();

                return e.toString();

            }

        }

        protected void onProgressUpdate(Integer... progress) {
            //setProgressPercent(progress[0]);
        }

        protected void onPostExecute(String result) {
            //showDialog("Downloaded " + result + " bytes");
        }
    }

}