/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ot.arithmetix;
import com.mycompany.ot.arithmetix.engine.Engine;
import com.mycompany.ot.arithmetix.ui.*;
import java.util.Scanner;

/**
 *
 * @author t430
 */
public class Main {
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        
        /** Tämä on ohjelman luokka Main, joka käynnistää varsinaisen käyttöliittymäluokan.
         * 
         * 
         *
         * 
         * 
         * 
         */
        
        
        
        // mikäli poistat vahingossa tietokannan tai teet siihen muutoksia,
        // voit ajaa tämän metodin jolloin tietokantataulu luodaan uudestaan
        // ja siihen lisätään nimiä

        // palvelu http://listofrandomnames.com on tällaisissa varsin näppärä
//        String url = "jdbc:sqlite:./testi2.db";
//        
//        // SQL statement for creating a new table
//        String sql = "CREATE TABLE IF NOT EXISTS warehouses (\n"
//                + "    id integer PRIMARY KEY,\n"
//                + "    name text NOT NULL,\n"
//                + "    capacity real\n"
//                + ");";
//        
//        try (Connection conn = DriverManager.getConnection(url);
//                Statement stmt = conn.createStatement()) {
//            // create a new table
//            stmt.execute(sql);
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
        
        
//        Engine game = new Engine();
//        Scanner reader = new Scanner(System.in);
//        
//        TextUI ui = new TextUI(reader);
//        
//        ui.start();
        // LoginPage.main(args);
        
        Ui.main(args);
    }
    
}
