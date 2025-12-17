FROM swr.cn-north-4.myhuaweicloud.com/oomall-javaee/openjdk:17-alpine
WORKDIR /app
COPY target/aftersale-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
