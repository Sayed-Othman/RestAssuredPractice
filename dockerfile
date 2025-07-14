# Stage 1: Build and test
FROM eclipse-temurin:18-jdk AS build

WORKDIR /app

# Install Maven
RUN apt-get update && apt-get install -y maven

# Copy source code
COPY . .

# Run tests (this generates allure-results)
RUN mvn clean test

# Stage 2: Create a minimal runtime image
FROM eclipse-temurin:18-jre

WORKDIR /app

# Copy allure-results from build stage
COPY --from=build /app/allure-results /app/allure-results