/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ot.arithmetix.engine;

/**
 *
 * @author vlappala
 */
public class Exercise {
    
    private int x;
    private int y;
    
    private int correctAnswer;
    
    public Exercise(int a, int b) {
        
        this.x = a;
        this.y = b;
        this.correctAnswer = a+b;
        
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
}
