/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ot.arithmetix.dao;

import com.mycompany.ot.arithmetix.engine.User;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.List;
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
public class UserDaoTest {
    
    private UserDao ud;
    
    public UserDaoTest() {
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
        
        this.ud = new UserDao(dbAddress);
        
         
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
        
        assertTrue(this.ud.url != null);
        
    }
    
    @Test
    public void listWorks() throws SQLException {
        
        List<User> x = this.ud.list();
        
        assertTrue(x.size()==1);
    }
}
