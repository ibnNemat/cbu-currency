FROM openjdk:19
ADD /target/valyuta-0.0.1-SNAPSHOT.jar demo-docker.jar
ENTRYPOINT ["java", "-jar", "demo-docker.jar"]