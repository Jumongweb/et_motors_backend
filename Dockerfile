FROM maven:3.8.7 as build
LABEL authors="lawaltoheeb"
COPY . .
RUN mvn -B clean package -DskipTests

FROM openjdk:17-jdk-slim
RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*
WORKDIR /app
COPY --from=build target/*.jar et_motors.jar
EXPOSE 2626

ENTRYPOINT ["java", "-jar", "-Dserver.port=2626", "et_motors.jar"]

HEALTHCHECK --interval=30s --timeout=3s --start-period=30s --retries=3 \
  CMD curl -f http://localhost:2626/actuator/health || exit 1