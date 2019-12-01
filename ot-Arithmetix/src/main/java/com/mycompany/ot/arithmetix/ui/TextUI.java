/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ot.arithmetix.ui;
import com.mycompany.ot.arithmetix.engine.*;
import java.util.Scanner;
import com.mycompany.ot.arithmetix.dao.*;
import java.sql.SQLException;

/**
 *
 * @author vlappala
 */
public class TextUI {
    
    private Engine gameEngine;
    private Scanner reader;
    private String loggedInUserName;
//    private Dao daoToEngine;

    
    public TextUI(Scanner keyboardReader) {
        
        UserDao taotao = new UserDao();
        this.loggedInUserName = "";
        
        
//        daoToEngine.createTablesIfNotExist();
        this.gameEngine = new Engine(taotao);
        
        
        this.reader = keyboardReader;
        
    }
    
//    public static void errorMessageWhenCreatingUser() {
//        System.out.println("Käyttäjä on jo olemassa, anna uusi!");
//    }
    
    private void loginScreen() {
        
        System.out.println("Anna nimi: ");
        String newName = this.reader.nextLine();
        
        try {
            
            this.gameEngine.loginUser(newName);
        }
        catch (Exception e) {
            System.out.println("SQL-virhe!");
        }    
        
        if (this.gameEngine.hasUser() == false) {
            createUserScreen(newName);
        }
        else {
            System.out.println("Kirjattiin sisään käyttäjä: "+this.gameEngine.getUser().getName());
        }
        

            
    }
    
    private void createUserScreen(String newName) {
        
        
        try {
            this.gameEngine.createUser(newName);
        }
        catch (Exception f) {
            System.out.println("Käyttäjää ei voitu luoda!");
        }
        System.out.println("Luotiin uusi käyttäjä nimellä "+newName+"!");
        
    }
        
    
    
    public void start() {
        
        try {
            this.gameEngine.getUsersFromDatabase();
        }
        catch (SQLException e) {
            System.out.println("Virhe käyttäjälistan lataamisessa!");
        }
        
        while (true) {
            
            if (this.gameEngine.hasUser() == true) {
                System.out.println("Kirjautunut käyttäjä: "+this.gameEngine.getUser().getName());
            }
            System.out.println("Tervetuloa pelaamaan! Valitse seuraavista vaihtoehdoista! ");
            System.out.println("Päästäksesi pelaamaan, tulee sinun olla kirjautunut sisään tai uusi käyttäjä!");
            System.out.println("");
            
            if (this.gameEngine.hasUser() == false) {
                System.out.println("1: Kirjaudu sisään tai luo uusi käyttäjä");
            }
            
            
            System.out.println("2: aloittaa pelaamisen");
            if (this.gameEngine.hasUser() == true) {
                System.out.println("3: Kirjaudu ulos ");
            }
            if (this.gameEngine.hasUser() == true) {
                System.out.println("4: Poista käyttäjä: "+this.gameEngine.getUser().getName());
            }
            System.out.println("0: Lopeta");
            System.out.println("Anna komento: ");
            String command = this.reader.nextLine();
            
            if (command.equals("1")) {
                
                if (this.gameEngine.hasUser() == false) {
                    loginScreen();
                }

                
                
                
//                System.out.println("Anna nimi: ");
//                String newName = this.reader.nextLine();
//                
//                
//                this.gameEngine.createUser(newName);
//                
//                while (this.gameEngine.hasUser() == false) {
//                    System.out.println("Anna nimi uudelle käyttäjälle! Sen pitää olla yli kolme merkkiä pitkä eikä sitä saa olla aiemmin tallennettu järjestelmään!");
//                    newName = this.reader.nextLine();
//                    this.gameEngine.createUser(newName);
//                }
                
//                System.out.println("");
//                System.out.println("Tervetuloa, "+this.gameEngine.getUser().getName());
//                
            } else if (command.equals("2") && this.gameEngine.hasUser() == true) {
                
                System.out.println("Tervetuloa pelaamaan, komento quit lopettaa!");
                
                while (true) {
                    System.out.println("Komento quit lopettaa!");
                    this.gameEngine.newExercise();
                
                    System.out.println("");
                    System.out.println("Tehtävä: "+this.gameEngine.getExercise().getX()+" + "+this.gameEngine.getExercise().getY()+": ");
                    
                    String answer = this.reader.nextLine();
                    
                    while (!answer.equals("quit") && !this.gameEngine.answerInGoodFormat(answer)) {
                        System.out.println("Vastaus on muotoiltu huonosti, yritä uudelleen!");
                        System.out.println("Komento quit lopettaa!");
                        System.out.println("Tehtävä: "+this.gameEngine.getExercise().getX()+" + "+this.gameEngine.getExercise().getY()+": ");
                        answer = this.reader.nextLine();
                    }
                    
                    if (answer.equals("quit")) {
                        break;
                    } else if (Integer.parseInt(answer) == this.gameEngine.getExercise().getAnswer()) {
                        System.out.println("Oikea vastaus, hienoa!");
                    } else {
                        System.out.println("Aijai, väärä vastaus!");
                    }
                }
//
//                
            } else if (command.equals("3") && this.gameEngine.hasUser() == true) {
                this.gameEngine.logoutUser();
            } else if (command.equals("4") && this.gameEngine.hasUser() == true) {
                
                try {
                    
                    this.gameEngine.deleteUser(this.gameEngine.getUser().getName());
                    this.gameEngine.logoutUser();
                    
                }
                catch (Exception e) {
                    System.out.println("Virhe käyttäjän tuhoamisessa: "+e.toString());
                }
            }
            
            else if (command.equals("0")) {
                System.out.println("Heippa!");
                break;
            }
            
            
            
        }
        
    }
    
    
    
//    public static void main(String[] args) {
//        
//        System.out.println("Testi, tuleeko lainausmerkki: \"");
//    }
    
}

