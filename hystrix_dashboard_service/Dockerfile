# ADD OPEN JDK
FROM adoptopenjdk/openjdk11:alpine-jre

# PORT TO EXPOSE
EXPOSE 8083

# VAR TO GET JAR FILE
ARG JAR_FILE=target/hystrix_dashboard_service.jar

# COPY THE JAR FILE
COPY ${JAR_FILE} hystrix_dashboard_service.jar

# CREATE AN ENTRY POINT
ENTRYPOINT ["java", "-jar", "hystrix_dashboard_service.jar"]