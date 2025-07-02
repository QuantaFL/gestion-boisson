package org.gestion.boisson.features.lots.services;

import org.gestion.boisson.features.lots.entities.Lot;

import java.util.List;

public interface LotService {
    boolean addQuantityToLot(Long lotId, int quantity);
    boolean removeQuantityFromLot(Long lotId, int quantity);
    boolean isExpired(Long lotId);
    boolean isLotAvailable(Long lotId);
    List<Lot> getAllLotsByBoisson(Long boissonId);
    List<Lot> getAllLotOrderByExpirationDate(Long boissonId);
}
