FROM openjdk:17
RUN mkdir /app
WORKDIR /app
COPY target/Endpoint-0.0.1-SNAPSHOT.jar /app
EXPOSE 8761

ENTRYPOINT ["java" , "-jar" , "DockerDemo-0.0.1-SNAPSHOT.jar"]
