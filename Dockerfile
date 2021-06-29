FROM openjdk:11.0.11-jdk-slim-buster AS builder

COPY . .

RUN ["./gradlew", "--stacktrace", "assemble"]

FROM openjdk:11-jre-slim-buster

COPY --from=builder ./apiserver/build/libs/apiserver.jar .

CMD ["java", "-jar", "-Dspring.profiles.active=stage", "apiserver.jar"]
