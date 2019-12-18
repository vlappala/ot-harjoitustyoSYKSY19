
package com.mycompany.ot.arithmetix.engine;

import com.mycompany.ot.arithmetix.dao.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    private ExerciseDao exerciseDao;
    
    private ArrayList<User> users;
    
    private Timer timer;
    
    private double lastClockedTime;
    
    public Engine(UserDao createdUserDao, ExerciseDao createdExerciseDao) {
        
        this.users = new ArrayList<>();
        
        this.userDao = createdUserDao;
        this.userDao.createTablesIfNotExist();
        
        this.exerciseDao = createdExerciseDao;
        this.exerciseDao.createTablesIfNotExist();
        
        this.timer = new Timer();
        
    }

    
    public void loginUser(String name) throws SQLException {
              
        User u = this.userDao.read(name);

        if (u != null) {
            this.loggedInGamer = u;
        }

    }
    
    public void createUser(String name) throws SQLException {
        

        this.loggedInGamer = this.userDao.create(name);        
    }
    
    public void deleteUser(String name) throws SQLException {
        
        this.userDao.delete(name);
        
    }
    
    public void getUsersFromDatabase() throws SQLException {
        
        
        this.users.clear();
        List<User> temp = this.userDao.list();
        
        for (User u : temp) {
            this.users.add(u);
        } 
      
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
    
    public void logoutUser() {
        this.loggedInGamer = null;
    }
    
    public ArrayList<User> getUserList() {
        return this.users;
    }
    
    public void newExercise() {
        
        int a = random.nextInt(10) + 1;
        int b = random.nextInt(10) + 1;
        
        this.exercise = new Exercise(a, b);
        
        this.timer.clearTimer();
        this.timer.setStartTime();
        
    }
    
    public Exercise getExercise() {
        return this.exercise;
    }
    
    public void clearExercise() {
        this.exercise = null;
    }
    
    public boolean processAnswer(String answer) {
        
        this.timer.setEndTime();
        
        boolean correct = false;
        
        if (Integer.parseInt(answer) == this.getExercise().getAnswer()) {
            correct = true;
        }
               
        double d = this.timer.getDifferenceInSeconds();
        
        System.out.println(d/1000);
        this.lastClockedTime = d/1000;
        
        this.timer.clearTimer();
        
        if (this.exercise != null) {
            this.exercise.setCorrect(correct);
            this.exercise.setTime(d/1000);
            
            try {
                this.getExerciseDao().create(this.getExercise(), this.getUser());
            }
            catch (Exception e) {
                System.out.println("Virhe harjoituksen viemisessä tietokantaan: "+e.toString());
            }
        }
        newExercise();
        return correct;
    }
    public String getQuestion() {
        
        String question = "";
        question += this.exercise.getProblemString();
        return question;
    }
    
    public UserDao getUserDao() {
        return this.userDao;
    }
    
    public ExerciseDao getExerciseDao() {
        return this.exerciseDao;
    }
    public Timer getTimer() {
        return this.timer;
    }
    public double getLastClockedTime() {
        return this.lastClockedTime;
    }
    
    public ArrayList<Exercise> getExerciseList() {
        
        ArrayList result = new ArrayList<>();
        
        try {
            result = this.exerciseDao.list(this.getUser().getName());
        } catch (Exception e) {
            System.out.println("Virhe harjoitusten hakemisessa tietokannasta!");
        }
        
        return result;
    }
    
    public boolean answerInGoodFormat(String text) {
        
        if (text.length() > 6 || text.length() == 0) {
            return false;
        }
        
        if ((int)text.charAt(0) == 45) {
            
            if (text.length() > 1) {
                
                for (int i = 1; i < text.length(); i++) {
                    if ((int) text.charAt(i) < 48 || (int) text.charAt(i) > 57) {
                        return false;
                    }
                }   
                return true;
            }
            return false;
        }
        
        
        for (int i = 0; i < text.length(); i++) {
            if ((int) text.charAt(i) < 48 || (int) text.charAt(i) > 57) {
                return false;
            }
        }
        return true;
    }
    
}
