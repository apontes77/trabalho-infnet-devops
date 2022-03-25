FROM amazoncorretto:17-alpine-jdk
MAINTAINER apontes77
COPY target/app.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]