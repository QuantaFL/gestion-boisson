package org.gestion.boisson.features.lots.repository;

import org.gestion.boisson.features.lots.dao.LotDao;
import org.gestion.boisson.features.lots.entities.Lot;

import java.util.List;

public class LotRepository  implements LotDao {

    @Override
    public Lot saveLot(Lot lot) {
        return null;
    }

    @Override
    public Lot getById(Long id) {
        return null;
    }

    @Override
    public Lot updateLot(Lot lot) {
        return null;
    }

    @Override
    public List<Lot> getAllLots() {
        return List.of();
    }

    @Override
    public List<Lot> getLotsByBoissonId(Long boissonId) {
        return List.of();
    }

    @Override
    public Lot getByNumero(String numero) {
        return null;
    }
}
