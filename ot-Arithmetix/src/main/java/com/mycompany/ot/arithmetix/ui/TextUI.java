/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ot.arithmetix.ui;
import com.mycompany.ot.arithmetix.engine.Engine;
import java.util.Scanner;

/**
 *
 * @author vlappala
 */
public class TextUI {
    
    private Engine gameEngine;
    private Scanner reader;
    
    public TextUI(Engine engine, Scanner keyboardReader) {
        
        this.gameEngine = engine;
        this.reader = keyboardReader;
        
    }
    
    public void start() {
        
        while (true) {
            
            
            System.out.println("Numero 1 luo uuden käyttäjän");
            System.out.println("Numero 2 aloittaa pelaamisen");
            System.out.println("Numero 0 lopettaa");
            System.out.println("Anna komento: ");
            String command = this.reader.nextLine();
            
            if (command.equals("1")) {
                
                System.out.println("Anna nimi: ");
                String newName = this.reader.nextLine();
                
                this.gameEngine.setUser(newName);
                
                System.out.println("");
                System.out.println("Tervetuloa, "+this.gameEngine.getUser().getName());
                
            } else if (command.equals("2")) {
                
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

                
            } else if (command.equals("0")) {
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
