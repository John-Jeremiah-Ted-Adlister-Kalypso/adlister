package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Image;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLImagesDao implements Images {
    private Connection connection = null;

    public MySQLImagesDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    public List<String> imagesByAdID(long ad_id) {
        List<String> images = new ArrayList<>();
        String sql = "SELECT filepath FROM images AS i JOIN ads_images AS ai ON i.id_image = ai.id_images where ai.ad_id = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, ad_id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                images.add(rs.getString("filepath"));
            }
            return images;
        } catch (SQLException e) {throw new RuntimeException("Error retrieving images for ad #"+ ad_id);}
    }

    public void uploadImage(String filepath, long ad_id){
        String sqlFilepath = "INSERT INTO images (filepath) VALUES ( ? )";
        String sqlAds_Images = "INSERT INTO ads_images (ad_id, id_images) VALUES ( ?, ? )";
        long id_image = 0;
        try {
            PreparedStatement stmt = connection.prepareStatement(sqlFilepath);
            stmt.setString(1, filepath);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            id_image = rs.getLong(1);
        } catch (SQLException e) {throw new RuntimeException("Error adding image to database.");}
        try {
            PreparedStatement stmt = connection.prepareStatement(sqlAds_Images);
            stmt.setLong(1, ad_id);
            stmt.setLong(2, id_image);
            stmt.executeUpdate();
        } catch (SQLException e) {throw new RuntimeException("Error adding images to ads.");}
    }

    @Override
    public void uploadImages(String [] arrayofimagefilepaths, long ad_id) {
        for (int i = 0; i < arrayofimagefilepaths.length; i++) {
            uploadImage(arrayofimagefilepaths[i], ad_id);
        }
    }

}

