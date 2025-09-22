# Use a base image with Java
FROM openjdk:17-jdk-slim AS build

# Set the working directory
WORKDIR /app

# Copy the Maven/Gradle wrapper and project files
COPY . .

# If using Maven, build the jar file
RUN ./mvnw clean package -DskipTests

# Use a smaller runtime image for efficiency
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy only the built jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
