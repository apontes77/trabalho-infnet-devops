FROM amazoncorretto:17-alpine-jdk
MAINTAINER apontes77
ADD *.jar /app.jar

ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8

EXPOSE 80 443

ENTRYPOINT java $JAVA_OPTS -jar /app.jar