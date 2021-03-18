package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.util.List;

public interface Ads {
    List<Ad> all();
    Long insert(Ad ad);

    List<Ad> byUserID(long id);
    void deleteByID(long id);

    List<Ad> search(String term);
}
