FROM gradle:7-jdk11 AS build
COPY --chown=gradle:gradle ../../../../../../../../my-shopping-list-api /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

FROM openjdk:11
EXPOSE 8080:8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/my-shopping-list-api-all.jar /app/my-shopping-list-api.jar
ENTRYPOINT ["java","-jar","/app/my-shopping-list-api.jar"]
