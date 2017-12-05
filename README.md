# ohtu_miniprojekti
[![Build Status](https://travis-ci.org/micaminoff/ohtu_miniprojekti.svg?branch=master)](https://travis-ci.org/micaminoff/ohtu_miniprojekti)
[![Codacy Badge](https://api.codacy.com/project/badge/Coverage/d11375bdbe984bdbbad74d83e588c210)](https://www.codacy.com/app/micaminoff/ohtu_miniprojekti?utm_source=github.com&utm_medium=referral&utm_content=micaminoff/ohtu_miniprojekti&utm_campaign=Badge_Coverage)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/d11375bdbe984bdbbad74d83e588c210)](https://www.codacy.com/app/micaminoff/ohtu_miniprojekti?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=micaminoff/ohtu_miniprojekti&amp;utm_campaign=Badge_Grade)

[Travis CI](https://travis-ci.org/micaminoff/ohtu_miniprojekti)

[Codacy](https://www.codacy.com/app/micaminoff/ohtu_miniprojekti/dashboard)

https://github.com/mluukkai/ohjelmistotuotanto2017/wiki/miniprojekti

Backlogit (sis. hyväksymäkriteeri user storyille): 
https://docs.google.com/spreadsheets/d/1A-KVJh2fhpbPtjkOAJumxaMg-83lkm0ApZ5xVJ3QzVU/edit?ts=5a0d637d#gid=1


<strong>Asennus- ja käyttöohje:</strong>
Sovellus on työpöytäsovellus jota käytetään tekstikäyttöliittymän kautta komentoriviltä.
- Nouda jar-tiedosto osoitteesta: https://github.com/micaminoff/ohtu_miniprojekti/blob/master/ohtu_miniprojekti.jar
- Nouda database.zip osoitteesta: https://github.com/micaminoff/ohtu_miniprojekti/blob/master/database.zip
- Siirry hakemistoon, johon tallensit tiedostot ja pura database.zip samaan kansioon
- Käynnistä ohjelma komentorivilläsi seuraavalla komennolla: java -jar ohtu_miniprojekti.jar
- Käyttöliittymä on intuitiivinen ja listaa mahdolliset komennot käyttäjälle.
- Jos koneellasi ei ole javaa, lataa java koneellesi osoitteesta https://java.com/en/download/.


<strong>Ryhmän määrittely Definition of Donelle:</strong>
Tehtävä on tehty kun
  - tuotantokoodi on valmis
  - jos kyse luokasta, myös JUnit-testi valmis
  - koko paketti testattu ja läpäisee testit
  - testien rivikattavuus vähintään 80 %
  - viety Githubiin ja koko ohjelmiston Travis-testit menevät läpi

Koodin ylläpidettävyyden tulee olla mahdollisimman hyvä:
- koodikieli englanti
- luokkien nimet isolla, metodien pienellä, yleisesti camelCase
- noudatetaan yhdessä katsottua arkkitehtuuria
