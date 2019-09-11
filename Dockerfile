FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} api.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.s ecurity.egd=file:/dev/./urandom","-jar","/api.jar"]