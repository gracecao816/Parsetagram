package com.pusheenicorn.parsetagram;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pusheenicorn.parsetagram.model.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private List<Post> mPosts;
    Context context;

    //pass in the Tweets array in the constructor
    public PostAdapter(List<Post> posts) {
        mPosts = posts;
    }

    //for each row, inflate the layout and cache references (only invoked if creating a new row)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View tweetView = inflater.inflate(R.layout.item_post, parent, false);
        ViewHolder viewHolder = new ViewHolder(tweetView);
        return viewHolder;
    }

    public void clear() {
        mPosts.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Post> list) {
        mPosts.addAll(list);
        notifyDataSetChanged();
    }
    //bind the values based on the position of the element

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
        //get the data according to position
        Post post = mPosts.get(position);
        //populate the views according to this data
        holder.tvCaption.setText(post.getDescription());
        holder.tvUsername.setText(post.getUser().getUsername());

        //load image into a viewable
        Glide.with(context).load(post.getImage().getUrl()).into(holder.ivImagePost);
    }

    //get item count
    public int getItemCount() {
        return mPosts.size();
    }


    //create the ViewHolder class

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivImagePost;
        public ImageButton ibHeart;
        public ImageView ivComment;
        public ImageView ivDirect;
        public TextView tvUsername;
        public TextView tvCaption;

        public ViewHolder(final View itemView) {
            super(itemView);

            //perform findViewById lookups

            ivImagePost = (ImageView) itemView.findViewById(R.id.ivImagePost);
            ibHeart = (ImageButton) itemView.findViewById(R.id.ibHeart);
            ivComment = (ImageView) itemView.findViewById(R.id.ivComment);
            ivDirect = (ImageView) itemView.findViewById(R.id.ivDirect);
            tvUsername = (TextView) itemView.findViewById(R.id.tvUsername);
            tvCaption = (TextView) itemView.findViewById(R.id.tvCaption);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent openDetail = new Intent(v.getContext(), DetailActivity.class);
                    openDetail.putExtra("Post", mPosts.get(getAdapterPosition()));
                    Activity detailActivity = (Activity) v.getContext();
                    detailActivity.startActivity(openDetail);
                }
            });

        }
    }

}

