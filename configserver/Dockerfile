FROM maven AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the JAR
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:21-slim

WORKDIR /app

COPY --from=build /app/target/configserver-0.0.1-SNAPSHOT.jar /app/configserver-0.0.1-SNAPSHOT.jar
COPY --from=build /root/.m2/repository/io/opentelemetry/javaagent/opentelemetry-javaagent/2.14.0/opentelemetry-javaagent-2.14.0.jar /app/libs/opentelemetry-javaagent-2.14.0.jar
COPY ./src/main/resources/config/ ./config/

RUN apt-get update && apt-get install -y curl

EXPOSE 8071

#RUN ./mvnw clean install 
ENTRYPOINT [ "java", "-jar", "configserver-0.0.1-SNAPSHOT.jar" ]