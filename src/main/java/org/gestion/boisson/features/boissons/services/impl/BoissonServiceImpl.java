package org.gestion.boisson.features.boissons.services.impl;

import org.gestion.boisson.features.boissons.entities.Boisson;
import org.gestion.boisson.features.boissons.services.BoissonService;

public class BoissonServiceImpl implements BoissonService {

    @Override
    public Boisson ajouterBoisson(Boisson boisson) {
        return null;
    }

    @Override
    public Boisson modifierBoisson(Boisson boisson) {
        return null;
    }

    @Override
    public boolean supprimerBoisson(Long id) {
        return false;
    }

    @Override
    public Boisson getBoissonDetails(String name) {
        return null;
    }

    @Override
    public boolean boissonExists(String name) {
        return false;
    }
}
