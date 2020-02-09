FROM openjdk:8-jre
ADD target/spring-app.jar spring-app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","spring-app.jar"]