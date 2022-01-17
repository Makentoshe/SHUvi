FROM openjdk:11
COPY . /app
WORKDIR /app

RUN ./gradlew build --stacktrace
