package org.gestion.boisson.features.lots.dao;

import org.gestion.boisson.features.lots.entities.Lot;

public interface LotDao {
    Lot saveLot(Lot lot);
    Lot getById(Long id);
    Lot updateLot(Lot lot);
}
