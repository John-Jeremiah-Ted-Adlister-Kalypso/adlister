package com.codeup.adlister.models;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class Ad {
    private long id;
    private long userId;
    private String title;
    private String description;
    private String created_time;
    private String updated_time;
    private List<String> categories;
    SimpleDateFormat sdf = new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss");

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(String updated_time) {
        this.updated_time = updated_time;
    }



    public Ad(long id, long userId, String title, String description) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
    }

    public Ad(long userId, String title, String description) {
        this.userId = userId;
        this.title = title;
        this.description = description;
    }

    public Ad(long id, long userId, String title, String description, Timestamp created_time, Timestamp updated_time) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.created_time = sdf.format(created_time);
        this.updated_time = sdf.format(updated_time);
    }

    public Ad(long id, long userId, String title, String description, Timestamp created_time, Timestamp updated_time, List<String> categories) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.created_time = sdf.format(created_time);
        this.updated_time = sdf.format(updated_time);
        this.categories = categories;
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
