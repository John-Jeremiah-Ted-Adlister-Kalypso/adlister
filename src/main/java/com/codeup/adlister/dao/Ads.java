package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.util.List;

public interface Ads {
    List<Ad> all();
    Long insert(Ad ad);
    List<Ad> search(String term);
    List<Ad> byUserID(long id);
    Boolean deleteByID(long id);
    Ad byAdID(long id);
    public void updateAdByID(long id, String title, String description);

}
