package com.example.embadedproject.tokenmanager;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenManager {

    private SharedPreferences sharedPreferences;
    private  SharedPreferences.Editor editor;
    private int Mode =0;
    private static final String REFNAME="JWTTOKEN";

    private  static  final String KEY_JWT_TOKEN="jwttoken";
    private Context context;

    public TokenManager(Context context)
    {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(REFNAME,Mode);
        editor = sharedPreferences.edit();
    }


    public void createSession(String Jwtvalue)
    {

        editor.putString(KEY_JWT_TOKEN,Jwtvalue);
        editor.commit();
    }
    public String getto(){
        String result = sharedPreferences.getString(KEY_JWT_TOKEN,"");
        return result;
    }

}
