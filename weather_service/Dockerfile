# ADD OPEN JDK
FROM adoptopenjdk/openjdk11:alpine-jre

# PORT TO EXPOSE
EXPOSE 8082

# VAR TO GET JAR FILE
ARG JAR_FILE=target/weather_service.jar

# COPY THE JAR FILE
COPY ${JAR_FILE} weather_service.jar

# CREATE AN ENTRY POINT
ENTRYPOINT ["java", "-jar", "weather_service.jar"]