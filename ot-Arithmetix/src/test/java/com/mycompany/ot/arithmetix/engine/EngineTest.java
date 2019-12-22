/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ot.arithmetix.engine;

import com.mycompany.ot.arithmetix.dao.ExerciseDao;
import com.mycompany.ot.arithmetix.dao.UserDao;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class EngineTest {
    
    Engine engine;
    String testiStringi = "xyxy1010";
    User testUser;
    
    
    
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
        
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("TESTconfig.properties"));
        }
        catch (Exception e) {
            System.out.println("Virhe konfiguraatiotiedoston lataamisessa: "+e.toString());
        }
    
        String dbAddress = properties.getProperty("dbFile");
        
        UserDao x = new UserDao(dbAddress);
        
        
        this.engine = new Engine(x, new ExerciseDao(dbAddress));
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
    public void hasUserWorksCorrectlyPositive() {
        
        try {
            this.engine.createUser(testiStringi);
        }
        catch (Exception e) {
            System.out.println("Virhe: "+e.toString());
        }
        
        assertEquals(true, this.engine.hasUser());
        
    }
    
    @Test
    public void getUserDaoWorks() {
        
        UserDao ud = this.engine.getUserDao();
        
        assertTrue(ud != null);
    }
    
    @Test
    public void createExerciseWorks() {
        this.engine.newExercise();
        Exercise ex = this.engine.getExercise();
        
        assertTrue(ex != null);
    }
    
    @Test
    public void createdUserHasCorrectName() {
        
        try {
            this.engine.createUser(testiStringi);
        }
        catch (Exception e) {
            System.out.println("Virhe: "+e.toString());
        }
        
        
        
        assertEquals(testiStringi, this.engine.getUser().getName());
        cleanTestUser();
    }
    
    @Test
    public void userLogsOutCorrectly() {
        
        try {
            this.engine.createUser(testiStringi);
        }
        catch (Exception e) {
            System.out.println("Virhe: "+e.toString());
        }
        
        this.engine.logoutUser();
        
        assertEquals(false, this.engine.hasUser());
        cleanTestUser();
    }
    
    @Test
    public void getUserListWorks() {
        
        ArrayList<User> x = new ArrayList<>();
        
        
//        try {
//            this.engine.getUsersFromDatabase();
//            x = this.engine.getUserList();
//        }
//        catch (SQLException e) {
//            System.out.println("Virhe käyttäjälistan lataamisessa!");
//        }
        
        assertEquals(x, this.engine.getUserList());
        x.clear();

    }
    
    @Test
    public void getExerciseDaoWorks() {
        
        ExerciseDao ed = this.engine.getExerciseDao();
        
        assertEquals(ed, this.engine.getExerciseDao());

    }
    
    @Test
    public void answerInGoodFormatWorksTooSmallString() {
        
        String test = "xy";
        
        assertEquals(false, this.engine.answerInGoodFormat(test));

    }
    
    @Test
    public void answerInGoodFormatWorksTooLargeString() {
        
        String test = "xyysysysysysys";
        
        assertEquals(false, this.engine.answerInGoodFormat(test));

    }
    
    @Test
    public void answerInGoodFormatWorksMinusThree() {
        
        String test = "-3";
        
        assertEquals(true, this.engine.answerInGoodFormat(test));

    }
    
    @Test
    public void answerInGoodFormatWorksPlusThree() {
        
        String test = "3";
        
        assertEquals(true, this.engine.answerInGoodFormat(test));

    }
    
    @Test
    public void answerInGoodFormatWorksMinusNonsense() {
        
        String test = "-,jh";
        
        assertEquals(false, this.engine.answerInGoodFormat(test));

    }
    
    @Test
    public void answerInGoodFormatWorksOnlyMinus() {
        
        String test = "-";
        
        assertEquals(false, this.engine.answerInGoodFormat(test));

    }
    
    @Test
    public void answerInGoodFormatWorksEmptyString() {
        
        String test = "";
        
        assertEquals(false, this.engine.answerInGoodFormat(test));

    }
    
    @Test
    public void answerInGoodFormatWorksBadChar() {
        
        String test = "/";
        
        assertEquals(false, this.engine.answerInGoodFormat(test));

    }
    
    @Test
    public void answerInGoodFormatWorksMinusBadChar() {
        
        String test = "-/";
        
        assertEquals(false, this.engine.answerInGoodFormat(test));

    }
    
    @Test
    public void answerInGoodFormatWorksMinusBadChar2() {
        
        String test = "-;";
        
        assertEquals(false, this.engine.answerInGoodFormat(test));

    }
    
    @Test
    public void processAnswerWorksPositive() throws Exception {
        
        
        this.engine.createUser(testiStringi);
        
        this.engine.newExercise();
        Exercise ex = this.engine.getExercise();
        String correctAnswer = ""+ex.getAnswer();
        
        assertEquals(true, this.engine.processAnswer(correctAnswer));
        cleanTestUser();
    }
    
    @Test
    public void processAnswerWorksNegative() throws Exception {
        
        
        this.engine.createUser(testiStringi);
        
        this.engine.newExercise();
        Exercise ex = this.engine.getExercise();
        String correctAnswer = ""+(ex.getAnswer()-1);
        
        assertEquals(false, this.engine.processAnswer(correctAnswer));
        cleanTestUser();
    }
    
//    @Test
//    public void userIsDeletedCorrectly() {
//        try {
//            this.engine.createUser(testiStringi);
//            this.engine.deleteUser(testiStringi);
//        }
//        catch (Exception e) {
//            System.out.println("Virhe testikäyttäjän poistamisessa!");
//        }
//        
//        assertEquals(this.engine.hasUser(), false);
//    }
}
