package com.pusheenicorn.parsetagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.pusheenicorn.parsetagram.model.Post;

import java.util.ArrayList;
import java.util.List;

public class TimelineActivity extends AppCompatActivity {
    PostAdapter postAdapter;
    ArrayList<Post> posts;
    RecyclerView rvPost;
    private SwipeRefreshLayout swipeContainer;
    Button ibPost;
    ProgressBar miActionProgress;
    private Button logOutButton;
    private ImageButton btnProfile;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        btnProfile = findViewById(R.id.btnProfile);
        //lookup the swipe container view
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        //setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchTimelineAsync(0);
            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_dark, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        ibPost = findViewById(R.id.btnPost);
        logOutButton = findViewById(R.id.btnLogOut);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);


        rvPost = (RecyclerView) findViewById(R.id.rvPost);
        posts = new ArrayList<>();
        postAdapter = new PostAdapter(posts);

        //RecyclerView set up
        rvPost.setLayoutManager(new LinearLayoutManager(this));
        //set the adapter
        rvPost.setAdapter(postAdapter);
        loadTopPosts();


        miActionProgress = findViewById(R.id.miActionProgress);
        logOutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent logOut = new Intent(TimelineActivity.this, HomeActivity.class);
                ParseUser.logOut();
                startActivity(logOut);
            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_compose:
                        Intent composeAction = new Intent(TimelineActivity.this, PostActivity.class);
                        startActivity(composeAction);
                        return true;
                    case R.id.action_profile:
                        Intent profileAction = new Intent(TimelineActivity.this, ProfileActivity.class);
                        startActivity(profileAction);
                        return true;
                    case R.id.action_timeline:
                        Toast.makeText(TimelineActivity.this, "You're already on the timeline!", Toast.LENGTH_LONG).show();
                        return true;
                }
                return false;
            }
        });
    }

    public void fetchTimelineAsync(int page) {
        postAdapter.clear();
        final Post.Query postQuery = new Post.Query();
        postQuery.getTop().withUser();


        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if (e == null) {
                    showProgressBar();
                    for (int i = 0; i < objects.size(); i++) {
                        posts.add(objects.get(objects.size() - 1 - i));
                    }
                    postAdapter.notifyDataSetChanged();
                    hideProgressBar();
                } else {
                    e.printStackTrace();
                }
            }
        });
        swipeContainer.setRefreshing(false);
    }

    private void loadTopPosts() {
        final Post.Query postQuery = new Post.Query();
        postQuery.getTop().withUser();


        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if (e == null) {
                    showProgressBar();
                    for (int i = 0; i < objects.size(); i++) {
                        posts.add(objects.get(objects.size() - 1 - i));
                    }
                    postAdapter.notifyDataSetChanged();
                    hideProgressBar();
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

    public void onPostClick(View v) {
        Intent i = new Intent(TimelineActivity.this, PostActivity.class);
        startActivity(i);
    }

    public void showProgressBar() {
        // Show progress item
        miActionProgress.setVisibility(View.VISIBLE);
    }

    public void hideProgressBar() {
        // Hide progress item
        miActionProgress.setVisibility(View.INVISIBLE);
    }

    public void onProfileClick(View v) {
        Intent goProfile = new Intent(TimelineActivity.this, ProfileActivity.class);
        startActivity(goProfile);
    }

}
