FROM openjdk:11-jdk-slim AS build
WORKDIR /workspace/app

COPY gradle gradle
COPY build.gradle settings.gradle gradlew ./
COPY src src
COPY .javaenv .javaenv
COPY app-runner.sh app-runner.sh

RUN ./gradlew bootJar -x test

FROM alpine:latest

ENV JAVA_HOME="/usr/lib/jvm/default-jvm/"
RUN apk add openjdk11 bash

ENV PATH=$PATH:${JAVA_HOME}/bin

VOLUME /tmp
ARG DEPENDENCY=/workspace/app

COPY --from=build ${DEPENDENCY}/build/libs/*.jar ./app.jar
COPY --from=build ${DEPENDENCY}/.javaenv ./.javaenv
COPY --from=build ${DEPENDENCY}/app-runner.sh ./app-runner.sh

ENTRYPOINT bash ./app-runner.sh .javaenv app.jar
