FROM openjdk:17-jdk-alpine
COPY target/OnlineBlog-0.0.1-SNAPSHOT.jar onlineblog.jar
ENTRYPOINT ["java", "-jar", "onlineblog.jar"]