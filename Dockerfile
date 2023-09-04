FROM openjdk:17-jre-slim
WORKDIR /app
COPY nearnstyle-apis/target/nearnstyle-apis-1.0.0-SNAPSHOT.jar /app/
EXPOSE 8080
CMD ["java", "-jar", "./nearnstyle-apis-1.0.0-SNAPSHOT.jar"]
