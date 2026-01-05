# Utilisez une image de base avec Java
FROM openjdk:17-jdk-slim

# Définition du répertoire de travail
WORKDIR /app

ADD target/health1-0.0.1-SNAPSHOT.jar /app.jar

# Exposez le port sur lequel votre application Spring Boot fonctionne (par défaut, c'est généralement 8080)
EXPOSE 8080

# Commande pour exécuter votre application
CMD ["java", "-jar", "app.jar"]
