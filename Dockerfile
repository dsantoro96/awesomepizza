FROM amazoncorretto:21

WORKDIR /app

COPY ./target/awesomepizza-*.jar /app/awesomepizza.jar

ENTRYPOINT ["java", "-jar", "awesomepizza.jar"]

EXPOSE 8080
