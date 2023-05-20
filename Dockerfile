FROM maven:3.9.2-amazoncorretto-17

WORKDIR app/
COPY deviationTracker/deviationTracker/src src
COPY deviationTracker/deviationTracker/pom.xml pom.xml

RUN mvn clean package
EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/target/deviationTracker-0.0.1-SNAPSHOT.jar"]