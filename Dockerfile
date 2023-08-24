FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY nearnstyle-apis/target/nearnstyle-0.0.1-SNAPSHOT.jar nearnstyle-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "nearnstyle-0.0.1-SNAPSHOT.jar"]