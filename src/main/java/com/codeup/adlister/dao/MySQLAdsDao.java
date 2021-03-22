package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class MySQLAdsDao implements Ads {
    private Connection connection = null;

    public MySQLAdsDao(Config config) {
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

    @Override
    public List<Ad> all() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads");
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public List<Ad> byUserID(long id) {
        String query = "SELECT * FROM ads WHERE user_id= ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1,id);
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {throw new RuntimeException("Error finding ads by user ID", e);}
    }

    @Override
    public Long insert(Ad ad) {
        try {
            String insertQuery = "INSERT INTO ads(user_id, title, description) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, ad.getUserId());
            stmt.setString(2, ad.getTitle());
            stmt.setString(3, ad.getDescription());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }

    @Override

    public Boolean deleteByID(long id) {

        String query = "DELETE FROM ads WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1,id);
            return stmt.execute();
        } catch (SQLException e) {throw new RuntimeException("Error deleting ad.", e);}
    }

    @Override
    public Ad byAdID(long id) {
        String query = "SELECT * FROM ads WHERE id = ? LIMIT 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1,id);
            ResultSet rs = stmt.executeQuery();
            List <Ad> ads = createAdsFromResults(rs);
            return ads.get(0);
        } catch (SQLException e) {throw new RuntimeException("Error finding ad by ID", e);}
    }

    @Override
    public void updateAdByID(long id, String title, String description) {
        String query ="UPDATE ads SET title=?, description=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setLong(3, id);
            stmt.executeUpdate();
//            return rows;
        } catch (SQLException e) {throw new RuntimeException("Error updating ad with id " + id);}
    }

    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
            rs.getLong("id"),
            rs.getLong("user_id"),
            rs.getString("title"),
            rs.getString("description"),
            rs.getTimestamp("created_time"),
            rs.getTimestamp("updated_time"),
            getCategoriesByAdID(rs.getLong("id"))
        );
    }

    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }
    @Override
    public List<Ad> search(String term){
        String sql = "SELECT * FROM ads WHERE title LIKE ?";
        String searchTermWithWildcards = "%" + term + "%";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, searchTermWithWildcards);

            ResultSet rs = stmt.executeQuery();
            return generateAds(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
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

    @Override
    public List<String> getCategoriesByAdID(long id) {
        List<String> categories = new ArrayList<>();
        String sql = "SELECT name FROM categories " +
                "JOIN ads_categories ON categories.cat_id = ads_categories.cat_id JOIN ads ON ads.id = ads_categories.ad_id  WHERE ads.id = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                categories.add(rs.getString("name"));
            }
            return categories;
        } catch (SQLException e) {throw new RuntimeException("Error finding ad categories with id " + id);}
    }

    @Override
    public void addCategoriesByAdID(long id, String[] categories) {
        String sql = "INSERT INTO ads_categories (ad_id, cat_id)" +
                "VALUES (?,?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            for (int i = 0; i < categories.length; i++) {
            stmt.setLong(1, id);
            stmt.setLong(2,parseInt(categories[i]));
            stmt.executeUpdate();
            }

        } catch (SQLException e) {throw new RuntimeException("Error adding categories to Ad");}
    }

    public void deleteAllCategoriesByAdID(long id) {
        String sql = "DELETE FROM ads_categories WHERE ad_id = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.execute();
        } catch (SQLException e) {throw new RuntimeException("Error updating categories to Ad");}
    }

    // transforms the resultset into a java list
    private List<Ad> generateAds(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()){
            ads.add(new Ad(
                    rs.getLong("id"),
                    rs.getLong("user_id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getTimestamp("created_time"),
                    rs.getTimestamp("updated_time"),
                    getCategoriesByAdID(rs.getLong("id"))
            ));
        }
        return ads;
    }
}
