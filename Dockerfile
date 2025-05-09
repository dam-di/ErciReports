# Etapa 1: construir el JAR
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar pom.xml y descargar dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar el código fuente
COPY src ./src

# Compilar el proyecto
RUN mvn package -DskipTests

# Etapa 2: ejecutar el JAR
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 5655
ENTRYPOINT ["java", "-jar", "app.jar"]
