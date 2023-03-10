# ECF5 containerisation de l'application localib

Après avoir fait les 4 ECF précedent il est temps de containeriser l'application.
Pour cela l'utilisation de différents Dockerfile et d'un docker-compose (afin d'orchestrer les containers) ont été créées.

## Pour lancer l'application :
- **Docker** au minimum
- <em> afin de pouvoir crééer les images de facon independante il faut **docker** associé ou non à un **IDE** de type VSC </em>

# Commandes pour lancer les Dockerfiles et le docker-compose.

### 1- A partir des dockerfile - indépendantes

Pour creer chaque image il faut aller dans les dossier specifiques : 
Dans dossiers specifique si souhaite crééer les images de faon indépendantes : <br>
> ``` docker build -t <nomADonnerALImage> . ```
<br>

Le '.' est le context, c'est à dire le dossier où se trouve le Dockerfile. Ce Dockerfile permet d'ajouter des couches à l'image alpine ou openJDK déjà créée et venant de DockerHub.
Afin de créer un container on doit par la suite lancer la commande : <br>
 > ```docker run -d <nomDeLImageDejaCreee>``` 

### 2- A partir du docker-compose qui va orchestrer les containers
Le docker-compose se lance de la racine du projet "ECF5/Docker", il se charge de crééer et lancer les containers en une seule commande : 

> ```docker-compose up -d ```

###  <em> 3 - Ajout sur un network commun dans le cas de l'utilisation de Dockerfiles. </em>
Il est préférable d'utiliser le docker-compose, de ce fait ils seront tous sur le même network en bridge.

Si les containers sont crées de facon autonome est faudra les ajouter sur un network. Pour cela il faudra créer un network : <br>
> ```docker network create -d bridge <nomADonner> ```

Puis ajouter les containers à ce dernier : <br>
> ```docker network connect <nomNetwork> <nomContainer> ```

En faisant <br> 
> ```docker network inspect <nomNetwork> ```

on aura accès aux containers compris dans ce dernier


