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

![Alkuruutu](https://raw.githubusercontent.com/vlappala/ot-harjoitustyoSYKSY19/master/dokumentointi/kuvat/Arithmetix_alkuruutu1.jpg)

Kirjautumisnäkymässä on kaksi mahdollisuutta kirjautua sisään. Mikäli käyttäjä on rekisteröitynyt jo aiemmin järjestelmään, voi hän kirjautua sisään syöttämällä aiemmin valitun käyttäjänimen. Käyttäjän on mahdollista valita myös uusi käyttäjänimi syöttämällä se tekstikenttään. Järjestelmä ei kysy varmistuskysymystä käyttäjältä kummassakaan tapauksessa, kirjautuminen onnistuu vain yhtä nappia painamalla.

Kirjautumisnappi on määritelty oletusnapiksi, jolloin kirjautuminen onnistuu paitsi klikkaamalla nappia hiirellä, niin myös enter-napilla.

Mikäli käyttäjä syöttää virheellisen, liian pitkän tai liian lyhyen käyttäjänimen, antaa järjestelmä virheilmoituksen:

![Alkuruutu_virheilmoitus](https://raw.githubusercontent.com/vlappala/ot-harjoitustyoSYKSY19/master/dokumentointi/kuvat/Arithmetix_virheilmoitus_kayttajanimi.jpg)

## Kirjautumisen jälkeen

Kun kirjautuminen on onnistunut, on seuraavana vuorossa pelin päävalikkoruutu:

![Valikkoruutu](https://raw.githubusercontent.com/vlappala/ot-harjoitustyoSYKSY19/master/dokumentointi/kuvat/Arithmetix_valikkoruutu.jpg)

Valikkoruudusta voi siirtyä peliruutuun Aloita pelaaminen-napin painalluksen lisäksi enter-painikkeella, esc-painike tai Kirjaudu-ulos napin painaminen kirjaa käyttäjän ulos ilman varoitusta.

## Pelaaminen

Peliruudussa voi harjoitella matematiikkaa:

![Peliruutu](https://raw.githubusercontent.com/vlappala/ot-harjoitustyoSYKSY19/master/dokumentointi/kuvat/Arithmetix_peliruutu.jpg)

## Tilastotietoja harjoittelusta

Pelaaja voi tarkastella tilastotietoja harjoittelustaan valitsemalla valikkoruudussa Katso tilastosi-painikkeen. Tilastoruutu näyttää seuraavalta:





