FROM swr.cn-north-4.myhuaweicloud.com/oomall-javaee/openjdk:17-alpine
WORKDIR /app
COPY target/service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]
