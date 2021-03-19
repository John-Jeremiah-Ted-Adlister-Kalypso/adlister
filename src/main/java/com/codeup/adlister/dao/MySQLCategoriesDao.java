package com.codeup.adlister.dao;

import jdk.jfr.Category;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLCategoriesDao {

    private Connection connection;

    public MySQLCategoriesDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to database.", e);
        }
    }

    public List<String> allCategories() {
        PreparedStatement stmt = null;
        List<String> categoryResult = new ArrayList<>();
        try {
            stmt = connection.prepareStatement("SELECT * FROM categories");
            ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            categoryResult.add(rs.getString("name"));
        }
            return categoryResult;
        } catch (SQLException e) {
            throw new RuntimeException("Error pulling Categories");
        }
    }

    public Category extractCategories(ResultSet rs) throws SQLException {
        return new Category (
                rs.getLong("cat_id"),
                rs.getString("name")
        );
    }

    public List<Category> categoriesFromResults(ResultSet rs) throws SQLException {
        List<Category> categories = new ArrayList<>();
        while (rs.next()) {
            categories.add(extractCategories(rs));
        }
        return categories;
    }

    public List<Category> categoryAdId(long id) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * " +
                    "FROM categories " +
                    "JOIN ads_categories ON categories.id = ads_categories.cat_id " +
                    "JOIN ads ON ads.id = ads_categories.ad_id " +
                    "WHERE ads.id = ?" );
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            return (rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching categories.", e);
        }
    }

    public List<Category> categoryByName(String name) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM categories WHERE title = ?");
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            return categoriesFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving categories.", e);
        }
    }

}
