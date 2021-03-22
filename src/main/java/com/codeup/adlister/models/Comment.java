package com.codeup.adlister.models;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class Comment {
    private long id;
    private long ad_id;
    private String comment;
    private long user_id;
    private String created_time;
    private SimpleDateFormat sdf = new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");

    Comment(long id, long ad_id, String comment, long user_id, String created_time) {
        this.id = id;
        this.ad_id = ad_id;
        this.user_id = user_id;
        this.comment = comment;
        this.created_time = created_time;
    }

    Comment (long ad_id, String comment, long user_id, Date created_time) {
        this.ad_id = ad_id;
        this.user_id = user_id;
        this.comment = comment;
        this.created_time = sdf.format(created_time);
    }



    public long getAd_id() {
        return ad_id;
    }

    public void setAd_id(long ad_id) {
        this.ad_id = ad_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }
}
