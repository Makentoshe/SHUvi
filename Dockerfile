# syntax=docker/dockerfile:1

# creates a layer from the gradle docker image
FROM gradle:7.4.0-jdk11-alpine AS build
# copy the source code inside the container
COPY --chown=gradle:gradle . /home/gradle/src
# change the working directory
WORKDIR /home/gradle/src
# build the project and make sure all is ok
RUN gradle build --no-daemon
# build the fat jar file
RUN gradle shadowJar --no-daemon
# switch to the jdk image
FROM openjdk:11-jre-slim
# open 8080 port
EXPOSE 8080
# create new folder where fat jar will be placed
RUN mkdir /monolith-application
# copy fat jar for execution
COPY --from=build /home/gradle/src/monolith/build/libs/monolith-shadow.jar /app/monolith-shadow.jar
# execute java -jar with several flags
ENTRYPOINT ["java", "-jar", "/app/monolith-shadow.jar"]

