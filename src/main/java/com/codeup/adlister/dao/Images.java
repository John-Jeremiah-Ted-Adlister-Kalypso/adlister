package com.codeup.adlister.dao;

import com.codeup.adlister.models.Image;

import java.util.List;

public interface Images {

    List<String> imagesByAdID(long ad_id);
    void uploadImage(String filepath, long ad_id);
    void uploadImages(String [] arrayofimagefilepaths, long ad_id);

}
