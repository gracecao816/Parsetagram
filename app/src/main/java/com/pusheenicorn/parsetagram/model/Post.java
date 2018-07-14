package com.pusheenicorn.parsetagram.model;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.Date;
import java.util.Calendar;

@ParseClassName("Post")
public class Post extends ParseObject {
    private final static String KEY_DESCRIPTION = "description";
    private final static String KEY_IMAGE = "image";
    private final static String KEY_USER = "user";
    private final static String CREATED_AT = "createdAt";
    private final static String LIKES = "likes";


    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }

    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile image) {
        put(KEY_IMAGE, image);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }

    public Number getLikes() {
        return getNumber(LIKES);
    }

    public void setLikes(Number likes) {
        put(LIKES, likes);
    }

    public Date getTime() {
        return getDate(CREATED_AT);
    }

    public void setTime(Date time) {
        put(String.valueOf(Calendar.getInstance().getTime()), time);
    }

    public static class Query extends ParseQuery<Post> {
        public Query() {
            super(Post.class);
        }

        public Query getTop() {
            setLimit(20);
            return this;
        }

        public Query withUser() {
            include("user");
            return this;
        }
    }

}
