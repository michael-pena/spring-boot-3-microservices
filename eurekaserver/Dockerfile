FROM maven AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the JAR
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:21-slim

WORKDIR /app

# copy whole project for building in the container
#COPY . .
COPY --from=build /app/target/eurekaserver-0.0.1-SNAPSHOT.jar /app/eurekaserver-0.0.1-SNAPSHOT.jar
COPY --from=build /root/.m2/repository/io/opentelemetry/javaagent/opentelemetry-javaagent/2.14.0/opentelemetry-javaagent-2.14.0.jar /app/libs/opentelemetry-javaagent-2.14.0.jar
RUN apt-get update && apt-get install -y curl

EXPOSE 8070

# build the jars in the container
#RUN ./mvnw clean install 
ENTRYPOINT [ "java", "-jar", "eurekaserver-0.0.1-SNAPSHOT.jar" ]