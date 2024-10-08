# Etapa de construcción (build) utilizando una imagen mínima de Alpine
FROM alpine:latest as build

# Actualiza el índice de los repositorios de Alpine
RUN apk update

# Instala OpenJDK 17 para poder compilar el proyecto Java
RUN apk add openjdk17

# Copia todo el contenido del directorio actual (tu código fuente) al contenedor
COPY . .

# Da permisos de ejecución al script de Gradle (./gradlew) para poder ejecutar el build
RUN chmod +x ./gradlew

# Ejecuta el comando de Gradle para generar el archivo JAR del proyecto Spring Boot
# --no-daemon evita que Gradle corra en modo demonio para ahorrar memoria
RUN ./gradlew bootJar --no-daemon

# Etapa de producción utilizando una imagen ligera de OpenJDK en Alpine
FROM openjdk:17-alpine

# Expone el puerto 9000 para que la aplicación Spring Boot esté disponible en este puerto
EXPOSE 9000

# Copia el JAR generado en la etapa de build al contenedor final
# Copia desde la carpeta /app/build/libs/ el archivo JAR generado con el nombre específico
COPY --from=build ./build/libs/*.jar ./app.jar 

# Comando de entrada que ejecuta la aplicación Java usando el archivo JAR copiado
ENTRYPOINT ["java", "-jar", "app.jar"]
