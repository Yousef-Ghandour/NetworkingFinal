package com.final2.networkingfinal.Commons;

import static android.content.Context.MODE_PRIVATE;

import static com.final2.networkingfinal.Commons.Common.SharedPreferenceName;

import android.content.Context;
import android.content.SharedPreferences;

public class CommonFuncs {
    public void WriteSP(Context context, String key, String value){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreferenceName, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.apply();

    }

    public String GetFromSP(Context context,String key){
        String data = "";
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreferenceName, MODE_PRIVATE);
        data = sharedPreferences.getString(key,"");
        return data;
    }

    public void DeleteSp(Context context,String key){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SharedPreferenceName, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }
}
