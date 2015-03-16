package model;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

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
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by administrator on 3/3/15.
 */
public class API {

    private SPTruckingAppConfigurationModel data;

    private Context context;

    private SharedPreferences preferences;

    public API(Context activity) {

        Gson gson = new Gson();

        this.context = activity;

        preferences = context.getSharedPreferences(CONSTANTS.PREF_NAME, 0);

        this.data = gson.fromJson(preferences.getString(CONSTANTS.AppConfig, ""), new TypeToken<SPTruckingAppConfigurationModel>() {}.getType());

    }

    public boolean DataSync() {

        Gson gson = new Gson();

        data = gson.fromJson(preferences.getString(CONSTANTS.AppConfig, ""), new TypeToken<SPTruckingAppConfigurationModel>() {}.getType());

        String url = this.data.CommunicationBaseURL + this.data.DataSyncMethodName;

        List<NameValuePair> values = new ArrayList<NameValuePair>();

        values.add(new BasicNameValuePair("Username", preferences.getString(CONSTANTS.Username, "")));
        values.add(new BasicNameValuePair("Password", ""));
        values.add(new BasicNameValuePair("Token", preferences.getString(CONSTANTS.Token, "")));
        values.add(new BasicNameValuePair("Model", ""));
        values.add(new BasicNameValuePair("LastUpdate", String.valueOf(millisToFiletime(Long.decode(String.valueOf(data.LastUpdate))))));
        values.add(new BasicNameValuePair("AppConfigVersion", String.valueOf(data.AppConfigurationVersion)));

        sendRequest r = new sendRequest(url, values, preferences.getString(CONSTANTS.Token, ""));

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

            SPTruckingResponseModel SP = gson.fromJson(response, new TypeToken<SPTruckingResponseModel>() {}.getType());

            if(SP.Success == true) {

                SqlHelper sql = new SqlHelper(context);

                String updateJSON = gson.toJson(SP.Updates);

                SPTruckingUpdatesModel updates = gson.fromJson(updateJSON, new TypeToken<SPTruckingAppConfigurationModel>() {}.getType());

                if(updates.Sites != null || updates.Sites.size() != 0) {

                    for(SPTruckingSiteModel site : updates.Sites) {

                        SPTruckingSiteModel old = null;

                        old = sql.getSiteModelFromSiteName(site.Name);

                        if(old == null) {

                            sql.insertSiteModel(site);

                        } else {

                            sql.updateSiteModel(site);

                        }

                    }

                }

                if(updates.AppConfig != null) {

                    SPTruckingAppConfigurationModel config = (SPTruckingAppConfigurationModel) updates.AppConfig;

                    SharedPreferences.Editor editor = preferences.edit();

                    editor.putString(CONSTANTS.AppConfig, gson.toJson(config));

                    editor.apply();

                    this.data = config;

                }

                /*if(SP.Updates)

                for (SPTruckingSiteModel s : data.Sites) {

                    if(sql.getSiteModelFromUniqueCode(s.UniqueCode) == null) {

                        sql.insertSiteModel(s);

                    }

                }*/

                return true;

            }

        } catch(Exception ex) {

            return false;

        }

        return false;

    }

    public String Login(String Username, String Password) {

        Gson gson = new Gson();

        data = gson.fromJson(preferences.getString(CONSTANTS.AppConfig, ""), new TypeToken<SPTruckingAppConfigurationModel>() {}.getType());

        String url = "http://199.193.116.90/service/token";

        List<NameValuePair> values = new ArrayList<NameValuePair>();

        values.add(new BasicNameValuePair("grant_type", "password"));
        values.add(new BasicNameValuePair("UserName", Username));
        values.add(new BasicNameValuePair("Password", Password));

        sendRequest r = new sendRequest(url, values, "");

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

            SPTruckingAuthenticationResponseModel authentication = gson.fromJson(response, new TypeToken<SPTruckingAuthenticationResponseModel>() {}.getType());

            if(authentication.Success == true) {

                return authentication.access_token;

            } else {

                return "Error: " + (authentication.DeactivationReasonID == 0 ? "Login Failed, Check Username and Password" : authentication.DeactivationReasonID);

            }

        } catch(Exception ex) {

            return "Error: " + ex.toString();

        }

    }

    public boolean CreateNewUser(SPTruckingNewUserModel model) {

        Gson gson = new Gson();

        data = gson.fromJson(preferences.getString(CONSTANTS.AppConfig, ""), new TypeToken<SPTruckingAppConfigurationModel>() {}.getType());

        String url = this.data.CommunicationBaseURL + this.data.RegisterNewUserMethodName;

        List<NameValuePair> values = new ArrayList<NameValuePair>();

        values.add(new BasicNameValuePair("Username", model.Username));
        values.add(new BasicNameValuePair("Password", model.Password));
        values.add(new BasicNameValuePair("UniqueCode", model.UniqueCode));
        values.add(new BasicNameValuePair("UniqueDeviceID", model.UniqueDeviceID));
        values.add(new BasicNameValuePair("DeviceType", model.DeviceType));
        values.add(new BasicNameValuePair("OS", model.OS));
        values.add(new BasicNameValuePair("FirstName", model.FirstName));
        values.add(new BasicNameValuePair("LastName", model.LastName));
        values.add(new BasicNameValuePair("Email", model.Email));
        values.add(new BasicNameValuePair("SecurityQuestion", model.SecurityQuestion));
        values.add(new BasicNameValuePair("SecurityAnswer", model.SecurityAnswer));
        values.add(new BasicNameValuePair("Phone", model.Phone));
        values.add(new BasicNameValuePair("TruckNumber", model.TruckNumber));
        values.add(new BasicNameValuePair("DriverNumber", model.DriverNumber));

        sendRequest r = new sendRequest(url, values, "");

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

            SPTruckingResponseModel SP = gson.fromJson(response, new TypeToken<SPTruckingResponseModel>() {}.getType());

            if(SP.Success == true) {

                SPTruckingInitializationModel initalization = (SPTruckingInitializationModel) SP.Result;

                SqlHelper sql = new SqlHelper(context);

                /*if(SP.Updates)

                for (SPTruckingSiteModel s : data.Sites) {

                    if(sql.getSiteModelFromUniqueCode(s.UniqueCode) == null) {

                        sql.insertSiteModel(s);

                    }

                }*/

                return true;

            }

        } catch(Exception ex) {

            return false;

        }

        return false;

    }

    private class sendRequest extends AsyncTask<Void, String, String> {

        private String URL;

        private List<NameValuePair> Values;

        private String Token;

        public sendRequest(String url, List<NameValuePair> values, String token) {

            this.URL = url;

            this.Values = values;

            this.Token = token;

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

                if(Token.length() == 0) {

                    headers = new Header[] {
                            new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                    };

                } else {

                    headers = new Header[] {
                            new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8"),
                            new BasicHeader("Authorization", "Bearer " + Token)
                    };

                }

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

    private static final long FILETIME_EPOCH_DIFF = 11644473600000L;

    /** One millisecond expressed in units of 100s of nanoseconds. */
    private static final long FILETIME_ONE_MILLISECOND = 10 * 1000;

    public long filetimeToMillis(final long filetime) {
        return (filetime / FILETIME_ONE_MILLISECOND) - FILETIME_EPOCH_DIFF;
    }

    public long millisToFiletime(final long millis) {
        return (millis + FILETIME_EPOCH_DIFF) * FILETIME_ONE_MILLISECOND;
    }

}