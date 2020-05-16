# ADD OPEN JDK
FROM adoptopenjdk/openjdk11:alpine-jre

# PORT TO EXPOSE
EXPOSE 8761

# VAR TO GET JAR FILE
ARG JAR_FILE=target/registry_service.jar

# COPY THE JAR FILE
COPY ${JAR_FILE} registry_service.jar

# CREATE AN ENTRY POINT
ENTRYPOINT ["java", "-jar", "registry_service.jar"]