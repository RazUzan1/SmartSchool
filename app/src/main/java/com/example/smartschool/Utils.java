package com.example.smartschool;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.smartschool.objects.UserObject;

public class Utils {
    public static UserObject user=null;
    /*
    * משתנה גלובלי של המשתמש שמחובר לאפליקציה כדי שתיהיה לנו גישה מכל מקום
    * המשתנה הזה מאותחל בפעם הראשונה כשהמשתמש מתחבר או נכנס לאפליקציה או נרשם
    *
    * */
    public static void saveData(Context context,String nameOfFile,String value,String key){
        SharedPreferences sharedPreferences=context.getSharedPreferences(nameOfFile,Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key,value).apply();
  /*
  * מאפשר לנו לשמור נתונים על גבי המכשיר מכל מקום באפליקציה
  * */
    }
    public static String loadData(Context context,String nameOfFile,String key){
        SharedPreferences sharedPreferences=context.getSharedPreferences(nameOfFile,Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"");
    }

    public static final String NAME_OF_FILE="user";
    public static final String USER_NAME="user";
}
