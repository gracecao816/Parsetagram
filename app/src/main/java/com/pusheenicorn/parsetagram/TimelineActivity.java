package com.pusheenicorn.parsetagram;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

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

    public static final int REQUEST_CODE = 1;

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
    }

    public void fetchTimelineAsync(int page) {
        postAdapter.clear();
        final Post.Query postQuery = new Post.Query();
        postQuery.getTop().withUser();


        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> objects, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < objects.size(); i++) {
                        posts.add(objects.get(objects.size() - 1 - i));
                    }
                    postAdapter.notifyDataSetChanged();
//                    for (int i = 0; i < objects.size(); i++) {
//                        Log.d("HomeActivity", "Post[" + i + "]= "
//                                + objects.get(i).getDescription()
//                                + "\nusername = " + objects.get(i).getUser().getUsername());
//                    }
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
                    for (int i = 0; i < objects.size(); i++) {
                        posts.add(objects.get(objects.size() - 1 - i));
                    }
                    postAdapter.notifyDataSetChanged();
//                    for (int i = 0; i < objects.size(); i++) {
//                        Log.d("HomeActivity", "Post[" + i + "]= "
//                                + objects.get(i).getDescription()
//                                + "\nusername = " + objects.get(i).getUser().getUsername());
//                    }
                } else {
                    e.printStackTrace();
                }
            }
        });
    }

}
