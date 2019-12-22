/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ot.arithmetix.dao;

import com.mycompany.ot.arithmetix.engine.Exercise;
import com.mycompany.ot.arithmetix.engine.User;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.Properties;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vlappala
 */
public class ExerciseDaoTest {
    
    private ExerciseDao ed;
    
    public ExerciseDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("TESTconfig.properties"));
        }
        catch (Exception e) {
            System.out.println("Virhe konfiguraatiotiedoston lataamisessa: "+e.toString());
        }
    
        String dbAddress = properties.getProperty("dbFile");
        
        this.ed = new ExerciseDao(dbAddress);
        
         
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void databaseAddressExists() {
        
        assertTrue(this.ed.url != null);
        
    }
    
    
//    @Test
//    public void getAverageWorks() throws SQLException {
//        
//        User testuser = new User("testuser");
//        
//        double d = 1.5;
//        
//        double testvalue = this.ed.averageTime(testuser);
//
//        assertTrue(testvalue==d);
//    }
}
