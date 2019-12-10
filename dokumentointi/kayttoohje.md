# Käyttöoohje

## Konfigurointi

Ohjelma olettaa, että sen käynnistyshakemistossa on konfiguraatiotiedosto config.properties, joka määrittelee käytettävän tietokannan nimen.
Tiedosto on muotoa:

    dbFile=testidata.db
    
Tietokannan nimen voi antaa konfiguraatiotiedostossa. Mikäli sennimistä tietokantaa ei löydy projektin juurihakemistosta, tietokanta luodaan ohjelman käynnistyessä.

## Ohjelman käynistäminen

Ohjelma käynnistetään komennolla

    java -jar ot-Arithmetix-1.0-SNAPSHOT.jar
    
## Kirjautuminen

Ohjelma käynnistyy kirjautumisnäkymään:

![Alkuruutu](https://raw.githubusercontent.com/vlappala/ot-harjoitustyoSYKSY19/master/dokumentointi/kuvat/Arithmetix_alkuruutu.jpg)
