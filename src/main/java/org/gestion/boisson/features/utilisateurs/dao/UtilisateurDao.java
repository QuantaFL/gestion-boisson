package org.gestion.boisson.features.utilisateurs.dao;

import org.gestion.boisson.features.utilisateurs.entities.Utilisateur;

import java.util.List;

public interface UtilisateurDao {
    Utilisateur getByEmail(String email);
    Utilisateur save(Utilisateur utilisateur);
    Utilisateur update(Utilisateur utilisateur);
    boolean delete(Long id);
    List<Utilisateur> getAll();
}
