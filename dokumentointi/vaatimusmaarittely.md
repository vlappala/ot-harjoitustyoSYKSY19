# Vaatimusmäärittely

## Sovelluksen tarkoitus
Sovelluksen avulla käyttäjä voi harjoitella laskutaitojaan. 
Käyttäjä voi valita, minkälaisia laskutehtäviä sovellus tarjoaa.
Sovelluksella on mahdollista olla useita käyttäjiä, ja sovellus tallentaa käyttäjän sekä käyttäjän harjoitteluhistorian tietokantaan.

## Käyttäjät
Alkuvaiheessa sovelluksella on ainoastaan yksi käyttäjärooli eli normaali käyttäjä. Myöhemmin sovellukseen saatetaan lisätä suuremmilla oikeuksilla varustettu pääkäyttäjä.

## Perusversion tarjoama toiminnallisuus

*  EI TEHTY VIELÄ: Graafinen käyttöliittymä

### Ennen kirjautumista


* TEHTY Käyttäjä voi luoda järjestelmään käyttäjätunnuksen
  * Käyttäjätunnuksen pituuden pitää olla vähintään 3 merkkiä ja uniikki
  * TEHTY Käyttäjä voi kirjautua järjestelmään 
    * TEHTY Sovellus käyttää sqlite-tietokantaa käyttäjien hallintaan
      * TEHTY Kirjautuminen onnistuu syötettäessä olemassaoleva käyttäjätunnus kirjautumislomakkeelle
      * TEHTY Sovellus ilmoittaa, mikäli käyttäjää ei ole olemassa tietokannassa
      * TEHTY Mikäli käyttäjää ei ole olemassa, luodaan uusi käyttäjä ja tallennetaan se tietokantaan
    * TEHTY Sovellus käyttää tietokantaa myös tehtyjen harjoitusten hallintaan

* Kirjautumisen jälkeen
  * Käyttäjä voi valita, minkälaisia laskutehtäviä haluaa suorittaa
  * TEHTY Käyttäjä voi suorittaa laskutehtäviä
  * TEHTY Käyttäjä voi kirjautua ulos sovelluksesta
  * TEHTY Käyttäjä voi poistaa käyttäjätiedot tietokannasta
  * TEHTY Käyttäjä voi listata kaikki käytössä olevat käyttäjätunnukest
    * Tämä ominaisuus täytyy ehkä viimeisestä versiosta poistaa
  * TEHTY Sovellus ottaa aikaa suorituksesta
  * TEHTY Suoritukset viedään tietokantaan
  * TEHTY Käyttäjä voi listata omat suorituksensa

## Jatkokehitysideoita

* OSAKSI TEHTY 4.12., KÄYTTÄJÄ VOI LISTATA OMAT SUORITUKSENSA Käyttäjäkohtaisten tilastojen tarkastelu
* Mahdollisuus valita useammantyyppisiä laskutehtäviä suoritettavaksi
  * Alussa yhteen- ja vähennyslaskut, myöhemmin ehkä kerto- ja jakolasku, potenssit, murtoluvut?
  * Laskutoimitusten visualisointi esimerkiksi näyttämällä käyttäjälle erilaisia geometrisiä kuvioita


