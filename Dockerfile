## Stage 1 : build with maven builder image with native capabilities
FROM quay.io/quarkus/ubi-quarkus-mandrel-builder-image:jdk-17 AS build
WORKDIR /code

# Copying maven wrapper and the pom.xml first optimizes the Docker build cache
COPY nearnstyle-apis/mvnw /code/mvnw
COPY nearnstyle-apis/.mvn /code/.mvn
COPY nearnstyle-apis/pom.xml /code/

# Use Quarkus user for building
USER quarkus
RUN ./mvnw -B org.apache.maven.plugins:maven-dependency-plugin:3.1.2:go-offline
COPY nearnstyle-apis/src /code/src
RUN ./mvnw package -Dnative -DskipTests

## Stage 2 : create the docker final image
FROM quay.io/quarkus/quarkus-micro-image:2.0
WORKDIR /app

# Copy the native executable from the build container
COPY --from=build /code/target/*-runner /app/application

# set up permissions for user `1001`
RUN chmod 775 /app /app/application \
  && chown -R 1001 /app \
  && chmod -R "g+rwX" /app \
  && chown -R 1001:root /app

EXPOSE 8080
USER 1001

CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]
