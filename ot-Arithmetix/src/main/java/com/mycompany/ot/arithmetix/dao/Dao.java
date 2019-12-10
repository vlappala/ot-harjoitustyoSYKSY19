/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ot.arithmetix.dao;
import java.sql.*;
import java.util.*;

        /** Tämä on rajapintaluokka. Luokkaa käytetään tietokantayhteyden muodostavien luokkien toteutuksen määrittelyyn
         * 
         * 
         *
         * 
         * 
         * 
         */

public interface Dao<T, K> {
    void create(T object) throws SQLException;
    T read(K key) throws SQLException;
    T update(T object) throws SQLException;
    void delete(K key) throws SQLException;
    List<T> list() throws SQLException;
//     void createTablesIfNotExist();
}