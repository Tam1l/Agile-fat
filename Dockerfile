# Use an official Maven image to build the app
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B clean package

# Run the app using a smaller JRE image
FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /app/target/online-donation-system-1.0-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]
