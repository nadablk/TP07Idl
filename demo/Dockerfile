# Stage 1: Build
FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:resolve

# Copy source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy the built JAR from builder stage
COPY --from=builder /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose port (Render will override with PORT env var)
EXPOSE 8080

# Set environment variables
ENV JAVA_OPTS="-Xmx512m -Xms256m"
ENV PORT=8080

# Run the application with port configuration
CMD ["sh", "-c", "java ${JAVA_OPTS} -Dserver.port=${PORT} -jar app.jar"]
ENTRYPOINT ["java", "-jar", "app.jar"]
