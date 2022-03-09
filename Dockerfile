FROM openjdk:11-jdk-slim as build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)


FROM openjdk:11-jdk-slim
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency

ENV USERNAME_ENV=username
ENV PASSWORD_ENV=password
ENV URL_ENV=jdbc:postgresql://localhost:5432/test

COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT java -cp app:app/lib/* -DDATASOURCE_URL=$URL_ENV -DDATASOURCE_USERNAME=$USERNAME_ENV -DDATASOURCE_PASSWORD=$PASSWORD_ENV com.example.seabattle.SeaBattleApplication
