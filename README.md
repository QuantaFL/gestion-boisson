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

N’hésitez pas à enrichir ce document au fil de l’avancement du projet !

