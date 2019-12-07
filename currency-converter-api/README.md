## POC

## Currency converter rest API
This provides rest service for currency converter

## Development requirements
1. Java 8
2. Maven 3
3. git
4. heroku

## How to run currency-converter-api using embed tomcat

1. import maven project form IDE (IntelliJ Idea)
2. run file "StartTomcat.java"
3. Application will run on port 9090, and ready to use, below are sample URLs:

i) Request input URL: http://localhost:9090/rest/currency/convert/amounts?source=USD&target=GBP&amount=100

Output: this will return only calculated amount:-

76.124932400

ii) Request input URL: http://localhost:9090/rest/currency/convert/details?source=USD&target=GBP&amount=100

output: this will return with more details:-

{"source":"USD","target":"GBP","amount":"100","convertedAmount":76.124932400,"convertedLocaleAmount":"£76.12","date":"2019-12-06","calculationTime":"30ms"}

## How to run currency-converter-api using heroku

#Deployment with the Heroku CLI

1. build a project uring maven command "mvn clean install" or "mvn package"
2. run "heroku plugins:install java"
3. run "heroku war:deploy target\currency-converter-api-1.0.war  --app currency-converter-rest-api"
4. run "heroku ps:scale web=1"

application deployed and ready to use and sample URLs are :

i) https://currency-converter-rest-api.herokuapp.com/rest/currency/convert/amounts?source=USD&target=GBP&amount=100

ii) https://currency-converter-rest-api.herokuapp.com/rest/currency/convert/details?source=USD&target=GBP&amount=100
