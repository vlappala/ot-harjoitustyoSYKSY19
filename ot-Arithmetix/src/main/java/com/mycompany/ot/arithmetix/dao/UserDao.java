package com.mycompany.ot.arithmetix.dao;

import java.util.*;
import java.sql.*;
import com.mycompany.ot.arithmetix.engine.User;

public class UserDao implements Dao<User, String> {
    
    String url;  
    
    public UserDao(String dbAddress) {
        
        this.url = "jdbc:sqlite:./" + dbAddress;
        
        // testitietokannan luomista varten taulujen luontimetodin kutsu:
        createTablesIfNotExist();
        
    }
    
    public void createTablesIfNotExist() {
        

        String sql = "CREATE TABLE IF NOT EXISTS Users (\n"
                + "    name varchar(255) PRIMARY KEY NOT NULL UNIQUE);";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void create(User user) throws SQLException {
        
//        Connection connection = DriverManager.getConnection(url);
//        
//
//        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Users"
//            + " (name)"
//            + " VALUES (?)");
//        stmt.setString(1, user.getName());
//
//        stmt.executeUpdate();
//        stmt.close();
//        connection.close();
    }
    
    public User create(String userName) throws SQLException {
        
        User newUser = new User(userName);
        
        Connection connection = DriverManager.getConnection(url);
        

        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Users"
            + " (name)"
            + " VALUES (?)");
        stmt.setString(1, newUser.getName());

        stmt.executeUpdate();
        stmt.close();
        connection.close();
        
        return newUser;
    }

    @Override
    public User read(String key) throws SQLException {
        
        Connection connection = DriverManager.getConnection(url);

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Users WHERE name = ?");
        stmt.setString(1, key);
        ResultSet rs = stmt.executeQuery();


        if (!rs.next()) {
            return null;
        }


        User a = new User(rs.getString("name"));

        stmt.close();
        rs.close();
        connection.close();

        return a;

    }

    @Override
    public User update(User object) throws SQLException {
        // Not implemented
        return null;
    }

    @Override
    public void delete(String key) throws SQLException {
        
        
        Connection connection = DriverManager.getConnection(url);

        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Users WHERE name = ?");
        stmt.setString(1, key);
        stmt.executeUpdate();
                

 
        stmt.close();
        
        connection.close();

    }

    @Override
    public List<User> list() throws SQLException {
	
        List<User> users = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url);
            ResultSet result = conn.prepareStatement("SELECT * FROM Users").executeQuery()) {

            while (result.next()) {
                users.add(new User(result.getString("name")));
            }
            conn.close();
        }

        return users;
	      
    }
}