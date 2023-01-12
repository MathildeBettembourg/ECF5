# Recuperation de la version de openjdk 18, cette version contient un jdk java
FROM openjdk:18
 
# setting du workdir qui est le fichier dans lequel on va copier les fichiers
WORKDIR /app

# apres avoir la commande ./mvnw package clean qui met l'application dans un package, ce package compresse l'application, le maven, et le stock dans un zip qui, une fois executé lance spring
COPY ./target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose le port 8080 qui est le port choisis pour l'api, celle avec laquelle va communiquer le back
EXPOSE 8080

# commande qui s'effectuent quand le container demare, ici on lance java, puis active le fichier app.jar qui contient le fichier
CMD ["java", "-jar", "app.jar"]