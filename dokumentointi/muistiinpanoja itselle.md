Linux-komento testiraportin avaamisen selaimessa: chromium-browser target/site/jacoco/index.html

Exclude-komennot skipped_file.xml:ään:

<suppress files="com.mycompany.ot.arithmetix.ui.TextUI.java" checks="[a-zA-Z0-9]*"/>
<suppress files="todoapp.ui.GuiHelper.java" checks="[a-zA-Z0-9]*"/>

maven-suorituskomento: mvn compile exec:java -Dexec.mainClass=com.mycompany.ot.arithmetix.Main
