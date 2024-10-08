# Etapa de compilación
FROM openjdk:17-alpine AS build

# Instalar dependencias necesarias
RUN apk update && apk add --no-cache bash

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar solo archivos esenciales para mejorar el tiempo de build
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# Dar permisos de ejecución a gradlew
RUN chmod +x ./gradlew

# Descargar dependencias del proyecto para cachear en futuros builds
RUN ./gradlew --no-daemon build || return 0

# Copiar el resto del código del proyecto
COPY . .

# Ejecutar el build de Gradle para generar el JAR
RUN ./gradlew bootJar --no-daemon

# Etapa de ejecución
FROM openjdk:17-alpine

# Establecer variables de entorno
ENV PORT=9000
ENV JAVA_OPTS=""

# Crear un directorio para la aplicación
WORKDIR /app

# Exponer el puerto de la aplicación
EXPOSE ${PORT}

# Copiar el archivo JAR generado desde la etapa de compilación
COPY --from=build /app/build/libs/mutant-0.0.1-SNAPSHOT.jar ./app.jar

# Comando para ejecutar la aplicación
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

