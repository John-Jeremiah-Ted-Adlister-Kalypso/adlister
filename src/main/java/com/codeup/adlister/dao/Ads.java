package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.util.List;

public interface Ads {
    // get a list of all the ads
    List<Ad> all();
    // insert a new ad and return the new ad's id
    Long insert(Ad ad);
    List<Ad> byUserID(long id);
    Boolean deleteByID(long id);
    Ad byAdID(long id);
    public void updateAdByID(long id, String title, String description);
}
