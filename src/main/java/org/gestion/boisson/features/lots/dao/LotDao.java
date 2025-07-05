package org.gestion.boisson.features.lots.dao;

import org.gestion.boisson.features.lots.entities.Lot;

import java.util.Arrays;
import java.util.List;

public interface LotDao {
    Lot saveLot(Lot lot);
    Lot getById(Long id);
    Lot updateLot(Lot lot);
    List<Lot> getAllLots();
    List<Lot> getLotsDisponibles(Long boissonId);
}
