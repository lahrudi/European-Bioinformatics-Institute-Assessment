## Introduction
This example builds a simple REST API. (retrieve, update, delete and insert a person)

##  Keywords
Maven <br>
Java 1.8 <br>
Spring Boot <br>
Spring Data REST<br>
Swagger <br>
Lombok <br>
H2 (in memory db)<br>

## Building this Project

This project is built with Maven (http://maven.apache.org) so make sure you have an up-to-date installation of Maven
before proceeding.

Clone this project, change to the root spring-boot-example directory and run

```bash
mvn clean install
```

to build the binary of this project.

## Startup with Profile settings
#### Default profile, H2 database
```bash
java -jar  target/ebi-rest-api-0.0.1-SNAPSHOT.jar
```
or
```bash
mvn spring-boot:run
```

## Welcome page
The root shows a welcome page
```bash
http://localhost:8080/public
```
## Login
Use the following command to log in and then use the return value as a Bearer Token in other methods
```bash
curl --location --request POST 'http://localhost:8080/users/signin' \
--header 'Content-Type: application/json' \
--data-raw '{
      "username": "admin",
      "password": "Aa135642"
    }'
```

## REST API example
####Retrieve the list of people
```bash
curl --location --request GET 'http://localhost:8080/api/persons' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJpYXQiOjE2MTMzMTk0OTksImV4cCI6MTYxMzMyMDA5OX0.P3RydHYnbeODuHoukLpV7UP5GdqVX6Qr6PNfyGpBrv0' \
--data-raw ''
```

####Query by person id
```bash
curl --location --request GET 'http://localhost:8080/api/person/1' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJpYXQiOjE2MTMzMTk0OTksImV4cCI6MTYxMzMyMDA5OX0.P3RydHYnbeODuHoukLpV7UP5GdqVX6Qr6PNfyGpBrv0' \
--data-raw ''
```

####Add a list of people
```bash
curl --location --request POST 'http://localhost:8080/api/addPerson' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJpYXQiOjE2MTMzMjEzMTUsImV4cCI6MTYxMzMyMTkxNX0.JBzBJ-ZrEXz1t1RFnjNR3BkIwf-zN-EJdhF5WWvg29U' \
--header 'Content-Type: application/json' \
--data-raw '{  
   "person":[     {
      "first_name": "Cinzia",
      "last_name": "Bianchi",
      "age": "19",
      "favourite_colour": "yellow"
    },
    {
      "first_name": "Sarah",
      "last_name": "Robinson",
      "age": "54",
      "favourite_colour": "blue"
    } ]
}'
```

####Update the info for a list of persons

```bash
curl --location --request PUT 'http://localhost:8080/api/updatePerson' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJpYXQiOjE2MTMzMjEzMTUsImV4cCI6MTYxMzMyMTkxNX0.JBzBJ-ZrEXz1t1RFnjNR3BkIwf-zN-EJdhF5WWvg29U' \
--header 'Content-Type: application/json' \
--data-raw '{  
   "person":[     {
      "id":2 ,
      "first_name": "Jane",
      "last_name": "Doe",
      "age": "27",
      "favourite_colour": "yellow"
    }]
}'
```

####Delete a list of person by id
```bash
curl --location --request DELETE 'http://localhost:8080/api/deletePerson' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJpYXQiOjE2MTMzMjEzMTUsImV4cCI6MTYxMzMyMTkxNX0.JBzBJ-ZrEXz1t1RFnjNR3BkIwf-zN-EJdhF5WWvg29U' \
--header 'Content-Type: application/json' \
--data-raw '{  
   "person":[     {
      "id":1 
    }]
}'
```

### REST API 404 example
If the person is not present, the status code is 404.

```bash
curl --location --request GET 'http://localhost:8080/api/person/12' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJpYXQiOjE2MTMzMjIwMTcsImV4cCI6MTYxMzMyMjYxN30.Ktz9SVSe2lD8NIQjyQhKPkFfS4_otqpnF33caqp8big' \
--data-raw ''

HTTP/1.1 404
Content-Length: 0
Date: 
```
## Dockerize EuroBioInstServices
##### Build jar
``
mvn package -DskipTests
``
##### Build Docker image
``
docker build -t ebi-rest-api .
``
##### Save Docker image
``
docker save ebi-rest-api > ebi-rest-api.tar
``
##### Load Docker image
``
docker load < ebi-rest-api.tar
``
##### Run Docker container
``
docker run --name ebi-app -d ebi-rest-api
``
##### enter Docker container
``
docker exec -it ebi-app /bin/bash
``"# European-Bioinformatics-Institute-Assessment" 
