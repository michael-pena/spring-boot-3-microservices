# Customer MS

## Description

Customer MS - names, address, account number - associated in accounts microservice. 

## Requirements

- Docker Desktop
- Java 21
- Maven

<!-- TODO -->
## Installation and Execution

1. build docker images:

    ```bash
    cd spring-boot-3-microservices/customer-ms/
    ./mvnw clean compile install
    docker build . -t customer
    ```

<!-- TODO -->
2. After building all the microservices docker images, run docker compose:

    ```bash
    docker-compose up --build
    ```


## Using the API

<!-- TODO -->

### Getting a Token / OAuth2

## REST API Endpoints

| Method | URL                                          | Authorization| Body (JSON)                               |
|--------|----------------------------------------------|--------------|-------------------------------------------|
| POST   | `/auth/token`                                | No           | `{ "username": "admin", "password": "password1"}`                    |
