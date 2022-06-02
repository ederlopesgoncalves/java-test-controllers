# Testing Controllers #

## Install Application

mvn clean install

## Run Application

mvn spring-boot:run

## Local Test

http://localhost:8080/cars/1

## Run Unit Test

mvn clean test

# Test via Docker

docker build .

docker run -p 8080:8080 cars

### Command

curl http://localhost:8080/cars/10

