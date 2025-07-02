package org.gestion.boisson.features.mouvements.dao;

import org.gestion.boisson.features.mouvements.entities.Mouvement;

import java.util.List;

public interface MouvementDao {
    Mouvement save(Mouvement mouvement);
    Mouvement getById(Long id);
    List<Mouvement> findAll();
}
