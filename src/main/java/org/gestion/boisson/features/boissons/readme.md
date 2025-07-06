# Documentation du Module `boissons`

## 1. Description Générale

Le module `boissons` est responsable de la gestion des boissons dans l'application. Il permet de créer, lire, mettre à jour et supprimer (CRUD) des informations sur les boissons. Ce module gère également la persistence des données des boissons, la logique métier et l'exposition des fonctionnalités via une API REST.

## 2. Structure du Module

Le module est structuré en plusieurs packages, chacun ayant une responsabilité spécifique :

-   **`controller`**: Contient les classes de contrôleur REST qui exposent les fonctionnalités du module via des endpoints HTTP.
-   **`dao`**: (Data Access Object) Définit les interfaces pour l'accès aux données des boissons.
-   **`database/seeder`**: Contient les classes pour initialiser la base de données avec des données de test.
-   **`dto`**: (Data Transfer Object) Contient les classes pour le transfert de données entre le client et le serveur.
-   **`entities`**: Contient les classes d'entités JPA qui représentent les tables de la base de données.
-   **`mappers`**: Contient les classes pour mapper les entités JPA en DTOs et vice-versa.
-   **`repository`**: Contient les implémentations des interfaces DAO, responsables de l'interaction avec la base de données.
-   **`services`**: Contient les interfaces et les implémentations de la logique métier du module.

## 3. Classes et Interfaces Clés

-   **`Boisson.java`**: Entité JPA représentant une boisson avec ses propriétés (nom, description, prix, etc.).
-   **`BoissonDto.java`**: DTO pour transférer les données de boisson.
-   **`BoissonMapper.java`**: Interface pour le mapping entre `Boisson` et `BoissonDto`.
-   **`BoissonDao.java`**: Interface définissant les opérations de base de données pour les boissons.
-   **`BoissonRepository.java`**: Implémentation de `BoissonDao` utilisant JPA pour interagir avec la base de données.
-   **`BoissonService.java`**: Interface définissant la logique métier pour la gestion des boissons.
-   **`BoissonServiceImpl.java`**: Implémentation de `BoissonService`.
-   **`BoissonController.java`**: Contrôleur REST qui gère les requêtes HTTP pour les boissons.
-   **`BoissonSeeder.java`**: Classe qui peuple la base de données avec des boissons initiales au démarrage de l'application.

## 4. Endpoints de l'API REST

Le `BoissonController` expose les endpoints suivants :

| Méthode | Chemin | Description |
| --- | --- | --- |
| `GET` | `/boissons/health` | Vérifie l'état de santé du service. |
| `GET` | `/boissons` | Récupère la liste de toutes les boissons. |
| `POST` | `/boissons` | Ajoute une nouvelle boisson. |
| `GET` | `/boissons/{nom}` | Récupère les détails d'une boisson par son nom. |
| `PUT` | `/boissons` | Met à jour une boisson existante. |
| `GET` | `/boissons/actives` | Récupère la liste de toutes les boissons actives. |
| `GET` | `/boissons/inactives` | Récupère la liste de toutes les boissons inactives. |

## 5. Base de Données

L'entité `Boisson` est mappée à la table `boissons` dans la base de données. La classe `BoissonSeeder` est utilisée pour insérer des données initiales dans cette table si elle est vide.

## 6. TODO

Voici une liste des tâches à effectuer pour améliorer ce module :

-   Refactoriser le code pour le rendre plus lisible et maintenable.
-   Refactoriser la `NamedQuery` pour la recherche par nom afin d'utiliser `LIKE`.
-   Ajouter des tests unitaires pour les services.
-   Documenter les endpoints REST de manière plus détaillée (par exemple avec Swagger/OpenAPI).
-   Améliorer la gestion des erreurs.
-   Ajouter des fonctionnalités de recherche avancée.
-   Optimiser les requêtes SQL pour les performances.
-   Mettre en place une authentification sécurisée pour les endpoints.
-   Ajouter des logs pour le suivi des actions importantes.

## 7. Données sous format JSON
json update  :

{
"id": 5,
"nom": "Jus 2 orange",
"description": "Boisson fraîche 100% naturelle",
"volume": 0.5,
"unite": "L",
"prixUnitaire": 2.5,
"seuil": 10,
"isActive": false
}

json save :
{
"nom": "Jus 2 orange",
"description": "Boisson fraîche 100% naturelle",
"volume": 0.5,
"unite": "L",
"prixUnitaire": 2.5,
"seuil": 10,
"isActive": false
}
