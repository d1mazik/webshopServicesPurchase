FROM gradle:7.3.3-jdk17 AS build

WORKDIR /app

# Copy the build.gradle and settings.gradle files to the working directory
COPY build.gradle .
COPY settings.gradle .

# Copy the source code to the working directory
COPY src ./src

# Build the application
RUN gradle build --no-daemon

# Use a different OpenJDK image as the runtime environment
FROM openjdk:17-jdk-alpine AS runtime

# Copy the built JAR file from the build environment to the runtime environment
COPY PurchaseService-0.0.1-SNAPSHOT.jar .


# Expose the port on which the application will run (change it if necessary)
EXPOSE 8080

# Set the command to run the application
CMD ["java", "-jar", "PurchaseService-0.0.1-SNAPSHOT.jar"]
