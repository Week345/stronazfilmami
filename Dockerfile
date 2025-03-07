FROM openjdk:21
FROM maven:3.9.8-eclipse-temurin-21
WORKDIR /app
ADD pom.xml /app
RUN mvn verify clean --fail-never
COPY . /app
RUN mvn -v
RUN mvn clean install -DskipTests
EXPOSE 8090
ADD ./target/*.jar /app/backend.jar
ENTRYPOINT ["java", "-jar", "/app/backend.jar"]
