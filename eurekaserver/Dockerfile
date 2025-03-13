FROM openjdk:21-slim

WORKDIR /app

# copy whole project for building in the container
#COPY . .
COPY ./target/eurekaserver-0.0.1-SNAPSHOT.jar eurekaserver-0.0.1-SNAPSHOT.jar 
RUN apt-get update && apt-get install -y curl

EXPOSE 8070

# build the jars in the container
#RUN ./mvnw clean install 
ENTRYPOINT [ "java", "-jar", "eurekaserver-0.0.1-SNAPSHOT.jar" ]