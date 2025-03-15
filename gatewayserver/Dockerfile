FROM openjdk:21-slim

WORKDIR /app

# copy whole project for building in the container
#COPY . .
COPY ./target/gatewayserver-0.0.1-SNAPSHOT.jar gatewayserver-0.0.1-SNAPSHOT.jar 
RUN apt-get update && apt-get install -y curl

EXPOSE 8072

# build the jars in the container
#RUN ./mvnw clean install 
ENTRYPOINT [ "java", "-jar", "gatewayserver-0.0.1-SNAPSHOT.jar" ]