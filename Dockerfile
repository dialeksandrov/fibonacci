FROM maven:3-jdk-11 AS build
COPY . /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/src/pom.xml clean package

FROM openjdk:11-jre-slim
COPY --from=build /home/app/src/target/fibonacci-app.jar /usr/local/lib/fibonacci-app.jar
ENTRYPOINT ["java", "-jar", "/usr/local/lib/fibonacci-app.jar"]