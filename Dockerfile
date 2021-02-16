FROM openjdk:8u121-jdk-alpine

COPY target/*-jar-with-dependencies.jar  /app.jar

ENTRYPOINT java -jar /app.jar