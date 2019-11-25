# Arithmetix

Sovelluksen avulla on mahdollista harjoitella aritmetiikkaa

## Dokumentaatio

[Tuntikirjanpito](https://github.com/vlappala/ot-harjoitustyoSYKSY19/blob/master/dokumentointi/tuntikirjanpito.md)

[Vaatimusmäärittelydokumentti](https://github.com/vlappala/ot-harjoitustyoSYKSY19/blob/master/dokumentointi/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/vlappala/ot-harjoitustyoSYKSY19/blob/master/dokumentointi/arkkitehtuuri.md)

## Komentorivitoiminnot



Maven-suorituskomento: 

    mvn compile exec:java -Dexec.mainClass=com.mycompany.ot.arithmetix.Main

Jacoco-testikattavuusraportti:

    mvn test jacoco:report
    
Checkstyle-tarkastus:

    mvn jxr:jxr checkstyle:checkstyle
