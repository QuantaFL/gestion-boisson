package org.gestion.boisson.features.boissons.repository;

import org.gestion.boisson.features.boissons.dao.BoissonDao;
import org.gestion.boisson.features.boissons.entities.Boisson;

import java.util.List;

public class BoissonRepository implements BoissonDao {
    @Override
    public Boisson getByNom(String nom) {
        return null;
    }

    @Override
    public Boisson save(Boisson boisson) {
        return null;
    }

    @Override
    public Boisson update(Boisson boisson) {
        return null;
    }

    @Override
    public List<Boisson> getAll() {
        return List.of();
    }
}
