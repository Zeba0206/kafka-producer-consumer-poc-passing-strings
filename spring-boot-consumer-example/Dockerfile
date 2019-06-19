FROM openjdk:11-jdk-stretch
ADD /target/kafka-consumer-0.0.1-SNAPSHOT.jar /src/app/kafka-consumer-0.0.1-SNAPSHOT.jar
WORKDIR /src/app/
ENTRYPOINT ["java", "-jar", "kafka-consumer-0.0.1-SNAPSHOT.jar"]