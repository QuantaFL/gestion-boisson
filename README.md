# Gestion Boisson

## Présentation

Ce projet est une application de gestion de boissons, permettant de gérer les utilisateurs, les stocks, les lots, les mouvements, etc. Il est structuré en modules pour faciliter la collaboration et la maintenance.

## Répartition des modules

- **Ouly** : Utilisateurs, Lots
- **Beni** : Boisson, Stock
- **Cheikh** : Mouvements

Chaque membre doit :
- Maîtriser le fonctionnement global des modules qui lui sont assignés (pas seulement coder les services).
- Lire et comprendre le code existant avant toute modification.
- Documenter toute fonctionnalité ou modification majeure.
- Respecter les conventions et bonnes pratiques décrites dans `rules.md`.

## Structure du projet

- `features/boissons` : Gestion des boissons
- `features/utilisateurs` : Gestion des utilisateurs et rôles
- `features/lots` : Gestion des lots de boissons
- `features/stocks` : Gestion des stocks
- `features/mouvements` : Gestion des mouvements de stock
- `utils/` : Utilitaires communs (ex: JPAUtil)

## Collaboration et Bonnes Pratiques

- **Interfaces de services** : Toujours créer une interface pour chaque service métier, pour faciliter les tests, la maintenance et la collaboration.
- **Nommage** : Respecter les conventions Java (voir `rules.md`).
- **Tests** : Ajouter des tests unitaires pour chaque fonctionnalité critique.
- **Documentation** : Documenter les méthodes publiques et les classes importantes.
- **Revue de code** : Relire le code des autres, proposer des améliorations, poser des questions.
- **Communication** : Prévenir l’équipe en cas de blocage ou de modification majeure.

## Démarrage

1. Cloner le dépôt
2. Configurer la base de données (voir `persistence.xml`)
3. Lancer l’application avec Maven ou votre IDE

## Ressources

- [rules.md](./rules.md) : conventions et bonnes pratiques à respecter
- [Javadoc](./target/site/apidocs/) : documentation générée

## Conseils pour une Collaboration Efficace

- Ne jamais se contenter d’implémenter un service sans comprendre le module.
- Prendre le temps de lire le code des autres modules pour mieux collaborer.
- Partager régulièrement l’avancement et les difficultés rencontrées.
- S’entraider et s’assurer que tout le monde comprend les enjeux métier.

---
# Docker-compose : 
un docker-compose contenant des images de postgrès et de pgAdmin a été ajouté pour uniformiser l'accès à la BDD. 
vous trouverez dans le fichier exemple du .env les variables necessaire pour bien l'exploiter.

### overview des variables : 
- **POSTGRES_USER** : l'utilisateur de postgres 
- **POSTGRES_PASSWORD** : le mot de passe de l'utilisateur de postgres 
- **POSTGRES_DB** :  le nom de la base de données 
- **PGADMIN_DEFAULT_EMAIL** :  l'email de connexion à l'interface de pgAdmin 
- **PGADMIN_DEFAULT_PASSWORD** : le mot de passe pour pgAdmin
- **POSTGRES_PORT** : le port exposé pour utiliser postgrès sur votre machine 
- **PGADMIN_PORT** : le port par lequel vous allez accéder à votre pgAdmin sur votre machine
```
POSTGRES_USER=lover 
POSTGRES_PASSWORD=lover
POSTGRES_DB=jee_boisson_database
PGADMIN_DEFAULT_EMAIL=quanta@gmail.com
PGADMIN_DEFAULT_PASSWORD=quanta123
POSTGRES_PORT=5433
PGADMIN_PORT=5000
```
- **Pour lancer le docker-compose** : docker compose up -d
- **Pour arrêter le docker-compose** : docker compose down
- **Pour accéder à pgAdmin** : http://localhost:5000
- **Pour connecter pgAdmin à la base de données** : 
  - Ouvrir pgAdmin
  - Créer un nouveau serveur
  - Nom du serveur : `jee_boisson_server`
  - Hôte : postgres-db
  - Port : `5432`
  - Nom d'utilisateur : `lover`
  - Mot de passe : `lover`

```
N’hésitez pas à enrichir ce document au fil de l’avancement du projet !

