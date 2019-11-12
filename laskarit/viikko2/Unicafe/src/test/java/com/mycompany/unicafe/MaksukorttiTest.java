package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortilleVoiLadataRahaa() {
        kortti.lataaRahaa(2500);
        assertEquals("saldo: 35.0", kortti.toString());
    }
    @Test
    public void saldoVaheneeOikein() {
        
        kortti.otaRahaa(800);
        assertEquals("saldo: 2.0", kortti.toString());
    }
        
    @Test
    public void saldoEiMuutuJosKortillaEiTarpeeksiRahaa() {
        
        kortti.otaRahaa(800);
        assertEquals("saldo: 2.0", kortti.toString());
        kortti.otaRahaa(800);
        assertEquals("saldo: 2.0", kortti.toString());
    }
    
    @Test
    public void otaRahaaPalauttaaTotuusarvonOikein() {
        
        boolean b = kortti.otaRahaa(800);
        assertEquals("saldo: 2.0", kortti.toString());
        assertTrue(b);
        b = kortti.otaRahaa(800);
        assertTrue(!b);
    }
    @Test
    public void saldoPalauttaaOikeanSaldon() {
        
        int n = kortti.saldo();
        assertEquals(1000, n);
        
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
}
