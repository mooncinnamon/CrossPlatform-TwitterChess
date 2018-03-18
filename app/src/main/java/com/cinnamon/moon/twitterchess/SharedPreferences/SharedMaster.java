package com.cinnamon.moon.twitterchess.SharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.cinnamon.moon.twitterchess.R;

import java.util.Map;
import java.util.Set;

/**
 * Created by moonp on 2018-03-19.
 */

public class SharedMaster {
    private Context context;
    private SharedPreferences sharedPref;

    public SharedMaster(Context context) {
        this.context = context;
        sharedPref = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    }

    public void setSharedPref(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void setSharedPref(String key, String value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void setSharedPref(String key, int value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public void setSharedPref(String key, long value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public void setSharedPref(String key, float value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public void setSharedPref(String key, Set<String> value) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putStringSet(key, value);
        editor.apply();
    }

    public String getSharedPref(String key, String defaultValue) {
        return sharedPref.getString(key, defaultValue);
    }

    public int getSharedPref(String key, int defaultValue) {
        return sharedPref.getInt(key, defaultValue);

    }

    public boolean getSharedPref(String key, boolean defaultValue) {
        return sharedPref.getBoolean(key, defaultValue);
    }

    public float getSharedPref(String key, float defaultValue) {
        return sharedPref.getFloat(key, defaultValue);
    }

    public long getSharedPref(String key, long defaultValue) {
        return sharedPref.getLong(key, defaultValue);
    }

    public Set<String> getSharedPref(String key, Set<String> defaultValue) {
        return sharedPref.getStringSet(key, defaultValue);
    }

    public Map<String, ?> getSharedPref() {
        return sharedPref.getAll();
    }

}
