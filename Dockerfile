FROM maven:3.8.6-openjdk-8 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:8-jdk-slim
WORKDIR /app
COPY --from=build /app/target/spring-boot-jwt-0.0.1-SNAPSHOT.jar /app/spring-boot-jwt.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-boot-jwt.jar"]
