# Käyttöoohje

## Konfigurointi

Ohjelma olettaa, että sen käynnistyshakemistossa on konfiguraatiotiedostot sekä ohjelman varsinaisia konfiguraatioita varten, että ohjelman testejä varten. Tiedostojen sisältö on rakenteeltaan sama. Varsinaisen sovelluksen konfiguraatiotiedoston nimi on config.properties ja testien konfiguraatiotiedoston nimi on TESTconfig.properties. Konfiguraatiotiedostoissa määritellään käytettävän tietokannan nimi.
Sovelluksen käyttämä tiedosto on sisällöltään seuraava:

    dbFile=testidata.db
    
Tietokannan nimen voi antaa konfiguraatiotiedostossa. Mikäli sennimistä tietokantaa ei löydy projektin juurihakemistosta, tietokanta luodaan ohjelman käynnistyessä.

## Ohjelman käynistäminen

Ohjelma käynnistetään komennolla

    java -jar ot-Arithmetix-1.0-SNAPSHOT.jar
    
## Kirjautuminen

Ohjelma käynnistyy kirjautumisnäkymään:

![Alkuruutu](https://raw.githubusercontent.com/vlappala/ot-harjoitustyoSYKSY19/master/dokumentointi/kuvat/Arithmetix_alkuruutu.jpg)

Kirjautumisnäkymän jälkeen ohjelman tässä versiossa on tervetuloruutu, josta voi siirtyä peliruutuun. 

## Pelaaminen

Peliruudussa voi harjoitella matematiikkaa:

![Peliruutu](https://raw.githubusercontent.com/vlappala/ot-harjoitustyoSYKSY19/master/dokumentointi/kuvat/Arithmetix_peliruutu.jpg)
