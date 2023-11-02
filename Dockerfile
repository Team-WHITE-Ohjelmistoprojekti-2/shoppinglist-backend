FROM maven:3.9.5-eclipse-temurin-17-alpine AS build
COPY src/main /app/src/main
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package

# Use Alpine Linux
FROM eclipse-temurin:17-jre-alpine
COPY --from=build /app/target/shoppinglist-0.0.1-SNAPSHOT.jar /app/shoppinglist.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/shoppinglist.jar"]