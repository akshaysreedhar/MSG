package com.msf.training.singletons;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager {

    private static SharedPreferences sharedPreferences;
    private static SharedPreferenceManager sharedPreferenceManager;

    private SharedPreferenceManager(Context context, String sharedPrefName) {
        sharedPreferences = context.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE);
    }

    public static void getInstance(Context context, String sharedPrefName) {
        if (sharedPreferenceManager == null) {
            sharedPreferenceManager = new SharedPreferenceManager(context, sharedPrefName);
        }
    }

    public static String getStringValue(String key) {
        return sharedPreferences.getString(key, null);
    }

    public static float getFloatValue(String key) {
        return sharedPreferences.getFloat(key, 0);
    }

    public static void setStringValue(String key, String newValue) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, newValue);
        editor.apply();
    }

    public static void setFloatValue(String key, Float newValue) {
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(key, newValue);
        editor.apply();
    }
}