FROM maven:3.8.6-openjdk-11

WORKDIR /home/app

COPY src ./src

COPY pom.xml .

ENTRYPOINT ["java", "-jar", "./target/demo-0.0.1-SNAPSHOT.jar"]

RUN mvn clean package -DskipTests

EXPOSE 8080
