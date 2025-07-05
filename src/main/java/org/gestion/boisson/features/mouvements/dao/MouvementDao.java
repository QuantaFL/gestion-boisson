package org.gestion.boisson.features.mouvements.dao;

import org.gestion.boisson.features.mouvements.entities.Mouvement;

import java.util.List;

public interface MouvementDao {
    Mouvement save(Mouvement mouvement);
    List<Mouvement> saveAll(List<Mouvement> mouvement);
    Mouvement getById(Long id);
    List<Mouvement> findAll();
    List<Mouvement> findByUtilisateurId(Long utilisateurId);
}
