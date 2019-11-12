package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {

    Maksukortti kortti;
    Kassapaate kassa;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
        kassa = new Kassapaate();
    }

    @Test
    public void kassapaatteenAlkuarvotOikein() {
        assertEquals(100000, kassa.kassassaRahaa()); 
        assertEquals(0, kassa.edullisiaLounaitaMyyty()+kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoToimiiEdullisetRahaRiittaaVaihtorahaOikein() {
        
        int n = kassa.syoEdullisesti(300);
        assertEquals(60, n); 
        
    }
    
    @Test
    public void kateisostoToimiiMaukkaatRahaRiittaaVaihtorahaOikein() {
        
        int n = kassa.syoMaukkaasti(410);
        assertEquals(10, n); 
        
    }
    
    @Test
    public void kateisostoToimiiEdullisetMyytyjenMaaraKasvaa() {
        
        kassa.syoEdullisesti(240);
        assertEquals(1, kassa.edullisiaLounaitaMyyty()); 
        
    }
    
    @Test
    public void kateisostoToimiiMaukkaatMyytyjenMaaraKasvaa() {
        
        kassa.syoMaukkaasti(400);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty()); 
        
    }
    
    @Test
    public void kateisostoToimiiEdullisetRahaEiRiita() {
        
        int n = kassa.syoEdullisesti(200);
        assertEquals(0, kassa.edullisiaLounaitaMyyty()); 
        assertEquals(100000, kassa.kassassaRahaa()); 
        assertEquals(200, n); 
               
    }
    
    @Test
    public void kateisostoToimiiMaukkaatRahaEiRiita() {
        
        int n = kassa.syoMaukkaasti(200);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty()); 
        assertEquals(100000, kassa.kassassaRahaa()); 
        assertEquals(200, n); 
               
    }
    
    @Test
    public void korttiostoToimiiEdullisetRahaRiittaa() {
        
        boolean b = kassa.syoEdullisesti(kortti);
        assertEquals(true, b); 
        
        assertEquals(760, kortti.saldo()); 
        
    }
    
    @Test
    public void korttiostoToimiiMaukkaatRahaRiittaa() {
        
        boolean b = kassa.syoMaukkaasti(kortti);
        assertEquals(true, b); 
        
        assertEquals(600, kortti.saldo()); 
        
    }
    
    @Test
    public void korttiostoEdullisetMyytyjenMaaraKasvaa() {
        
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty()); 
        
    }
    
    @Test
    public void korttiostoMaukkaatMyytyjenMaaraKasvaa() {
        
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty()); 
        
    }
    
    @Test
    public void korttiostoEdullisetRahaEiRiita() {
        
        kortti.otaRahaa(990);
        boolean b = kassa.syoEdullisesti(kortti);
        assertEquals(10, kortti.saldo()); 
        
        assertEquals(0, kassa.edullisiaLounaitaMyyty()); 
        
        assertEquals(false, b);
        
    }
    
    @Test
    public void korttiostoMaukkaatRahaEiRiita() {
        
        kortti.otaRahaa(990);
        boolean b = kassa.syoMaukkaasti(kortti);
        assertEquals(10, kortti.saldo()); 
        
        assertEquals(0, kassa.maukkaitaLounaitaMyyty()); 
        
        assertEquals(false, b);
        
    }
    
    @Test
    public void korttiostoEdullisetKassanRahamaaraEiMuutu() {
        
        kassa.syoEdullisesti(kortti);
        
        assertEquals(100000, kassa.kassassaRahaa()); 
        
        
        
    }
    
    @Test
    public void korttiostoMaukkaatKassanRahamaaraEiMuutu() {
        
        kassa.syoMaukkaasti(kortti);
        
        assertEquals(100000, kassa.kassassaRahaa()); 
        
        
        
    }
    
    @Test
    public void kortinLatausToimii() {
        
        kassa.lataaRahaaKortille(kortti, 10);
        
        assertEquals(100010, kassa.kassassaRahaa()); 
        assertEquals(1010, kortti.saldo());
        
        
        
    }
    
    @Test
    public void kortinNegatiivinenLatausEiToimi() {
        
        kassa.lataaRahaaKortille(kortti, -10);
        
        assertEquals(100000, kassa.kassassaRahaa()); 
        assertEquals(1000, kortti.saldo());
        
        
        
    }
    
    
//    jos kortilla ei ole tarpeeksi rahaa, kortin rahamäärä ei muutu, myytyjen lounaiden määrä muuttumaton ja palautetaan false
    
    
}
