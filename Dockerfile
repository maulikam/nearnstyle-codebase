## Stage 1 : Copy the built application
FROM quay.io/quarkus/ubi-quarkus-mandrel-builder-image:jdk-17 AS build
WORKDIR /code
COPY nearnstyle-apis/target/*-runner /code/application

## Stage 2 : create the docker final image
FROM quay.io/quarkus/quarkus-micro-image:2.0
WORKDIR /app

# Copy the native executable from the build container
COPY --from=build /code/application /app/application


# set up permissions for user `1001`
RUN chmod 775 /app /app/application \
  && chown -R 1001 /app \
  && chmod -R "g+rwX" /app \
  && chown -R 1001:root /app

EXPOSE 8080
USER 1001

CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]
