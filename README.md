# Spring Boot 3 Microservices

## Description

A Demonstration of microservices concepts using two very simple rest apis using Java 21, Spring Boot 3, Spring Cloud, Docker, Kubernetes etc. <br>
A simple "bank" application with only two services (accounts and customers) with their own h2 databases (implemented instead of PostgreSQL to save system resouces). <br>
This projected was made to learn microservices concepts using spring cloud / spring netflix, Apache Kafka, RabbitMq, etc.

## Technologies and libraries used

- Java 21
- Maven
- Spring Boot
- Docker / Docker Compose
- H2 Database
- Kubernetes
- Helm
- Mapstruct for DTO mapping
- Netflix Eureka Server
- Grafana, Prometheus, Loki, and Tempo
- Apache Kafka 
- Spring Cloud OpenFeign
- Spring Cloud Config
- Spring Cloud Gateway

## Requirements

- Docker Desktop
- Java 21
- Maven

<!-- TODO -->
## Installation and Execution

### Different ways to run:

1. Clone and cd into the repository:

    ```bash
    git clone https://github.com/michael-pena/spring-boot-3-microservices.git
    cd spring-boot-3-microservices/
    ```

<!-- TODO -->

2. Run light stack (no grafana + prometheus) using Docker Compose:

    ```bash
    cd ../docker-compose
    docker-compose up --build
    ```

3. Run stack with Grafana + Prometheus using Docker Compose (resource intensive):

    ```bash
    cd ../docker-compose/observability
    docker-compose up --build
    ```
4. Run with Kubernetes in docker desktop <br>
execute the bash script to build the docker images locally

    ```bash
    chmod +x ./buildimages.sh
    ./buildimages.sh all
    ```

    or build them individually

    ```bash
    ./buildimages.sh accounts-ms customer-ms ...
    ```

    cd into k8s/ and launch each service in order by file number

    ```bash
    kubectl apply -f 1_configmaps.yml
    kubectl apply -f 2_configserver.yml
    ...
    kubectl apply -f 6_gateway.yml
    ```


5. Run with Helm <br>



## Using the APIs

### REST API Endpoints

All endpoints are reachable through the spring cloud gateway server. <br>
The `customer/details/{customerId}` endpoint is an aggregated response from the customer and accounts service using OpenFeign. 

| Method | URL                                          | Body (JSON)                               |
|--------|----------------------------------------------|-------------------------------------------|
| POST   | `localhost:8072/bank/accounts/api/v1/account/` | `{ "customerId":"1", accountType="test", "balance":"1200"}` |
| POST   | `localhost:8072/bank/customers/api/v1/customer/` | `{ "firstName":"John" "lastName":"Doe" "address":"test" "accountNumber":"1"}` |
| GET    | `localhost:8072/bank/customers/api/v1/customer/details/{customerId}` |  |


## Todo
- Integrate Keycloak / OAuth2
- Add testing