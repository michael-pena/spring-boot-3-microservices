FROM openjdk:21-slim

WORKDIR /app

# copy whole project for building in the container
#COPY . .
COPY ./target/accounts-ms-0.0.1-SNAPSHOT.jar accounts-ms-0.0.1-SNAPSHOT.jar

EXPOSE 8080

# build the jars in the container
#RUN ./mvnw clean install 
ENTRYPOINT [ "java", "-jar", "accounts-ms-0.0.1-SNAPSHOT.jar" ]