package com.example.extra;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.gharakaama.SplashScreen;


public class SessionManager {

    SharedPreferences sharedprefernce;
    SharedPreferences.Editor editor;

    Context context;
    int PRIVATE_MODE=0;

    private static final String PREF_NAME = "sharedcheckLogin";
    private static final String SUBCAT_ID = "subcatid";
    private static final String USER_EMAIL = "useremail";
    private static final String USER_MOBILENO = "usermobile";
    private static final String CATEGORY_iD = "categoryid";
    private static final String IS_LOGIN="islogin";
    private static final String IS_OTP="isotp";


    public SessionManager(Context context){

        this.context =  context;
        sharedprefernce = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedprefernce.edit();
    }

    public void setSubcatId(String id ){

        editor.putString(SUBCAT_ID,id);
        editor.commit();

    }

    public String getSubcatId(){

        return  sharedprefernce.getString(SUBCAT_ID,"DEFAULT");
    }

    public void setuserEmail(String email){

        editor.putString(USER_EMAIL,email);
        editor.commit();
    }

    public String getUserEmail(){

        return sharedprefernce.getString(USER_EMAIL,"DEFAULT");
    }

    public void setUserMobileNO(String mobileNO){

        editor.putString(USER_MOBILENO,mobileNO);
        editor.commit();
    }

    public String getUserMobileno(){

        return sharedprefernce.getString(USER_MOBILENO,"DEFAULT");

    }

    public void setCategoryID(String categoryid){

        editor.putString(CATEGORY_iD,categoryid);
        editor.commit();
    }

    public String getCategoryId(){

        return sharedprefernce.getString(CATEGORY_iD,"DEFAULT");

    }

    public Boolean isLogin(){
        return sharedprefernce.getBoolean(IS_LOGIN, false);

    }
    public void setLogin(){

        editor.putBoolean(IS_LOGIN, true);
        editor.commit();

    }

    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, SplashScreen.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);





    }

}
