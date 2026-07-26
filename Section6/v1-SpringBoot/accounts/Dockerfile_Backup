# Start with a base image containing Java runtime
#FROM openjdk:17-jdk-slim	# ==> Image not found. Insted of this using below jdk image
FROM eclipse-temurin:17-jdk

# Information around who maintains the image
MAINTAINER eazybytes.com

# Add the application's jar to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# Execute the application
ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]