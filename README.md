# Spring Boot 3 Microservices

## Description

Demonstration of microservices concepts using Java and Spring boot 3, Docker, and Kubernetes.

## Technologies and libraries used

- Java 21
- Maven
- Swagger UI / OpenAPI
- Spring Boot
- PostgreSQL
- Docker
- Kubernetes
- Mapstruct for DTO mapping
- Eureka Server
- Spring Cloud Config
- Spring Cloud Gateway

<!-- TODO -->
## Features

- User Registration

## Requirements

- Docker Desktop
- Java 21
- Maven

<!-- TODO -->
## Installation and Execution

1. Clone and cd into the repository:

    ```bash
    git clone https://github.com/michael-pena/spring-boot-3-microservices.git
    cd spring-boot-3-microservices/
    ```

<!-- TODO -->
2. Build Jars for each service:

    ```bash
    cd spring-boot-3-microservices/accounts-ms/
    ./mvnw clean compile install

    cd ../customer-ms/
    ./mvnw clean compile install
    ```

3. Run with Docker Compose:

    ```bash
    cd ..
    docker-compose up --build
    ```


## Using the API

<!-- TODO -->

### Getting a Token / OAuth2

## REST API Endpoints

| Method | URL                                          | Authorization| Body (JSON)                               |
|--------|----------------------------------------------|--------------|-------------------------------------------|
| POST   | `/auth/token`                                | No           | `{ "username": "admin", "password": "password1"}`                    |
