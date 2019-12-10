/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ot.arithmetix.engine;

        /** Tämä on User-luokka, josta luodaan ilmentymiä sovelluksen käyttäjien erottelemiseksi toisistaan.
         *  Erottelu tapahtuu nimien perusteella.
         * 
         *
         * 
         * 
         * 
         */
public class User {
    
    private String username;
    
    public User(String name) {
        this.username = name;
    }
    
    public String getName() {
        return this.username;
    }
    
}
