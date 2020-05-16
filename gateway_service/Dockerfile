# ADD OPEN JDK
FROM adoptopenjdk/openjdk11:alpine-jre

# PORT TO EXPOSE
EXPOSE 8080

# VAR TO GET JAR FILE
ARG JAR_FILE=target/gateway_service.jar

# COPY THE JAR FILE
COPY ${JAR_FILE} gateway_service.jar

# CREATE AN ENTRY POINT
ENTRYPOINT ["java", "-jar", "gateway_service.jar"]