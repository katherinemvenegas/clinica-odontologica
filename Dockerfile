FROM maven:3.8.6-openjdk-11

WORKDIR /home/app

ARG JAR_FILE=./target/demo-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

COPY src ./src

COPY pomp.xml .

ENTRYPOINT ["java", "-jar", "app.jar"]

RUN mvn clean package -DskipTests

EXPOSE 8080
