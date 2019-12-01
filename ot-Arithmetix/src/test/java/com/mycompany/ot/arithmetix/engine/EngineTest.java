/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ot.arithmetix.engine;

import com.mycompany.ot.arithmetix.dao.UserDao;
import java.util.Random;
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
public class EngineTest {
    
    Engine engine;
    String testiStringi = "xyxy1010";
    
    
    
    public EngineTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        UserDao x = new UserDao();
        this.engine = new Engine(x);
        cleanTestUser();
        
        

    }
    
    public void cleanTestUser() {
        
        try {
            this.engine.deleteUser(testiStringi);
        }
        catch (Exception e) {
            System.out.println("Virhe testikäyttäjän poistamisessa!");
        }
        
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
    public void userIsCreatedCorrectly() {
        
        try {
            this.engine.createUser(testiStringi);
        }
        catch (Exception e) {
            System.out.println("Virhe: "+e.toString());
        }
        
        User testattava = this.engine.getUser();
        
        assertTrue(testattava!=null);
        cleanTestUser();
    }
    
    @Test
    public void createdUserHasCorrectName() {
        
        try {
            this.engine.createUser(testiStringi);
        }
        catch (Exception e) {
            System.out.println("Virhe: "+e.toString());
        }
        
        User testattava = this.engine.getUser();
        
        assertEquals(this.engine.getUser().getName(), testiStringi);
        cleanTestUser();
    }
}
