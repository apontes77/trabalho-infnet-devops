FROM amazoncorretto:17-alpine-jdk
MAINTAINER apontes77
ADD *.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]