FROM adoptopenjdk/openjdk11

ARG JAR_FILE=target/clinica-odontologica-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

EXPOSE 8080
