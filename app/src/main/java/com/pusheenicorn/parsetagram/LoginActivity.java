package com.pusheenicorn.parsetagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {
    EditText etUsername;
    EditText etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (ParseUser.getCurrentUser() != null) {
            ParseUser.logOut();
        }
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogIn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                logIn(username, password);
            }
        });

    }

    public void logIn (String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {

            @Override
            public void done(ParseUser user, com.parse.ParseException e) {
                if (e == null) {
                    Log.d("LoginActivity","Login Successful");
                    Intent intent = new Intent(LoginActivity.this,
                            TimelineActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Log.d("LoginActivity", "Login failure.");
                    e.printStackTrace();
                }
            }
        });
    }

}
