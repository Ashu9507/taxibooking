FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/taxi-app.jar taxi-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "taxi-app.jar"]
