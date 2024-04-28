V2 pour TP 2 du 23/04/2024 : Réunification de 2 projets individuels pour mettre en commun et continuer de travailler en groupe sur le reste du projet


# WEB SERVICE : Movie Project

## Table des matières

1. [Avant de commencer](#avant-de-commencer)
2. [Contexte](#contexte)
3. [Introduction](#introduction)
4. [Membres du projet](#membres-du-projet)
5. [Technologies utilisées](#technologies-utilisées)  
6. [Contraintes](#contraintes)
7. [Documentations](#documentations)
8. [Contact](#contact)  



## Avant de commencer
Ceci est un projet d'un Web Service REST, en JAVA avec Spring.


### Prérequis

Pour plus de simplicité, vous pouvez utiliser Docker, ou si vous êtes à l'aise en Java, un environnement de dev


#### Avec Docker
- Installer Docker : https://docs.docker.com/engine/install/
- Ouvrir un CMD
- Utilisez la commande  `cd` pour naviguer jusqu'à la racine du projet, où ce trouve le fichier `docker-compose.yaml`
- Lancer la commande :
```sh
docker compose up -d
```


#### Avec tout le nécessaire en local
- Java évidement :
	- Téléchargez et installez Java JDK à partir du site officiel d'Oracle : https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html
	- Suivez les instructions d'installation pour votre système d'exploitation
- Maven :
	- Téléchargez et installez Apache Maven à partir du site officiel : https://maven.apache.org/download.cgi
    - Décompressez l'archive téléchargée dans un répertoire de votre choix. 
    - Configurez la variable d'environnement  M2_HOME pour qu'elle pointe vers le répertoire Maven que vous venez de décompresser. 
    - Ajoutez le répertoire  bin de Maven à votre variable d'environnement  PATH. 
- PostgreSQL
	- Téléchargez et installez : https://www.postgresql.org/download/
	- Suivez les instructions d'installation pour votre système d'exploitation
- PGAdmin 
	- Téléchargez et installez : https://www.pgadmin.org/download/
	- Suivez les instructions d'installation pour votre système d'exploitation
	- Créer une base de donnée `movie-project`


### Installation 

1. Avant tout, cloner le projet ;) 
```sh
git clone https://github.com/BastienBrunet/movie-project.git
```

2. Ouvrer le projet avec un IDE (Eclipse, Intellij ou celui que vous connaissez bien pour du JAVA)
		
- Un exemple avec Eclipse ( https://www.eclipse.org/downloads/ )
	- Ouvrez votre projet dans Eclipse 
	- Lancez Eclipse et ouvrez le projet en utilisant  File -> Open Projects from File System ou  File -> Import -> Maven -> Existing Maven Projects, selon votre configuration. 
	- Ouvrer le projet client, src/main/resources
		- Modifier le fichier `application-local.properties` avec votre username et mot de passe PostgreSQL
	- Exécutez le projet : 
		- Aller dans le projet client, src/main/java/com/mouvie/client
		- Cliquez avec le bouton droit de la classe `ClientApplication`, sélectionnez  Run As -> Debug Configuration 
		- Choisissez le Profile `local`
	
 
Maven téléchargera les dépendances nécessaires, compilera le code source et exécutera l'application Spring Boot. 
Vous pourrez accéder à l'application via un navigateur Web ou un outil comme Postman en utilisant l'URL par défaut http://localhost:8080/


## Contexte

L’objectif est la création d’un WebService permettant la gestion d’une liste de film.


## Introduction
La notion de collection de d’entité (comme des catégories, des langues …) est un des premiers concept croisé en entreprise.

C’est aussi très pratique pour mettre en place les bases des projets sur lesquels vous travaillerez par la suite.

- Il doit disposer de routes de récupération des films (en listing, et récupération d’entité)
- Il doit être possible de modifier un film
- Il doit être possible de créer un film
- Il doit être possible de supprimer un film


## Membres du projet 

- Bastien Brunet M2 Développement Logiciel, Mobile et IOT
- Clara Vesval M2 Développement Logiciel, Mobile et IOT

On a fait le premier projet séparemment, puis on a décid d'utiliser la base de code de Bastien Brunet pour continuer le TP 2

(TP 1 de Clara Vesval : https://github.com/Clara-1606/WebService-Movie1)


## Technologies utilisées
- Java 21
- Spring boot 3.2.5
- PostgreSQL 


## Contraintes 

(Faire plaisir à Richardson)

### L’entité film dispose des éléments suivants :

- Le nom (texte libre, maximum 128 charactères)
- La description (texte libre, maximum 2048 charactères)
- La date de parution (date format ISO 8601)
- La note (entier entre 0 et 5, optionel)
- Un film doit être attaché à une ou plusieurs catégories
- Un film a une affiche


### Les codes de retour sont implémentés :

- 200 – OK, tout s’est bien passé pour une récupération de ressource ou listing ou modification avec succès
- 201 – Ressource crée avec succès
- 202 – Requête acceptée, mais aucune garantie que tout ira bien au final
- 204 – Requête traitée, mais aucune information à renvoyer
- 206 – Seul une partie du résultat de la requête est retournée
- 301 – Redirection permanente
- 302 – Redirection temporaire
- 400 – Requête invalide, malformée …
- 401 – Authentification nécessaire
- 403 – Accès à la ressource interdit
- 404 – Ressource non trouvée (absente)
- 406 – Impossible de répondre aux exigence de Accept
- 410 – La ressource n’existe plus
- 418 – Je suis une théière (indispensable à mettre en easter egg)
- 422 – Validation impossible (Impossible de traiter l’entité fournie (incompréhensible ou incomplète))


### Deux formats de sortie sont gérés :

- JSON
	-  Il devra pouvoir retourner du JSON HAL
- XML

Ces formats sont explicités via le header Accept


### Pagination

Amélioration du WebService permettant la gestion d’une liste de film. (seul une fraction de ces résultats intéresse l’utilisateur final (ou développeur)
Le résultat doit être paginé (page 1 sur 22, 10 éléments par page par exemple)


### Recherche

Il doit permettre de rechercher par titre ou par description


### End Points

On doit pouvoir lister les catégories d’un film et inversement


### Uploader une affiche de film (photo)

Il devra : 
- Etre validé : (type mime, poids …),
- Le rendre disponible avec un lien 
- Et surtout pouvoir l’afficher



## Documentations

Implémentation de la documentation OpenAPI (anciennement Swagger) disponible lorsqu'on lance le projet :
- http://localhost:8080/swagger-ui/index.html
- http://localhost:8080/v3/api-docs

Implémentation de la documentation Postman disponible n'importe quand :
- https://documenter.getpostman.com/view/25108943/2sA3Bq5rR2



## Contact 

Clara Vesval (https://clara-1606.github.io/) [![LinkedIn][linkedin-shield]][linkedin-url-clara].  
Bastien Brunet   [![LinkedIn][linkedin-shield]][linkedin-url-bastien].

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url-clara]: https://www.linkedin.com/in/clara-vesval-84b911193/
[linkedin-url-bastien]: https://www.linkedin.com/in/bastienbrunet/
