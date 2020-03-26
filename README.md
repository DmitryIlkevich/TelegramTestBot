# TelegramTestBot

project tech summary <br/>
Java 11 <br/>
PostgreSQL <br/>
Spring boot <br/>

To start project you need to follow next steps:

<b>1) create database in postgresql with</b> 
  - name: guide_town_db 
  - port: 5432
  after shema and data will be loaded automaticly after app start
  
<b>2) bot config</b>
  - name : testTownGuideBot
  - token: 1078945734:AAEr3AwuT2fPIzmhY8FhALKJdawsDcwVoJg

<b>3) load project .jar and run it </b><br/>
  java -Dfile.encoding=UTF-8 -jar bot-test-1.0-SNAPSHOT.jar --spring.datasource.username=<write username> --spring.datasource.password=<write password>
  <br/>
  you need to write postgres username and password
  <br/><br/>
  
 To check that app is working you can use any of CRUD operations<br/>
 for ex. GET http://localhost:8080/guide/places will return all places<br/>
         GET http://localhost:8080/guide/places/москва will return info about moscow<br/>
 <br/>
 To check BOT you can send any of of EU capital to bot in RU lang.
