WORKDIR /app
COPY nearnstyle-apis/target/*-runner /app/application
RUN chmod 775 /app/application
EXPOSE 8080
CMD ["/app/application"]
