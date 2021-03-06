package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;

import java.util.List;

public interface Users {
    User findByUsername(String username);
    User findByUserID(long id);
    Long insert(User user);
    void updateAvatar(String filepath, long id);
    void updateUser(User user);
}
