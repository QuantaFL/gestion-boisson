package org.gestion.boisson.features.utilisateurs.entities;

public enum Role {
    GERANT, // Gérant du système avec accès complet
    EMPLOYE, // Employé avec accès restreint crud simple
    LIVREUR // Livreur avec consultation uniquement des commandes
}
