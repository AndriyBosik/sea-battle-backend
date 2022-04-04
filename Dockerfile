FROM openjdk:11-jdk-slim AS build
WORKDIR /workspace/app

COPY gradle gradle
COPY build.gradle settings.gradle gradlew ./
COPY src src

RUN ./gradlew bootJar -x test

FROM openjdk:11-jdk-slim
VOLUME /tmp
ARG DEPENDENCY=/workspace/app

ENV USERNAME_ENV=username
ENV PASSWORD_ENV=password
ENV URL_ENV=jdbc:postgresql://localhost:5432/sea_battle

COPY --from=build ${DEPENDENCY}/build/libs/*.jar ./app.jar
ENTRYPOINT java -jar -DDATASOURCE_URL=$URL_ENV -DDATASOURCE_USERNAME=$USERNAME_ENV -DDATASOURCE_PASSWORD=$PASSWORD_ENV app.jar
