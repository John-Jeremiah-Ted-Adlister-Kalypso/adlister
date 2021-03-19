package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        String query = "SELECT * FROM ads WHERE user_id = ?";
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
            rs.getTimestamp("updated_time")
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
                    rs.getTimestamp("updated_time")
            ));
        }
        return ads;
    }
}
