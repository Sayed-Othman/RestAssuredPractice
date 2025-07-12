# Stage 1: Build and test
FROM eclipse-temurin:18-jdk AS build
WORKDIR /app
RUN apt-get update && apt-get install -y maven
COPY . .
RUN mvn test

# Stage 2: Runtime with Allure only
FROM eclipse-temurin:18-jre
WORKDIR /app

# Copy test results only
COPY --from=build /app/allure-results /project/allure-results

# Install Allure CLI
RUN apt-get update && apt-get install -y wget unzip && \
    wget https://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline/2.24.0/allure-commandline-2.24.0.zip && \
    unzip allure-commandline-2.24.0.zip -d /opt/ && \
    ln -s /opt/allure-2.24.0/bin/allure /usr/bin/allure && \
    rm allure-commandline-2.24.0.zip

CMD ["sleep", "infinity"]
