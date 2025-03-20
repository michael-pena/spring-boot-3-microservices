FROM maven AS build

COPY pom.xml .

# Copy the source code and build the JAR
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:21-slim

WORKDIR /app

# copy whole project for building in the container
COPY --from=build /app/target/message-0.0.1-SNAPSHOT.jar /app/message-0.0.1-SNAPSHOT.jar
RUN apt-get update && apt-get install -y curl

EXPOSE 9010

# build the jars in the container
ENTRYPOINT [ "java", "-jar", "message-0.0.1-SNAPSHOT.jar" ]