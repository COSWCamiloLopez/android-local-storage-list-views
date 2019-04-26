package com.eci.cosw.taskplanner.Util;

import android.content.Context;
import android.content.SharedPreferences;

import com.eci.cosw.taskplanner.R;

public class SharedPreference {

    private final SharedPreferences sharedPreferences;
    private static final String TOKEN_KEY = "TOKEN_KEY";

    public SharedPreference(Context context) {
        this.sharedPreferences =
                context.getSharedPreferences(context.getString(R.string.preference_file_key),
                        Context.MODE_PRIVATE);
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public void saveToken(String token) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN_KEY, token);
        editor.commit();
    }

    public Boolean containsToken() {
        return sharedPreferences.contains(TOKEN_KEY);
    }

}
