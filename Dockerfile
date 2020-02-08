FROM openjdk:8-jre
ADD target/test-app-0.0.1-SNAPSHOT.jar test-app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","test-app.jar"]