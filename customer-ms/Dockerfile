FROM openjdk:21-slim

WORKDIR /app

# copy whole project for building in the container
#COPY . .
COPY ./target/customer-ms-0.0.1-SNAPSHOT.jar customer-ms-0.0.1-SNAPSHOT.jar

EXPOSE 8081

# build the jars in the container
#RUN ./mvnw clean install 
ENTRYPOINT [ "java", "-jar", "customer-ms-0.0.1-SNAPSHOT.jar" ]