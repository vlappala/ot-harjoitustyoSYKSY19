# Arithmetix

Sovelluksen avulla on mahdollista harjoitella aritmetiikkaa

## Releaset

[Viikon 5 Release](https://github.com/vlappala/ot-harjoitustyoSYKSY19/releases/tag/viikko5)

## Dokumentaatio

[Käyttöohje](https://github.com/vlappala/ot-harjoitustyoSYKSY19/blob/master/dokumentointi/kayttoohje.md)

[Tuntikirjanpito](https://github.com/vlappala/ot-harjoitustyoSYKSY19/blob/master/dokumentointi/tuntikirjanpito.md)

[Vaatimusmäärittelydokumentti](https://github.com/vlappala/ot-harjoitustyoSYKSY19/blob/master/dokumentointi/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/vlappala/ot-harjoitustyoSYKSY19/blob/master/dokumentointi/arkkitehtuuri.md)

## Komentorivitoiminnot



Maven-suorituskomento: 

    mvn compile exec:java -Dexec.mainClass=com.mycompany.ot.arithmetix.Main
    
Maven-testauskomento:

    mvn test

Jacoco-testikattavuusraportti:

    mvn test jacoco:report
    
Checkstyle-tarkastus:

    mvn jxr:jxr checkstyle:checkstyle

Suoritettavan jarin generointi

    mvn package
