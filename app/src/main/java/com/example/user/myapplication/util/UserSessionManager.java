package com.example.user.myapplication.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.user.myapplication.main.activity.LoginActivity;

import java.util.HashMap;

/**
 * Created by Junho on 2016-03-08.
 */
public class UserSessionManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context mContext;

    int PRIVATE_MODE = 0;
    private static final String PREFER_NAME = "LOGINPREF";
    private static final String ISUSERLOGIN = "ISUSERLOGIN";
    public static final String KEYEMAIL="email";
    public static final String KEYPASSWORD="password";
    public static final String LOCKCHECK="LOCKCHECK";

    public UserSessionManager(Context context){
        this.mContext=context;
        pref = context.getSharedPreferences(PREFER_NAME,PRIVATE_MODE);
        editor = pref.edit();

    }
    public void createUserLoginSession(String email, String password, String lockcheck){
        editor.putBoolean(ISUSERLOGIN,true);
        editor.putString(KEYEMAIL, email);
        editor.putString(KEYPASSWORD,password);
        editor.putString(LOCKCHECK,lockcheck);
        editor.commit();
    }
    // Check for login
    public boolean isUserLogIn(){
        return pref.getBoolean(ISUSERLOGIN,false);
    }
    /**
     * Check login method will check user login status
     * If false it will redirect user to login page
     * Else do anything
     * */
    public boolean checkLogin(){
        if(!this.isUserLogIn()){
            Intent i = new Intent(mContext,LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(i);
            return true;
        }
        return false;
    }
    /**
     * Get stored session data
     * */
    public HashMap<String, String>getUserDetails(){
        HashMap<String,String> user = new HashMap<String,String>();
        user.put(KEYEMAIL,pref.getString(KEYEMAIL,null));
        user.put(KEYPASSWORD,pref.getString(KEYPASSWORD,null));
        return user;
    }
    public String getLockcheck(){
        String flag = pref.getString(LOCKCHECK,"");
        return flag;
    }
    public void setLockcheck(String flag){
        editor.remove(LOCKCHECK);
        editor.commit();

        editor.putString(LOCKCHECK,flag);
        editor.commit();
    }
    /**
     * Clear session details
     * */
      public void logoutUser(){
          // Clearing all user data from Shared Preferences
        editor.clear();
        editor.commit();
          // After logout redirect user to Login Activity
        Intent i = new Intent(mContext,LoginActivity.class);
          // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
          // Add new Flag to start new Activity
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          // Staring Login Activity
        mContext.startActivity(i);


    }


}
