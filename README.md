# TelegramTestBot

project tech summary 
Java 11
PostgreSQL
Spring boot

To start project you need to follow next steps:

1) create database in postgresql with 
  - name: guide_town_db 
  - port: 5432
  after shema and data will be loaded automaticly after app start
  
2) bot config
  - name : testTownGuideBot
  - token: 1078945734:AAEr3AwuT2fPIzmhY8FhALKJdawsDcwVoJg

3) load project .jar and run it 
  java -Dfile.encoding=UTF-8 -jar bot-test-1.0-SNAPSHOT.jar --spring.datasource.username=<write username> --spring.datasource.password=<write password>
  
  you need to write postgres username and password
  
  
 To check that app is working you can use any of CRUD operations
 for ex. GET http://localhost:8080/guide/places will return all places
         GET http://localhost:8080/guide/places/москва will return info about moscow
 
 To check BOT you can send any of of EU capital to bot in RU lang.
