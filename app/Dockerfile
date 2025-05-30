FROM openjdk:8-jdk-alpine

WORKDIR /usr/src/app

COPY . /usr/src/app

RUN apk add --no-cache maven && mvn clean install

EXPOSE 3001

CMD ["mvn", "spring-boot:run"]
