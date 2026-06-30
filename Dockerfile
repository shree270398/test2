FROM eclipse-temurin:17-jre
WORKDIR /app
# Copies the jar created in Stage 1 directly into this stage
COPY --from=build /workspace/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
