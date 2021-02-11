# Patient documentation
Project for managing patient documentation

**Prerequisites:** [Java 14](https://adoptopenjdk.net/).

## Steps to run database
1. Build `docker-compose build`
2. Run `docker-compose up`
3. Database is accessible via localhost:3756
4. Use username and password as 'xxx'

## Steps to run application
1. Build the project using
   `mvn clean install`
2. Run using `mvn spring-boot:run`
3. The web application is accessible via localhost:8083
4. To create an account in application you need to set up **Email Properties** in application.properties
