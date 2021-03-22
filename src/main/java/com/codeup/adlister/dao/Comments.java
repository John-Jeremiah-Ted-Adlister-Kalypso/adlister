package com.codeup.adlister.dao;

import com.codeup.adlister.models.Comment;

import java.util.List;

public interface Comments {
    List<Comment> commentsByAd(long ad_id);
    Comment commentByID(String created_time);
//    void deleteByID (long id);
}
