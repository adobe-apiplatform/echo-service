echo-service
============

A Java [micro-service](https://github.com/adobe-apiplatform/micro-service-container) that implements an echo service.

### Building

Requirements:
- Java 8
- Maven 3.x

`mvn clean install`

### Continuous integration


### Running the service

The Maven build will then generate a single executable JAR file which will contain the micro-service code and the required libraries.

Use `java -jar target/echo-service-1.0-SNAPSHOT.jar` to run the service.

### Exposed APIs

- `POST http://localhost:8080/api/echo` outputs the request body on the response

- `GET http://localhost:8080/api/echo/headers` outputs the request headers on the response

