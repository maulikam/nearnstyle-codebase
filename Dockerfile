FROM quay.io/quarkus/ubi-quarkus-mandrel-builder-image:jdk-17
WORKDIR /app
COPY nearnstyle-apis/target/*-runner /app/application
RUN chmod 775 /app/application
EXPOSE 8080
CMD ["/app/application"]
