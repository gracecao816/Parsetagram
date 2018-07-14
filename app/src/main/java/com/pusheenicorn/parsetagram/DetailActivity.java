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
    Date date;
    int count = 0;
    TextView tvNumLikes;
    boolean likePost = false;

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
        refreshButton = findViewById(R.id.btnProfile);
        tvTime = (TextView) findViewById(R.id.tvTime);
        tvNumLikes = (TextView) findViewById(R.id.tvNumLikes);

        post = (Post) getIntent().getParcelableExtra("Post");
        tvNameUserDetail.setText(post.getUser().getUsername());
        tvCaptionDetail.setText(post.getDescription());
        tvUserDetail.setText(post.getUser().getUsername());
        date = post.getCreatedAt();
        DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy");
        String formattedDate = dateFormat.format(date);


        tvTime.setText(getRelativeTimeAgo(formattedDate));
        //load image using glide
        Glide.with(DetailActivity.this).load(post.getImage().getUrl())
                .into(ivImageDetail);
        ibLikeDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count == 0) {
                    count++;
                    tvNumLikes.setText(String.valueOf(count));
                    ImageButton ibLikeDetail = (ImageButton) v;
                    if (!likePost) {
                        ibLikeDetail.setImageResource(R.drawable.ufi_heart_active);
                    } else if (likePost) {
                        ibLikeDetail.setImageResource(R.drawable.ufi_heart);
                    }
                    likePost = !likePost;
                } else if (count == 1) {
                    count--;
                    tvNumLikes.setText(String.valueOf(count));
                    ImageButton ibLikeDetail = (ImageButton) v;
                    if (!likePost) {
                        ibLikeDetail.setImageResource(R.drawable.ufi_heart_active);

                    } else if (likePost) {
                        ibLikeDetail.setImageResource(R.drawable.ufi_heart);
                    }
                    likePost = !likePost;
                }
            }
        });

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
}
