package org.gestion.boisson.features.boissons.services;

import org.gestion.boisson.features.boissons.entities.Boisson;

import java.util.List;

public interface BoissonService {
    Boisson ajouterBoisson(Boisson boisson);
    Boisson modifierBoisson(Boisson boisson);
    boolean supprimerBoisson(Long id);
    Boisson getBoissonDetails(String name);
    boolean boissonExists(String name);
    List<Boisson> getAllBoissons();
    List<Boisson> getAllBoissonsActives();
}
