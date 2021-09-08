package com.example.smartschool;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.smartschool.objects.UserObject;

public class Utils {
    public static UserObject user=null;
    public static void saveData(Context context,String nameOfFile,String value,String key){
        SharedPreferences sharedPreferences=context.getSharedPreferences(nameOfFile,Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key,value).apply();
    }
    public static String loadData(Context context,String nameOfFile,String key){
        SharedPreferences sharedPreferences=context.getSharedPreferences(nameOfFile,Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"");
    }

    public static final String NAME_OF_FILE="user";
    public static final String USER_NAME="user";
}
