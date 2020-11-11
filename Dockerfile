# syntax=docker/dockerfile:experimental

FROM maven:3.6-jdk-8-alpine AS builder
WORKDIR /app
RUN --mount=target=. --mount=type=cache,target=/root/.m2 mvn package -DbuildDirectory=/target

FROM tomcat:8.5
COPY --from=builder /target/sparkjava-hello-world-1.0.war /usr/local/tomcat/webapps/ROOT.war
