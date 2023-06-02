FROM amazoncorretto:11
WORKDIR /app-webflux
COPY ./build/libs/webflux-0.0.1-SNAPSHOT.jar ./webflux-app.jar
CMD ["java", "-jar", "webflux-app.jar"]