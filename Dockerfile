FROM maven:3.8.6-jdk-8 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:8.0.1-jdk-slim
COPY --from=build /target/shoeshop-backend-0.0.1-SNAPSHOT.jar shoeshop-backend.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "shoeshop-backend.jar"]