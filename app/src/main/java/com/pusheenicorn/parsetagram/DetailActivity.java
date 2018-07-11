package com.pusheenicorn.parsetagram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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

    }
}
