package com.example.gdjkj.myapplication.utlis;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Shared prefences to hold the prefences of the application.
 */
public class SharedPreference {

    private static final int MODE_PRIVATE=0;
    private final SharedPreferences preferences;

    public SharedPreference(Context context){
        String userPref = "Wedding";
        preferences=context.getSharedPreferences(userPref,MODE_PRIVATE);
    }

    public void storeDeviceToken(String token){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("ReferenceToken", token);
        editor.commit();
    }
    public String getDeviceToken(){
        return preferences.getString("ReferenceToken","");
    }


    public void storePhoneNumber(String token){

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("phone", token);
        editor.commit();
    }
    public String getStorePhoneNumber(){
        return preferences.getString("phone","");
    }



   /* public void storeDeviceToken(String token){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("ReferenceToken", token);
        editor.commit();
    }
    public String getDeviceToken(){
        return preferences.getString("ReferenceToken","");
    }

    public void storeDeviceToken(String token){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("ReferenceToken", token);
        editor.commit();
    }
    public String getDeviceToken(){
        return preferences.getString("ReferenceToken","");
    }*/

}
