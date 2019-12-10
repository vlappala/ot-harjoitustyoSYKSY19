package com.mycompany.ot.arithmetix.dao;

import java.util.*;
import java.sql.*;
import com.mycompany.ot.arithmetix.engine.User;

public class UserDao implements Dao<User, String> {
    
    String url;  // "jdbc:sqlite:./testidata.db";
    
    public UserDao(String dbAddress) {
        
        this.url = "jdbc:sqlite:./" + dbAddress;
        
    }
    
    public void createTablesIfNotExist() {
        
//        String url = "jdbc:sqlite:./testidata.db";
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Users (\n"
                + "    name varchar(255) PRIMARY KEY NOT NULL UNIQUE);";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void create(User user) throws SQLException {
        
        Connection connection = DriverManager.getConnection(url);
        
//        if (read(user.getName()) == null) {
//            return;
//        }

        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Users"
            + " (name)"
            + " VALUES (?)");
        stmt.setString(1, user.getName());
//        stmt.setString(2, user.getPuhelinnumero());
//        stmt.setString(3, user.getKatuosoite());
//        stmt.setInt(4, user.getPostinumero());
//        stmt.setString(5, user.getPostitoimipaikka());

        stmt.executeUpdate();
        stmt.close();
        connection.close();
    }

    @Override
    public User read(String key) throws SQLException {
        
        Connection connection = DriverManager.getConnection(url);

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Users WHERE name = ?");
        stmt.setString(1, key);
        ResultSet rs = stmt.executeQuery();

        // Mikäli tulostaulussa ei ole yhtäkään riviä,
        // palautetaan null-viite
        if (!rs.next()) {
            return null;
        }

        // Edellä siirryttiin ensimmäiselle tulostaulun
        // riville -- luodaan user
        User a = new User(rs.getString("name"));

        stmt.close();
        rs.close();
        connection.close();

        return a;
//            return null;
    }

    @Override
    public User update(User object) throws SQLException {
        // ei toteutettu
        return null;
    }

    @Override
    public void delete(String key) throws SQLException {
        // ei toteutettu
        
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