package com.pusheenicorn.parsetagram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.pusheenicorn.parsetagram.model.Post;

import java.util.ArrayList;
import java.util.List;

public class TimelineActivity extends AppCompatActivity {
    private ImageButton refreshButton;
    PostAdapter postAdapter;
    ArrayList<Post> posts;
    RecyclerView rvPost;
    private SwipeRefreshLayout swipeContainer;
    Button ibPost;
    ProgressBar miActionProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        refreshButton = findViewById(R.id.refresh_btn);
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

        rvPost = (RecyclerView) findViewById(R.id.rvPost);
        posts = new ArrayList<>();
        postAdapter = new PostAdapter(posts);

        //RecyclerView set up
        rvPost.setLayoutManager(new LinearLayoutManager(this));
        //set the adapter
        rvPost.setAdapter(postAdapter);
        loadTopPosts();

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadTopPosts();
            }
        });

        miActionProgress = findViewById(R.id.miActionProgress);
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

}
