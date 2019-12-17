Linux-komento testiraportin avaamisen selaimessa: chromium-browser target/site/jacoco/index.html

Exclude-komennot skipped_file.xml:ään:

<suppress files="com.mycompany.ot.arithmetix.ui.TextUI.java" checks="[a-zA-Z0-9]*"/>
<suppress files="todoapp.ui.GuiHelper.java" checks="[a-zA-Z0-9]*"/>

maven-suorituskomento: mvn compile exec:java -Dexec.mainClass=com.mycompany.ot.arithmetix.Main


TODO:

Syötetarkistus vastaukseen, 3 miljardia aiheutti Exceptionin, joka tais olla NumberFormatException...

GUI 
DAO, vaiheessa, LOGIN-systeemi kuntoon JA HETI!!

[Markdown-ohje](https://guides.github.com/features/mastering-markdown/)

[JavaFX-ohje](http://tutorials.jenkov.com/javafx/your-first-javafx-application.html)

[H2-Downloads](http://www.h2database.com/html/download.html)

[SQLite-ohje:](https://www.sqlitetutorial.net/sqlite-java/)

        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>1.4.197</version>
        </dependency>

13:15-

"git checkout ." palauttaa paikalliset tiedostot vastaamaan repositorion tiedostoja



Javassa on ilmeisesti luokka 
SimpleDateFormat, josta voi luoda olioita. Olioille voi määritellä patterneja, joiden perusteella SimpleDateFormat-luokka muuttaa stringit päiväyksiksi. Siistiä tässä on se, että nuo patternit voi ilmeisesti määritellä itse melko vapaasti!

Voin siis määritellä SimpleDateFormat-olion patterniksi juuri Sqliten datetime-funktion tuloksen mukaisen stringin komennolla 

SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

Tämän jälkeen voin luoda SimpleDateFormat-oliosta Date-olion komennolla

Date date=formatter.parse("2019-12-13 13:56:31");

http://tutorials.jenkov.com/java-internationalization/simpledateformat.html

SimpleDateFormat:in avulla saan ulos vaikka suomeksi lokalisoitua päivämäärää: "perjantai 13 joulukuuta 2019 17:21:11".

Parempi vaihtoehto: LocalDateTime!

Toteuta käyttäjän poisto-toiminnallisuus! Samalla pitää poistaa kaikki käyttäjään liittyvät harjoitukset(ehkä? ei? kysy käyttäjältä?)

Refaktorointi-ideoita:

Dao-luokka luomaan sekä User-olion että Exercise-olion. Vaatii Dao-määrittelyn uusimista sekä metodien lisäämistä Daoihin. Parametrit, settingsit? Dao ei näe Settings-oliota, pitäiskö se saada parametrinä? Hm, ehkä, ainakin Exercise-olioihin. Myös Exercise-luokan konstruktoreita ehkä päivitettävä.

Viimeisimmän ajan näyttö peliruutuun!

Pelilauta, vaihtelevan kokoinen? Voi konfiguroida settingseissä?

Kissakuva?

Ohjetekstit ControlFX-luokan avulla?
