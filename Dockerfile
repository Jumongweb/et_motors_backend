FROM maven:3.8.7 as build
LABEL authors="lawaltoheeb"
COPY . .
RUN mvn -B clean package -DskipTests

FROM openjdk:17-jdk-slim

ARG DB_URL
ARG DB_USERNAME
ARG DB_PASSWORD
ARG CLOUD_NAME
ARG CLOUD_API_KEY
ARG CLOUD_API_SECRET
ARG SPRING_PROFILES_ACTIVE
ARG JWT_SECRET

ENV DB_URL=${DB_URL} \
    DB_USERNAME=${DB_USERNAME} \
    DB_PASSWORD=${DB_PASSWORD} \
    CLOUD_NAME=${CLOUD_NAME} \
    CLOUD_API_KEY=${CLOUD_API_KEY} \
    CLOUD_API_SECRET=${CLOUD_API_SECRET} \
    SPRING_PROFILES_ACTIVE=${SPRING_PROFILES_ACTIVE}
    JWT_SECRET=${JWT_SECRET}

RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY --from=build target/*.jar et_motors.jar

EXPOSE 2626

ENTRYPOINT ["java", "-jar", "-Dserver.port=2626", "et_motors.jar", "--spring.profiles.active=${SPRING_PROFILES_ACTIVE}"]

HEALTHCHECK --interval=30s --timeout=3s --start-period=30s --retries=3 \
  CMD curl -f http://localhost:2626/actuator/health || exit 1
