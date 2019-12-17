
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

}