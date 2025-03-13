FROM openjdk:21-slim

WORKDIR /app

COPY ./target/configserver-0.0.1-SNAPSHOT.jar configserver-0.0.1-SNAPSHOT.jar
COPY ./src/main/resources/config/ ./config/

RUN apt-get update && apt-get install -y curl

EXPOSE 8071

#RUN ./mvnw clean install 
ENTRYPOINT [ "java", "-jar", "configserver-0.0.1-SNAPSHOT.jar" ]