# Testausdokumentti

## Yksikkötestaus

Sovellusta on testattu JUnit-yksikkötesteillä. Testauksen rivikattavuus on 22.12.2019 klo 21 70 prosenttia ja haarautumakattavuus 76 prosenttia. Lukemat ovat hyviä.

![Testikattavuus](https://github.com/vlappala/ot-harjoitustyoSYKSY19/blob/master/dokumentointi/kuvat/Arithmetix_testikattavuus.jpg)

Testausta on vielä mahdollista kehittää ja täten saavuttaa suuremmat rivi- ja haarautumakattavuudet.

## Integraatiotestaus

![Integraatiotestaus](https://github.com/vlappala/ot-harjoitustyoSYKSY19/blob/master/dokumentointi/kuvat/Arithmetix_process_answer.jpg)

Esimerkiksi Engine-luokan metodin processAnswer testit testaavat samalla metodin ja ExerciseDao-luokan toimimista yhdessä.

## Järjestelmätestaus

Sovellusta on koekäytetty runsaasti ja kokeiltu esimerkiksi erilaisilla testisyötteillä syötekenttien ja muuttujien toimivuutta. Sovellukselle on rakennettu kaksi käyttöliittymää, tekstikäyttöliittymä ja graafinen käyttöliittymä. Joidenkin ominaisuuksien testaaminen onnistui helpommin tekstikäyttöliittymässä. Eräsa tällainen ominaisuus on kaikkien käyttäjien listaaminen. Tämä ominaisuus jäi pois sovelluksen lopullisesta, graafisen käyttöliittymän kera toimivasta versiosta.
