package com.codeup.adlister.models;

public class Category {

    private long id;
    private String category;
    private long catId;

    // Javabean
    public Category(){};

    public Category(long id, long catId, String category) {
        this.id = id;
        this.catId = catId;
        this.category = category;
    }

    public long getPostId() {
        return id;
    }
    public void setPostId(long id) {
        this.id = id;
    }

    public long getCatId() {
        return catId;
    }
    public void setCatId(long catId) {
        this.catId = catId;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

}
