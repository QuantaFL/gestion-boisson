package org.gestion.boisson.features.utilisateurs.services;

import org.gestion.boisson.features.utilisateurs.entities.Utilisateur;

public interface UtilisateurService {
    Utilisateur authentifier(String email, String motDePasse);
    boolean changerMotDePasse(Long id, String ancienMotDePasse, String nouveauMotDePasse);
}
