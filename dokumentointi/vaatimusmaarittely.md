# Vaatimusmäärittely

## Sovelluksen tarkoitus
Sovelluksen avulla käyttäjä voi harjoitella laskutaitojaan. 
Käyttäjä voi valita, minkälaisia laskutehtäviä sovellus tarjoaa.
Sovelluksella on mahdollista olla useita käyttäjiä, ja sovellus tallentaa käyttäjän sekä käyttäjän harjoitteluhistorian tietokantaan.

## Käyttäjät
Alkuvaiheessa sovelluksella on ainoastaan yksi käyttäjärooli eli normaali käyttäjä. Myöhemmin sovellukseen saatetaan lisätä suuremmilla oikeuksilla varustettu pääkäyttäjä.

## Perusversion tarjoama toiminnallisuus

### Ennen kirjautumista


* Käyttäjä voi luoda järjestelmään käyttäjätunnuksen
  * Käyttäjätunnuksen pituuden pitää olla vähintään 3 merkkiä ja uniikki
  * TEHTY Käyttäjä voi kirjautua järjestelmään 
    * Kirjautuminen onnistuu syötettäessä olemassaoleva käyttäjätunnus kirjautumislomakkeelle
    * Sovellus ilmoittaa, mikäli käyttäjää ei ole olemassa

* Kirjautumisen jälkeen
  * Käyttäjä voi valita, minkälaisia laskutehtäviä haluaa suorittaa
  * TEHTY Käyttäjä voi suorittaa laskutehtäviä
  * TEHTY Käyttäjä voi kirjautua ulos sovelluksesta

## Jatkokehitysideoita

* Käyttäjäkohtaisten tilastojen tarkastelu
* Mahdollisuus valita useammantyyppisiä laskutehtäviä suoritettavaksi
  * Alussa yhteen- ja vähennyslaskut, myöhemmin ehkä kerto- ja jakolasku, potenssit, murtoluvut?
  * Laskutoimitusten visualisointi esimerkiksi näyttämällä käyttäjälle erilaisia geometrisiä kuvioita


