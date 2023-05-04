FROM openjdk:17
RUN mkdir /app
WORKDIR /app
COPY target/endpoint-0.0.1-SNAPSHOT.jar /app
EXPOSE 8761

ENTRYPOINT ["java" , "-jar" , "endpoint-0.0.1-SNAPSHOT.jar"]
