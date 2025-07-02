package org.gestion.boisson.features.boissons.services;

import org.gestion.boisson.features.boissons.entities.Boisson;

public interface BoissonService {
    void ajouterBoisson(Boisson boisson);
    void modifierBoisson(Boisson boisson);
    void supprimerBoisson(Long id);
    Boisson getBoissonDetails(String name);
    boolean boissonExists(String name);
}
