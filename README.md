#PragmaBrewery​ Code ​Challenge

The application is developed as a maven project using `Java 8` and `Spring Boot`. TDD is being used for the code challenge.
For hardcoded services there are no tests which are marked as `@Profile("!unitTest")`.

##Start
Run the `Server.java` file, which will start the spring boot web server and monitor the temperature for all configured Drinks. Drinks are currently hardcoded based on the given configuration. 

##API
The default port is 8090 configured in `configuration-default.properties` file. 

`GET /services/temperature/{Drinks ID}` will return the current temperature of Drinks ID.

##Assumptions
* There is some way to get the temperature data. In real world we need to implement `TemperatureDataDao`. For code challenge `TemperatureDataDaoImpl` returns a Random number.
* There is some way to notify the user. In real world we need to implement `NotifyMessageService`. For code challenge `NotifyMessageServiceImpl` prints to the console.
 
##Improvements
* Running Read Service in parallel.
* Rather than pulling information, if there is a way that the sensor can push information then things will be much better.
* Use some service to get all the configured Drinks instead of hardcoding.
* Use Database.