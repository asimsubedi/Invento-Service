FROM openjdk:8-jdk-alpine

RUN apk add bash && apk update

WORKDIR /usr/app/src

COPY ./target/invento-0.0.1-SNAPSHOT.jar app.jar

RUN chmod 755 *.*

RUN ls -lptr

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]