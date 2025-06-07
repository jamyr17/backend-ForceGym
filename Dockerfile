FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk
WORKDIR /app
COPY --from=build /app/target/ForceGym-Backend.jar .
EXPOSE 7000
ENTRYPOINT ["java", "-jar", "ForceGym-Backend.jar"]
