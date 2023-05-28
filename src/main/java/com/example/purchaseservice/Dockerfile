# Use a base image with Gradle and JDK
FROM gradle:7.3.3-jdk17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the build.gradle and settings.gradle files to the working directory
COPY build.gradle .
COPY settings.gradle .

# Copy the source code to the working directory
COPY src ./src

# Build the application
RUN gradle build --no-daemon

# Use a different OpenJDK image as the runtime environment
FROM adoptopenjdk:17-jdk-hotspot AS runtime

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the build environment to the runtime environment
COPY --from=build /app/build/libs/PurchaseService-0.0.1-SNAPSHOT.jar .

# Expose the port on which the application will run (change it if necessary)
EXPOSE 8080

# Set the command to run the application
CMD ["java", "-jar", "PurchaseService-0.0.1-SNAPSHOT.jar"]
