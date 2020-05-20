
# For Java 8
FROM openjdk:8-jdk-alpine

# Set the working directory.
WORKDIR /API_Gateway

ARG JAR_FILE=target/api-gateway-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} /API_Gateway/app.jar

EXPOSE 8080/tcp

# java -jar /AppointmentManagement/app
ENTRYPOINT ["java","-jar","/API_Gateway/app.jar"]
