FROM openjdk:8u121-jdk-alpine

COPY target/market-price-handler-0.0.1-SNAPSHOT.jar  /app.jar

ARG DB_HOST

ENTRYPOINT java -Dspring.datasource.url=jdbc:postgresql://$DB_HOST:5432/postgres -jar /app.jar
