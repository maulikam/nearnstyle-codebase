FROM alpine:latest
WORKDIR /app
COPY nearnstyle-apis/target/nearnstyle-apis-1.0.0-SNAPSHOT-runner /app/
EXPOSE 8080
CMD ["./nearnstyle-apis-1.0.0-SNAPSHOT-runner"]