FROM eclipse-temurin:17-jdk-alpine
MAINTAINER jguiu84@gmail.com

ARG JAR_FILE=/application/target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]


