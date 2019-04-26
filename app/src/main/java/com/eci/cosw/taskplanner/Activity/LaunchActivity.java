package com.eci.cosw.taskplanner.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.eci.cosw.taskplanner.R;
import com.eci.cosw.taskplanner.Util.SharedPreference;

public class LaunchActivity extends AppCompatActivity {

    private static final String TOKEN_KEY = "TOKEN_KEY";
    private SharedPreference sharedPreference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreference = new SharedPreference(this);

        if (sharedPreference.containsToken()) {
            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
        } else {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);
        }
    }
}
