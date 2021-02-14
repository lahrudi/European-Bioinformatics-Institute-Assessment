FROM java:8
WORKDIR /
ADD target/ebi-rest-api-0.0.1-SNAPSHOT.jar //
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/ebi-rest-api-0.0.1-SNAPSHOT.jar"]
