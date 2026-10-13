# Build stage using official Eclipse Temurin JDK 17
FROM eclipse-temurin:17-jdk-jammy AS build
WORKDIR /app

# Copy project files into the container
COPY . .

# Grant execution permission to the Maven wrapper and build the JAR
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Run stage
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copy the generated JAR file to the runtime container
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]