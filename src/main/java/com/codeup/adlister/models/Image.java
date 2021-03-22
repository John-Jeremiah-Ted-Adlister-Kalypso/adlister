package com.codeup.adlister.models;

import java.text.SimpleDateFormat;

public class Image {
    private long id;
    private String filepath;
    private long ad_id;
    private final String SAVE_DIR = "img/uploadFiles";
    private String linkpath = SAVE_DIR + filepath;
    private SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

    public String getSaveDir() {
        return SAVE_DIR;
    }

    public SimpleDateFormat getSdf() {
        return sdf;
    }

    public Image(long id, String filepath) {
        this.id = id;
        this.filepath = filepath;
    }

    public Image(long id, String filepath, long ad_id) {
        this.id = id;
        this.filepath = filepath;
        this.ad_id = ad_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public long getAd_id() {
        return ad_id;
    }

    public void setAd_id(long ad_id) {
        this.ad_id = ad_id;
    }
}
