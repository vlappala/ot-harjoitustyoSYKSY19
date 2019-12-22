
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
public class ExerciseDao implements Dao<Exercise, String> {
    
    String url;  
    
    public ExerciseDao(String dbAddress) {
        
        this.url = "jdbc:sqlite:./" + dbAddress;
         
        // testitietokannan luomista varten taulujen luontimetodin kutsu:
        createTablesIfNotExist();

        
    }
    
    public void createTablesIfNotExist() {
        

        String sql = "CREATE TABLE IF NOT EXISTS Exercises (\n"
                + "    id integer primary key, \n"
                + "    name varchar(255),\n"
                + "    firstterm integer, \n"
                + "    operation varchar(1),\n"
                + "    secondterm integer,\n"
                + "    correct varchar(1),\n"
                + "    time real,\n"
                + "    date date);";
        
        try (Connection conn = DriverManager.getConnection(this.url);
                Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void create(Exercise ex) throws SQLException {
        
        // Not implemented
    }
    
    public void create(Exercise ex, User u) throws SQLException {
        
        Connection connection = DriverManager.getConnection(url);
        


        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Exercises"
            + " (name, firstterm, operation, secondterm, correct, time, date)"
            + " VALUES (?, ?, ?, ?, ?, ?, datetime('now', 'localtime'))");
        stmt.setString(1, u.getName());
        stmt.setInt(2, ex.getX());
        stmt.setString(3, ex.getOperation());
        stmt.setInt(4, ex.getY());
        stmt.setString(5, ex.getCorrectFi());
        stmt.setDouble(6, ex.getTime());


        stmt.executeUpdate();
        stmt.close();
        connection.close();
        
    }

    @Override
    public Exercise read(String key) throws SQLException {
        
        // Not implemented
        return null;
    }

    @Override
    public Exercise update(Exercise ex) throws SQLException {
        // Not implemented
        return null;
    }

    @Override
    public void delete(String key) throws SQLException {
        // Not implemented
    }

    @Override
    public List<Exercise> list() throws SQLException {
	
        // Not implemented
        return null;
	      
    }
    
    public double averageTime(User u) throws SQLException {
        
        String name = u.getName();
        
        double average = 0;

        Connection conn = DriverManager.getConnection(url);

        PreparedStatement stmt = conn.prepareStatement("SELECT avg(time) FROM Exercises WHERE name = ?");
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();

        average = rs.getDouble(1);

        conn.close();
        

        return average;
    }
    
    public ArrayList<Exercise> list(String username) throws SQLException {
        
        String name = username;
        
        ArrayList<Exercise> exercises = new ArrayList<>();

        Connection conn = DriverManager.getConnection(url);

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Exercises WHERE name = ?");
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            exercises.add(new Exercise(rs.getInt("firstterm"), rs.getString("operation"), rs.getInt("secondterm"), rs.getString("correct"), rs.getDouble("time"), rs.getString("date")));
        }
        conn.close();
        

        return exercises;
    }
    
}
