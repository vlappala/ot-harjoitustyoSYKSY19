## Pakkausarkkitehtuuri

Arithmetix-sovelluksessa käytetään kolmitasoista pakkausarkkitehtuuria. Käyttöliittymästä huolehtiva ui-pakkaus on riippuvainen engine-pakkauksesta, sillä se pyytää erilaista tietoa sovelluksen logiikkakerrokselta, engine-pakkaukselta, näytettäväksi käyttäjälle. Dao-pakkauksessa on kaksi Data Access Object-toteutusta, UserDao vastaa käyttäjätietojen tallennuksesta tietokantaan ja ExerciseDao vastaa harjoitusten tallentamisesta tietokantaan.

![Projektin pakkauskaavio](https://github.com/vlappala/ot-harjoitustyoSYKSY19/blob/master/dokumentointi/kuvat/Arithmetix_pakkausrakenne.jpg)

## Käyttöliittymä

Sovelluksen käyttöliittymässä on neljä erilaista näkymää. Näkymät on toteutettu Scene-olioina, joita välitetään kokonaisnäkymästä huolehtivalle Stage-oliolle tarpeen mukaan.

Erilaiset näkymät ovat

*  Kirjautumisruutu
*  Valikkoruutu
*  Peliruutu
*  Tilastoruutu

Käyttöliittymä saa tarpeelliset näytettävät tiedot Engine-oliolta kutsumalla Engine-luokan metodeja. Käyttöliittymä on pyritty eristämään ohjelman muusta rakenteesta hyvän käytännön mukaisesti.

## Sovelluslogiikka

![datamalli](https://github.com/vlappala/ot-harjoitustyoSYKSY19/blob/master/dokumentointi/kuvat/Arithmetix_datamalli.jpg)

Arithmetixin perustana oleva datamalli on yksinkertainen. Käyttäjiin voi liittyä useita harjoituksia, jotka tallennetaan tietokantaan.

Sovelluksen keskeinen osa on sen Engine-luokka. Engine-luokasta luodaan yksi olio, jolle hyvän käytännön mukaisesti injektoidaan konstruktorin parametrina riippuvuudet UserDao- ja ExerciseDao-olioihin. 

## Tietojen pysyväistallennus

Sovellus tallentaa käyttäjätiedot ja suoritetut harjoitukset tietokantaan. Sovelluksessa on toteutettu dataa käsittelevät osat DAO-suunnittelumallin mukaisesti, jolloin uudet toteutukset on helppo päivittää vanhojen tilalle, jos tarvetta ilmenee.

Sovelluksen käyttämän tietokantatiedoston nimi määritellään konfiguraatiotiedostossa config.properties ja sen sisältö on seuraavanlainen

    dbFile=testidata.db
    
Tietokannan skeema on seuraavanlainen

    CREATE TABLE Users (
    name varchar(255) PRIMARY KEY NOT NULL UNIQUE);
    
    CREATE TABLE Exercises (
    id integer primary key,
    name varchar(255),
    firstterm integer,
    operation varchar(1),
    secondterm integer,
    correct varchar(1),
    time real,
    date date);
    
## Toiminta

Sekvenssikaavio uuden käyttäjän luomisesta:

![Sekvenssikaavio, uuden käyttäjän luominen](https://github.com/vlappala/ot-harjoitustyoSYKSY19/blob/master/dokumentointi/kuvat/Arithmetix%2C%20uuden%20k%C3%A4ytt%C3%A4j%C3%A4n%20luominen.png)

## Järjestelmän heikkoudet

Valittu tietokantaratkaisu sqlite ei välttämättä ole paras mahdollinen. Mahdollisissa tulevissa versioissa asiaan olisi syytä kiinnittää huomiota, samoin kuin tietokannan rakenteeseen. Jotkut sovelluksen jatkokehitysideoista vaativat useita uusia tietokantatauluja, ja sen yhteydessä tietokannan parempi suunnittelu olisi suotavaa.

![Luokka_pakkauskaavio](https://github.com/vlappala/ot-harjoitustyoSYKSY19/blob/master/dokumentointi/kuvat/Arithmetix_luokka_pakkauskaavio.jpg)


