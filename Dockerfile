FROM amazoncorretto:17-alpine-jdk

COPY target/backend.jar app.jar

ENTRYPOINT ["java" , "-jar" , "/app.jar"]
