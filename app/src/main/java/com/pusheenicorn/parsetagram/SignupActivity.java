package com.pusheenicorn.parsetagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {
    EditText etUsername;
    EditText etPassword;
    EditText etEmail;
    Button btnSignUp;
    ParseUser user;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        // Create the ParseUser
//        ParseUser user = new ParseUser();
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etEmail = (EditText) findViewById(R.id.etEmail);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        user = new ParseUser();

//        String username = etUsername.getText().toString();
//        String password = etPassword.getText().toString();
//        String email = etEmail.getText().toString();
//
//
//
//        // Set core properties
//        user.setUsername(username);
//        user.setPassword(password);
//        user.setEmail(email);

        // Set custom properties
//        user.put("phone","650-253-0000");
        // Invoke signUpInBackground

//        user.signUpInBackground(new SignUpCallback() {
//            @Override
//            public void done(com.parse.ParseException e) {
//                if (e == null) {
//                    // Hooray! Let them use the app now.
//                } else {
//                    // Sign up didn't succeed. Look at the ParseException
//                    // to figure out what went wrong
//                }
//            }
//
//        });
    }

    public void signUpOnClick (View v) {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String email = etEmail.getText().toString();



        // Set core properties
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("SignupActivity","Sign Up Successful");
                    Intent intent = new Intent(SignupActivity.this,
                            PostActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Log.d("SignupActivity", "Sign up failure.");
                    e.printStackTrace();
                }
            }
        });
    }
}
