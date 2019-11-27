/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ot.arithmetix.engine;

import com.mycompany.ot.arithmetix.dao.*;
import java.sql.SQLException;
import java.util.Random;

/**
 *
 * @author vlappala
 */
public class Engine {
    
    private User loggedInGamer;
    private Random random = new Random();
    private Exercise exercise;
    private UserDao userDao;
    
    public Engine(UserDao createdUserDao) {
        
        this.userDao = createdUserDao;
        this.userDao.createTablesIfNotExist();
        
        
        
    }
    

    
//    public void setUserDao(Dao dao) {
//        this.userDao = dao;
//    }
    
    public void loginUser(String name) throws SQLException{
        
               
//        if (this.userDao.read(name) != null) {

            this.loggedInGamer = this.userDao.read(name);
//            return;
//        }
        
    }
    
    public void createUser(String name) throws SQLException{
        
        
        User createdUser = new User(name);

        this.userDao.create(createdUser);
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
        
        int a = random.nextInt(10) + 1;
        int b = random.nextInt(10) + 1;
        
        this.exercise = new Exercise(a, b);
        
    }
    
    public Exercise getExercise() {
        return this.exercise;
    }
    
    public Dao getUserDao() {
        return this.userDao;
    }
    
    public boolean answerInGoodFormat(String text) {
        
        
        for (int i = 0; i < text.length(); i++) {
            if ((int) text.charAt(i) < 48 || (int) text.charAt(i) > 57) {
                return false;
            }
        }
        return true;
    }
    
}
