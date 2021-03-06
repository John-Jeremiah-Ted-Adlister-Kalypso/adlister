package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class MySQLUsersDao implements Users {
    private Connection connection;

    public MySQLUsersDao(Config config) {
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
    public User findByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ? LIMIT 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            return extractUser(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a user by username", e);
        }
    }

    @Override
    public User findByUserID(long id) {
        String query = "SELECT * FROM users WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setLong(1,id);
            return extractUser(stmt.executeQuery());

        } catch (SQLException e) {throw new RuntimeException("Error finding a user by ID", e);}
    }

    @Override
    public Long insert(User user) {
        String query = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating new user", e);
        }
    }

    @Override
    public void updateAvatar(String filepath, long id) {
        String query = "UPDATE users SET avatar_filepath = ? WHERE id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,filepath);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {throw new RuntimeException("Error updating user's Profile pictuer.");}
    }

    public void updateUser(User user) {
        String sql = "UPDATE users SET email=?, password=? WHERE id = ?";
        try {PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setLong(3, user.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {throw new RuntimeException("Error updating profile for user id #"+ user.getId());}
    }


    private User extractUser(ResultSet rs) throws SQLException {
        if (! rs.next()) {
            return null;
        }
        return new User(
            rs.getLong("id"),
            rs.getString("username"),
            rs.getString("email"),
            rs.getString("password"),
            rs.getString("avatar_filepath")
        );
    }

}
