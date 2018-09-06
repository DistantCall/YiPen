package com.example.administrator.yipen.constance;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;
import java.util.Set;

public class SharePUtils {

    private  static SharedPreferences.Editor edit;
    private static SharedPreferences sharedPreferences;

    public SharePUtils(Context context, String tabName) {
        sharedPreferences = context.getSharedPreferences(tabName, Context.MODE_PRIVATE);
        edit = sharedPreferences.edit();
    }

    public  void add(String key,String value){
        edit.putString(key,value);
        edit.commit();
}

    public  void delete(String key){
        edit.remove(key);
        edit.commit();
    }
    public  String query(String str)
    {
       return sharedPreferences.getString(str,"err");
    }
}
