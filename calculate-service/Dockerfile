FROM eclipse-temurin:17-alpine

COPY ./target/calculation-service.jar calculation-service.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/calculation-service.jar"]
