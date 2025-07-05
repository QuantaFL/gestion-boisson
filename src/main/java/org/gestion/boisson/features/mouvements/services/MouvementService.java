package org.gestion.boisson.features.mouvements.services;

import org.gestion.boisson.features.mouvements.entities.Mouvement;

import java.util.List;

public interface MouvementService {
    Mouvement enregistrerMouvement(Mouvement mouvement); // single mouvement (with FIFO/FEFO)

    List<Mouvement> enregistrerMouvementsBatch(List<Mouvement> mouvements); // batch processing, CSV import use case

    Mouvement enregistrerRetour(Mouvement mouvement, String raisonRetour); // special case: returns, damaged, consigned

    Mouvement enregistrerAjustement(Mouvement mouvement, String raisonAjustement); // corrections, casse, vol...

    List<Mouvement> getAllMouvementsByBoisson(Long boissonId);

    List<Mouvement> getAllMouvementsByLot(Long lotId);

    List<Mouvement> getAllMouvementsByUtilisateur(Long utilisateurId);
}
