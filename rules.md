# Règles de Codage et de Collaboration

## Conventions de Nommage

- **Classes** : PascalCase (ex: `UtilisateurService`)
- **Interfaces** : suffixer par `Interface` ou `Service` (ex: `StockInterface`, `LotService`)
- **Méthodes** : camelCase (ex: `getByEmail`, `ajouterBoisson`)
- **Variables** : camelCase (ex: `motDePasse`, `quantiteTotale`)
- **Constantes** : MAJUSCULES_UNDERSCORE (ex: `SEUIL_MINIMUM`)

## Déclaration des Fonctions

- Toujours spécifier la visibilité (`public`, `private`, etc.)
- Privilégier les signatures explicites et les types forts
- Ajouter des commentaires Javadoc pour chaque méthode publique

## Services et Interfaces

- **Obligatoire** : chaque service métier doit avoir une interface dédiée
- Implémenter les interfaces dans des classes séparées (ex: `UtilisateurServiceImpl`)
- Les interfaces facilitent les tests, la maintenance et la collaboration

## Bonnes Pratiques Java

- Utiliser Lombok pour réduire le boilerplate (constructeurs, getters/setters)
- Utiliser les annotations JPA pour la persistance
- Respecter l’immutabilité quand c’est possible
- Valider les entrées utilisateur et gérer les exceptions proprement
- Factoriser le code commun dans des utilitaires

## Collaboration

- **Lire le code existant** avant toute modification
- **Documenter** chaque nouvelle fonctionnalité ou modification majeure
- **Pousser régulièrement** vos modifications (push/pull)
- **Faire des revues de code** (code review) entre membres
- **Communiquer** en cas de doute, de blocage ou de changement d’architecture

## Tests

- Écrire des tests unitaires pour chaque méthode critique
- Utiliser des mocks pour les dépendances externes
- S’assurer que tous les tests passent avant de pousser

## Documentation

- Documenter les endpoints, les entités et les services
- Générer la Javadoc régulièrement
- Mettre à jour le README et le rules.md si besoin

## Éthique et Professionnalisme

- Respecter les délais et les engagements
- Être proactif : proposer des améliorations, signaler les problèmes
- S’entraider et partager les connaissances
- Ne jamais se contenter de “faire le service” sans comprendre le métier

## Collaboration sur les Modules

- Chaque membre doit apprendre et comprendre le module qui lui est assigné, pas seulement implémenter les services.
- Prendre le temps de lire et comprendre le code des autres modules pour une meilleure intégration.
- Partager régulièrement l’avancement et les difficultés rencontrées.
- S’assurer que tout le monde comprend les enjeux métier et techniques.

---

N’hésitez pas à enrichir ce document au fil de l’avancement du projet !

