# README du dossier mouvements

Ce dossier gère les mouvements de stock (entrée, sortie, ajustement) dans l'application.

## Structure du dossier
- **controller/** : Contrôleur REST (MouvementController.java) pour recevoir les requêtes HTTP (POST, etc.)
- **dao/** : Interface pour accéder et enregistrer les mouvements en base.
- **dto/** : MouvementDto, sert à transférer les données entre le backend et le frontend. 
  - **Annotations dans le DTO** :
    - `@JsonInclude(JsonInclude.Include.NON_NULL)` : n'inclut dans la réponse JSON que les champs non nuls (plus propre pour le frontend).
    - `@JsonProperty` : permet de personnaliser le nom des champs dans le JSON envoyé/reçu (utile pour garder des noms clairs côté API).
    - `@Data` (Lombok) : génère automatiquement getters, setters, equals, hashCode, toString.
- **entities/** : Entité JPA Mouvement, représente un mouvement en base de données.
- **enums/** : TypeMouvement et TypeAjustement, limitent les valeurs possibles.
- **mappers/** : MouvementMapper et MouvementMapperImpl, convertissent automatiquement entre Mouvement et MouvementDto.
- **repositories/** : accès aux données en implementant le dao (DATA ACCESS OBJECT).
- **services/** : Interface et implémentation pour la logique métier des mouvements.

## Fonctionnement
- Le contrôleur REST reçoit une requête (ex : POST /mouvements).
- Le mapper transforme le DTO en entité.
- Le service enregistre le mouvement.
- Le mapper convertit l'entité en DTO pour la réponse.

## Dépendances importantes dans pom.xml
- **jakarta.ws.rs-api** : Pour créer des routes REST avec JAX-RS (@Path, @POST...)
- **jersey-cdi2-se** : Pour l'injection de dépendances (@Inject) avec Jersey.
- **jersey-media-json-jackson** : Pour gérer le JSON automatiquement dans les API REST.
- **jakarta.persistence-api** : Pour utiliser JPA et les entités (@Entity, @Id...)
- **jakarta.xml.bind-api** : Pour gérer le XML si besoin.
- **logback-classic** : Pour avoir des logs lisibles.
- **lombok** : Pour générer automatiquement les getters, setters, etc.

## Fichier beans.xml
- Le fichier `beans.xml` (souvent dans `META-INF` ou `WEB-INF`) est obligatoire pour activer l'injection de dépendances (CDI) avec Jersey et Jakarta EE.
- Sans ce fichier, les annotations comme `@Inject` ne fonctionnent pas.
- Il peut être vide, mais il doit exister pour que CDI soit activé.

## Pourquoi ces ajouts ?
- Sans ces dépendances et le fichier beans.xml, les routes REST, l'injection, la persistance, le mapping JSON/XML et les logs ne fonctionnent pas.
- Les mappers évitent d'écrire beaucoup de code manuel pour convertir entre entité et DTO.
- Lombok simplifie le code Java.

Lis le code source pour voir comment chaque partie fonctionne. Ce dossier est fait pour être simple, clair et facile à maintenir.
