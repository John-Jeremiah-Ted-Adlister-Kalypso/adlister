package com.codeup.adlister.models;

import com.codeup.adlister.util.Password;

public class User {
    private long id;
    private String username;
    private String email;
    private String password;
    private String avatar_filepath;

    public User() {}

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        setPassword(password);
    }

    public User(long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        setPassword(password);
    }

    public User(long id, String username, String email, String password, String avatar_filepath) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar_filepath = avatar_filepath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = Password.hash(password);
    }

    public String getAvatar_filepath() {
        return avatar_filepath;
    }

    public void setAvatar_filepath(String avatar_filepath, long id) {
        this.avatar_filepath = avatar_filepath;
    }
}
