# Build
FROM maven:3.9.2-amazoncorretto-17 as build
WORKDIR app/
COPY deviationTracker/deviationTracker/src src
COPY deviationTracker/deviationTracker/pom.xml pom.xml
RUN mvn clean package

# Package
FROM amazoncorretto:17-alpine
COPY --from=build /app/target/deviationTracker-0.0.1-SNAPSHOT.jar app/deviationTracker.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/deviationTracker.jar"]