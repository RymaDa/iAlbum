# iAlbum
iAlbum est une application Android développée avec le langage de programmation Kotlin et l'architecture MVVM avec une approche de Clean Architecture. Elle utilise Retrofit pour récupérer les données depuis le serveur, Koin pour l'injection de dépendances, Coroutine pour la gestion des tâches asynchrones, Glide pour le chargement des images et SQLite avec Room pour la sauvegarde des données et l'affichage en mode hors ligne. JUnit est utilisé pour les tests unitaires.

## Architecture
L'application est structurée en utilisant une approche de Clean Architecture, qui se compose de trois couches principales :

* La couche de présentation qui gère l'interface utilisateur et communique avec les autres couches via le ViewModel. 
* La couche de domaine qui contient la logique métier de l'application. 
* La couche de données qui contient les sources de données, telles que la récupération de données depuis le serveur et la base de données locale.
> L'utilisation de cette architecture permet de séparer les préoccupations et d'assurer une meilleure testabilité de l'application.

## Bibliothèques et outils

* Retrofit : une bibliothèque pour récupérer les données depuis le serveur de manière simple et rapide
* Koin : un framework léger pour l'injection de dépendances en Kotlin
* Coroutine : une bibliothèque pour gérer les tâches asynchrones de manière simple et concise
* Glide : une bibliothèque pour le chargement des images de manière efficace
* SQLite avec Room : une bibliothèque pour la sauvegarde des données localement
* JUnit : un framework de test pour les tests unitaires.

## Installation
1. Cloner le projet depuis GitHub en utilisant la commande 'https://github.com/RymaDa/iAlbum.git'
2. Ouvrir le projet dans Android Studio
3. Construire et exécuter l'application sur un émulateur ou un périphérique Android