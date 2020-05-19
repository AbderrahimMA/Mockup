FROM openjdk:8-jdk-alpine
COPY target/api-gateway-0.0.1-SNAPSHOT.jar /app.jar
EXPOSE 7777/tcp
ENTRYPOINT ["java", "-jar", "/app.jar"]


