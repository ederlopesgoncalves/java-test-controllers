FROM openjdk:8-jdk-alpine

VOLUME /tmp

ADD target/cars-0.0.1-SNAPSHOT.jar cars.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/cars.jar"]

