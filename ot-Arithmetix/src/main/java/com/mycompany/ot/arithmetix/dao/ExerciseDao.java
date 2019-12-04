/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ot.arithmetix.dao;

import com.mycompany.ot.arithmetix.engine.Exercise;
import com.mycompany.ot.arithmetix.engine.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vlappala
 */
public class ExerciseDao implements Dao<Exercise, String>{
    
    String url;  // "jdbc:sqlite:./testidata.db";
//    UserDao userDao;
    
    public ExerciseDao(String dbAddress) {
        
        this.url = "jdbc:sqlite:./"+dbAddress;
//        this.userDao = ud;
        
    }
    
    public void createTablesIfNotExist() {
        
//        String url = "jdbc:sqlite:./testidata.db";
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Exercises (\n"
                + "    id integer primary key, \n"
                + "    name varchar(255),\n"
                + "    firstterm integer, \n"
                + "    operation varchar(1),\n"
                + "    secondterm integer,\n"
                + "    correct varchar(1),\n"
                + "    time real);";
        
        try (Connection conn = DriverManager.getConnection(this.url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void create(Exercise ex) throws SQLException {
        
//        Connection connection = DriverManager.getConnection(url);
//        
////        if (read(user.getName()) == null) {
////            return;
////        }
//
//        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Users"
//            + " (name)"
//            + " VALUES (?)");
//        stmt.setString(1, user.getName());
////        stmt.setString(2, user.getPuhelinnumero());
////        stmt.setString(3, user.getKatuosoite());
////        stmt.setInt(4, user.getPostinumero());
////        stmt.setString(5, user.getPostitoimipaikka());
//
//        stmt.executeUpdate();
//        stmt.close();
//        connection.close();
    }
    
    public void create(Exercise ex, User u) throws SQLException {
        
        Connection connection = DriverManager.getConnection(url);
        
//        if (read(user.getName()) == null) {
//            return;
//        }

        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Exercises"
            + " (name, firstterm, operation, secondterm, correct, time)"
            + " VALUES (?, ?, ?, ?, ?, ?)");
        stmt.setString(1, u.getName());
        stmt.setInt(2, ex.getX());
        stmt.setString(3, ex.getOperation());
        stmt.setInt(4, ex.getY());
        stmt.setString(5, ex.getCorrect());
        stmt.setDouble(6, ex.getTime());
//        stmt.setString(3), ex.getOperation());
//        stmt.setString(2, user.getPuhelinnumero());
//        stmt.setString(3, user.getKatuosoite());
//        stmt.setInt(4, user.getPostinumero());
//        stmt.setString(5, user.getPostitoimipaikka());

        stmt.executeUpdate();
        stmt.close();
        connection.close();
        
    }

    @Override
    public Exercise read(String key) throws SQLException {
        
//        Connection connection = DriverManager.getConnection(url);
//
//        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Users WHERE name = ?");
//        stmt.setString(1, key);
//        ResultSet rs = stmt.executeQuery();
//
//        // Mikäli tulostaulussa ei ole yhtäkään riviä,
//        // palautetaan null-viite
//        if (!rs.next()) {
//            return null;
//        }
//
//        // Edellä siirryttiin ensimmäiselle tulostaulun
//        // riville -- luodaan user
//        User a = new User(rs.getString("name"));
//
//        stmt.close();
//        rs.close();
//        connection.close();
//
//        return a;
          return null;
    }

    @Override
    public Exercise update(Exercise ex) throws SQLException {
        // ei toteutettu
        return null;
    }

    @Override
    public void delete(String key) throws SQLException {
        // ei toteutettu
        
//        Connection connection = DriverManager.getConnection(url);
//
//        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Users WHERE name = ?");
//        stmt.setString(1, key);
//        stmt.executeUpdate();
//                
//
// 
//        stmt.close();
//        
//        connection.close();

    }

    @Override
    public List<Exercise> list() throws SQLException {
	
//        List<User> users = new ArrayList<>();
//
//        try (Connection conn = DriverManager.getConnection(url);
//            ResultSet result = conn.prepareStatement("SELECT * FROM Users").executeQuery()) {
//
//            while (result.next()) {
//                users.add(new User(result.getString("name")));
//            }
//            conn.close();
//        }
//
//        return users;
    return null;
	      
    }
    public ArrayList<Exercise> list(User u) throws SQLException {
        
        String name = u.getName();
        
        ArrayList<Exercise> exercises = new ArrayList<>();

        Connection conn = DriverManager.getConnection(url);
//            ResultSet result = conn.prepareStatement("SELECT * FROM Exercise WHERE name = ?").executeQuery()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Exercises WHERE name = ?");
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                exercises.add(new Exercise(rs.getInt("firstterm"), rs.getString("operation"), rs.getInt("secondterm"), rs.getString("correct"), rs.getDouble("time")));
            }
            conn.close();
        

        return exercises;
    }
    
}
