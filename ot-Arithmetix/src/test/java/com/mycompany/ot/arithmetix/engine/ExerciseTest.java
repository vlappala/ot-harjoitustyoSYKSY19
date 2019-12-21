/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ot.arithmetix.engine;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author t430
 */
public class ExerciseTest {
    
    private Random randomi;
    
    public ExerciseTest() {
        this.randomi = new Random();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
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
    public void exerciseHasCorrectTerms(){
        
        int z = this.randomi.nextInt(100);
        int w = this.randomi.nextInt(100);
        
        Exercise ex = new Exercise(z, w);
        
        assertEquals(z, ex.getX());
        assertEquals(w, ex.getY());
    }
    
    @Test
    public void exerciseHasCorrectAnswer() {
        int z = this.randomi.nextInt(100);
        int w = this.randomi.nextInt(100);
        
        Exercise ex = new Exercise(z, w);
        
        assertEquals(z+w, ex.getAnswer());
    }
    
    @Test
    public void exerciseOperationIsCorrect(){
        
        int z = this.randomi.nextInt(100);
        int w = this.randomi.nextInt(100);
        
        Exercise ex = new Exercise(z, w);
        
        assertEquals("+", ex.getOperation());
        
    }
    
    @Test
    public void exerciseOperationOutCorrect(){
        
        int z = this.randomi.nextInt(100);
        int w = this.randomi.nextInt(100);
        
        Exercise ex = new Exercise(z, w);
        
        assertEquals("+", ex.getOperationOut());
        
    }
    
    @Test
    public void exerciseOtherConstructorWorks(){
        
        int z = this.randomi.nextInt(100);
        int w = this.randomi.nextInt(100);
        
        String testi = ""+z+w;
        
        Exercise ex = new Exercise(z, "+", w, testi, 0.0);
        
        assertEquals(z, ex.getX());
        assertEquals(w, ex.getY());
        assertEquals(""+z+w, ex.getAnswerCorrectOut());
        assertEquals("+", ex.getOperationOut());
        assertEquals(0, ex.getTime(), 0);
    }
    
    @Test
    public void exerciseThirdConstructorWorks(){
        
        int z = this.randomi.nextInt(100);
        int w = this.randomi.nextInt(100);
        
        String testi = ""+z+w;
        String date = "2001-9-11 09:07";
        
        Exercise ex = new Exercise(z, "+", w, testi, 0.0, date);
        
        assertEquals(z, ex.getX());
        assertEquals(w, ex.getY());
        assertEquals(""+z+w, ex.getAnswerCorrectOut());
        assertEquals("+", ex.getOperationOut());
        assertEquals(0, ex.getTime(), 0);
        assertEquals(date, ex.getDate());
    }
    
    @Test
    public void toStringWorks(){
        
        int z = this.randomi.nextInt(100);
        int w = this.randomi.nextInt(100);
        
        String testi = ""+z+w;
        
        Exercise ex = new Exercise(z, "+", w, testi, 0.0);
        
        assertEquals("" + z + " + " + w + " " + testi + " 0.0", ex.toString());

    }
    
    @Test
    public void getProblemStringWorks(){
        
        int z = this.randomi.nextInt(100);
        int w = this.randomi.nextInt(100);
        
        String testi = ""+z+w;
        
        Exercise ex = new Exercise(z,w);
        
        assertEquals("" + z + " + " + w + "?", ex.getProblemString());

    }
    
    @Test
    public void getCorrectFiWorksPositive(){
        
        int z = this.randomi.nextInt(100);
        int w = this.randomi.nextInt(100);
        
        String testi = ""+z+w;
        
        Exercise ex = new Exercise(z,w);
        
        ex.setCorrect(true);
        
        assertEquals("oikein", ex.getCorrectFi());
        
        

    }
    
    @Test
    public void getCorrectFiWorksNegative(){
        
        int z = this.randomi.nextInt(100);
        int w = this.randomi.nextInt(100);
        
        String testi = ""+z+w;
        
        Exercise ex = new Exercise(z,w);
        
        ex.setCorrect(false);
        
        assertEquals("väärin", ex.getCorrectFi());
        
    }
    


    
}
