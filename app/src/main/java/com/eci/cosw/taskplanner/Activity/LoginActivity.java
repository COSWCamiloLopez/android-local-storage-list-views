package com.eci.cosw.taskplanner.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.eci.cosw.taskplanner.Model.LoginWrapper;
import com.eci.cosw.taskplanner.R;
import com.eci.cosw.taskplanner.Service.AuthService;
import com.eci.cosw.taskplanner.Model.Token;
import com.eci.cosw.taskplanner.Util.SharedPreference;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private static AuthService authService;
    private final ExecutorService executorService = Executors.newFixedThreadPool(1);
    Context context = this;
    private SharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreference = new SharedPreference(context);

        if (authService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8080/") //localhost for emulator
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            authService = retrofit.create(AuthService.class);
        }
    }

    public void login(View view) {
        EditText email = (EditText) findViewById(R.id.email);
        EditText password = (EditText) findViewById(R.id.password);

        final String stringEmail = email.getText().toString();
        final String stringPassword = password.getText().toString();

        if (!stringEmail.matches("")) {
            if (!stringPassword.matches("")) {
                view.setEnabled(false);
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            LoginWrapper loginWrapper = new LoginWrapper(stringEmail,
                                    stringPassword);
                            Response<Token> response = authService.login(loginWrapper).execute();
                            if (response.isSuccessful()) {
                                Token token = response.body();

                                sharedPreference.saveToken(token.getAccessToken());

                                startLoginActivity();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } else {
                password.setError("You must enter a password");
            }
        } else {
            email.setError("You must enter an email");
        }
    }

    public void startLoginActivity() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }

}

