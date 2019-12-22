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
public class Timer {
    
    private long startTime;
    private long endTime;
    
    public Timer() {
        
    }
    
    void setStartTime() {
        this.startTime = System.currentTimeMillis();
    }
    
    public void setEndTime() {
        this.endTime = System.currentTimeMillis();
    }
    
    long getDifferenceInSeconds() {
        return (this.endTime - this.startTime);
    }
    void clearTimer() {
        this.startTime = 0;
        this.endTime = 0;
    }
}
