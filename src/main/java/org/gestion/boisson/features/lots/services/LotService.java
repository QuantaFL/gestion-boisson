package org.gestion.boisson.features.lots.services;

import org.gestion.boisson.features.lots.entities.Lot;
import org.gestion.boisson.features.mouvements.entities.Mouvement;

import java.util.List;

public interface LotService {
    boolean addQuantityToLot(Long lotId, int quantity);
    boolean removeQuantityFromLot(Long lotId, int quantity);
    boolean isExpired(Long lotId);
    boolean isLotAvailable(Long lotId);
    List<Lot> getAllLotsByBoisson(Long boissonId);
    List<Lot> getAllLotOrderByExpirationDate(Long boissonId);
    boolean updateLot(Lot lot);
    List<Lot> getAllLotsDisponibles();
    List<Mouvement> findByBoissonId(Long boissonId);
    List<Mouvement> findByLotId(Long lotId);

    void save(Lot lot);
}
