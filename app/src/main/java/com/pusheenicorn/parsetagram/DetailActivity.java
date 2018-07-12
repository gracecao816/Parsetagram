package com.pusheenicorn.parsetagram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pusheenicorn.parsetagram.model.Post;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailActivity extends AppCompatActivity {
    ImageView ivImageDetail;
    TextView tvUserDetail;
    TextView tvCaptionDetail;
    ImageButton ibLikeDetail;
    TextView tvLocationDetail;
    ImageButton ibSaveDetail;
    TextView tvNameUserDetail;
    ImageButton ibCommentDetail;
    ImageButton ibDirectDetail;
    TextView tvTime;
    ImageButton ibLikedPost;
    Date date;
    boolean likePost;

    Post post;
    private ImageButton refreshButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivImageDetail = (ImageView) findViewById(R.id.ivImageDetail);
        tvUserDetail = (TextView) findViewById(R.id.tvUserDetail);
        tvCaptionDetail = (TextView) findViewById(R.id.tvCaptionDetail);
        ibLikeDetail = (ImageButton) findViewById(R.id.ibLikeDetail);
        tvLocationDetail = (TextView) findViewById(R.id.tvLocationDetail);
        ibSaveDetail = (ImageButton) findViewById(R.id.ibSaveDetail);
        tvNameUserDetail = (TextView) findViewById(R.id.tvNameUserDetail);
        ibCommentDetail = (ImageButton) findViewById(R.id.ibCommentDetail);
        ibDirectDetail = (ImageButton) findViewById(R.id.ibDirectDetail);
        refreshButton = findViewById(R.id.refresh_btn);
        tvTime = (TextView) findViewById(R.id.tvTime);
        ibLikedPost = (ImageButton) findViewById(R.id.ibLikedPost);

        post = (Post) getIntent().getParcelableExtra("Post");
        tvNameUserDetail.setText(post.getUser().getUsername());
        tvCaptionDetail.setText(post.getDescription());
        tvUserDetail.setText(post.getUser().getUsername());
        date = post.getCreatedAt();
        DateFormat dateFormat = new SimpleDateFormat( "EEE MMM dd HH:mm:ss ZZZZZ yyyy");
        String formattedDate = dateFormat.format(date);



        tvTime.setText(getRelativeTimeAgo(formattedDate));
        //load image using glide
        Glide.with(DetailActivity.this).load(post.getImage().getUrl())
                .into(ivImageDetail);

    }

    public static String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }

    public void onLikeClick(View v) {
        if (!likePost) {
            likePost = true;
            ibLikeDetail.setVisibility(View.INVISIBLE);
            ibLikedPost.setVisibility(View.VISIBLE);
        } else if (likePost) {
            likePost = false;
            ibLikeDetail.setVisibility(View.VISIBLE);
            ibLikedPost.setVisibility(View.INVISIBLE);
        }
    }
}
