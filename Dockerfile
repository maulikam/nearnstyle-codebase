FROM alpine:latest
WORKDIR /app
COPY nearnstyle-apis/target/*-runner /app/application
EXPOSE 8080
CMD ["/app/application"]
