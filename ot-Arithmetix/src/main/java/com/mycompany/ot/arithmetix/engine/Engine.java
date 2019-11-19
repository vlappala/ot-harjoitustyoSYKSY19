/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ot.arithmetix.engine;

import java.util.Random;

/**
 *
 * @author vlappala
 */
public class Engine {
    
    private User loggedInGamer;
    private Random random = new Random();
    private Exercise exercise;
    
    public Engine() {
        
    }
    
    public void setUser(String name) {
        User createdUser = new User(name);
        this.loggedInGamer = createdUser;
    }
    
    public boolean hasUser() {
        
        if (this.loggedInGamer == null) {
            return false;
        }
        return true;
    }
    
    public User getUser() {
                
        return this.loggedInGamer;
             
    }
    
    public void newExercise() {
        
        int a = random.nextInt(10)+1;
        int b = random.nextInt(10)+1;
        
        this.exercise = new Exercise(a, b);
        
    }
    
    public Exercise getExercise() {
        return this.exercise;
    }
    
    public boolean answerInGoodFormat(String text) {
        
        
        for (int i = 0;i < text.length();i++) {
            if ((int)text.charAt(i) < 48 || (int)text.charAt(i) > 57) {
                return false;
            }
        }
        return true;
    }
    
}
