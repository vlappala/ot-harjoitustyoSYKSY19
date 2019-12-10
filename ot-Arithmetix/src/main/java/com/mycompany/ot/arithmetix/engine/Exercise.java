/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ot.arithmetix.engine;

        /** Tämä on Exercise-luokka. Luokasta luodaan harjoituksia, joilla ohjelman käyttäjä
         *  voi harjoitella matemaattista osaamistaan
         * 
         *
         * 
         * 
         * 
         */
public class Exercise {
    
    private int x;
    private int y;
    
    private int correctAnswer;
    private char operation;
    
    private double time;
    
    private boolean answerCorrect;
    private String answerCorrectOut;
    private String operationOut;
    
    public Exercise(int a, int b) {
        
        this.x = a;
        this.y = b;
        this.correctAnswer = a + b;
        this.operation = '+';
        
    }
    
    public Exercise(int a, String operation, int b, String correct, double time) {
        
        this.x = a;
        this.operationOut = operation;
        this.y = b;
        this.answerCorrectOut = correct;
        this.time = time;
        
    }
    
    public int getAnswer() {
        return this.correctAnswer;
    }
    
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public String getOperation() {
        return "" + this.operation;
    }
    public void setTime(double time) {
        this.time = time;
    }
    public double getTime() {
        return this.time;
    }
    
    public void setCorrect(boolean b) {
        this.answerCorrect = b;
    }
    public String getCorrect() {
        return "" + this.answerCorrect;
    }
    public String getOperationOut() {
        return this.operationOut;
    }
    public String getAnswerCorrectOut() {
        return this.answerCorrectOut;
    }
    public String toString() {
        return "" + this.x + " " + this.getOperationOut() + " " + this.getY() + " " + this.getAnswerCorrectOut() + " " + this.getTime();
    }
}
