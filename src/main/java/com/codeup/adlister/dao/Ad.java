package com.codeup.adlister.dao;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Ad {
    private long id;
    private long userId;
    private String title;
    private String description;
    private String created_time;
    private String updated_time;
    SimpleDateFormat sdf = new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");

    public Ad(long id, long userId, String title, String description) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
    }

    public Ad(long id, long userId, String title, String description, Date created_time, Date updated_time) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.created_time = sdf.format(created_time);
        this.updated_time = sdf.format(updated_time);
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getUserId() {
        return userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
