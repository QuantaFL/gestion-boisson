package org.gestion.boisson.features.mouvements.services;

import org.gestion.boisson.features.mouvements.entities.Mouvement;

import java.util.List;

public interface MouvementService {
    Mouvement enregistrerMouvement(Mouvement mouvement); //TODO FEFO/FIFO
    List<Mouvement> getAllMouvementsByBoisson(Long boissonId);
    List<Mouvement> getAllMouvementsByLot(Long lotId);
    List<Mouvement> getAllMouvementsByUtilisateur(Long boissonId);
}
