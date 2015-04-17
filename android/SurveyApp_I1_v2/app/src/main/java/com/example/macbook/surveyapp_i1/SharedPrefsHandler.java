package com.example.macbook.surveyapp_i1;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Artsiom on 2/22/15.
 */
public class SharedPrefsHandler {

    Context context;
    SharedPreferences sPref;

    public SharedPrefsHandler(Context context) {
        sPref = context.getSharedPreferences
                (Constants.PREF_NAME, context.MODE_PRIVATE);
    }

    public void saveSharedPreferences(String fieldName, String fieldContent){

        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(fieldName, fieldContent);
        ed.apply();
    }


    public String readSharedPreferences(String fieldName, String defaultData){
       return sPref.getString(fieldName, defaultData);
    }

    public boolean readSharedPreferences(String fieldName, boolean defaultData){
        return sPref.getBoolean(fieldName, defaultData);
    }

}
