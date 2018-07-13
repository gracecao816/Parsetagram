package com.pusheenicorn.parsetagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.parse.ParseUser;

public class ProfileActivity extends AppCompatActivity {
    private Button logOutButton;
    private ImageButton ibHome;
    private ImageButton ibProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        logOutButton = findViewById(R.id.btnLogOut);
        ibHome = findViewById(R.id.ibHome);
        ibProfileImage = findViewById(R.id.ibProfileImage);

        logOutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent logOut = new Intent(ProfileActivity.this, HomeActivity.class);
                ParseUser.logOut();
                startActivity(logOut);
            }
        });

        ibHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnHome = new Intent(ProfileActivity.this, TimelineActivity.class);
                startActivity(returnHome);
            }
        });
    }
}
