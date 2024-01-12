# Build stage
FROM gradle:jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

# Package stage
FROM openjdk:17-jdk-slim
COPY --from=build /home/gradle/src/build/libs/*.jar /usr/local/lib/school-api.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","/usr/local/lib/school-api.jar"]
