FROM eclipse-temurin:17-jdk-jammy
MAINTAINER jguiu84@gmail.com

ARG JAR_FILE=/application/target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]


