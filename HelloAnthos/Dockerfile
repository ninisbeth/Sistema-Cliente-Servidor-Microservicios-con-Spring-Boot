# Usa OpenJDK 21 como base
FROM openjdk:21-jdk-slim

# Instala Maven
RUN apt-get update && \
    apt-get install -y maven

# Establece el directorio de trabajo
WORKDIR /app

# Copia los archivos de tu aplicación al contenedor
COPY . .

# Construye la aplicación
RUN mvn clean package -DskipTests

# Configura el punto de entrada
CMD ["java", "-jar", "target/your-app.jar"]
