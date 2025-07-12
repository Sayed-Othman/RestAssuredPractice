# Stage 1: Build using Maven and custom Java 18 base
FROM eclipse-temurin:18-jdk AS build

WORKDIR /app

# Install Maven manually in the container
RUN apt-get update && apt-get install -y maven

# Copy all files
COPY . .

# Build & test the project
RUN mvn test

# Stage 2: Runtime with Allure CLI
FROM eclipse-temurin:18-jre

WORKDIR /app

# Copy test results from build stage
COPY --from=build /app/allure-results /app/allure-results

# Install Allure CLI
RUN apt-get update && apt-get install -y wget unzip && \
    wget https://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline/2.24.0/allure-commandline-2.24.0.zip && \
    unzip allure-commandline-2.24.0.zip -d /opt/ && \
    ln -s /opt/allure-2.24.0/bin/allure /usr/bin/allure && \
    rm allure-commandline-2.24.0.zip

# Entry point just generates the report and exits
CMD ["sh", "-c", "allure generate allure-results -o allure-report"]
