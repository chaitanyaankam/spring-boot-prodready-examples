FROM openjdk:8-jre-alpine
VOLUME /tmp
RUN mkdir -p /app
WORKDIR /app
ARG JAR_FILE
COPY ${JAR_FILE} /app/book-service.jar
EXPOSE 8090
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/book-service.jar"]