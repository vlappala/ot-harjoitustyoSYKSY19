/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ot.arithmetix;
import com.mycompany.ot.arithmetix.ui.*;
import com.mycompany.ot.arithmetix.engine.Engine;
import java.util.Scanner;

/**
 *
 * @author t430
 */
public class Main {
    
    
    
    public static void main(String[] args) {
        
        Engine game = new Engine();
        Scanner reader = new Scanner(System.in);
        
        TextUI UI = new TextUI(game, reader);
        
        UI.start();
        // LoginPage.main(args);
    }
    
}
