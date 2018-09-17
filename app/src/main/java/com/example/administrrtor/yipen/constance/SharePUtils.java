package com.example.administrrtor.yipen.constance;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;
import java.util.Set;

import retrofit2.http.PUT;

public class SharePUtils {

    private static SharedPreferences.Editor edit;
    private static SharedPreferences sharedPreferences;

    public SharePUtils(Context context, String tabName) {
        sharedPreferences = context.getSharedPreferences(tabName, Context.MODE_PRIVATE);
    }

    public void add(String key, String value) {
        edit = sharedPreferences.edit();
        edit.putString(key, value);
        edit.commit();
    }

    public void remove() {
        edit.clear();
    }

    public void delete(String key) {
        edit = sharedPreferences.edit();
        edit.remove(key);
        edit.commit();
    }

    public String query(String str) {
        return sharedPreferences.getString(str, "err");
    }
}
