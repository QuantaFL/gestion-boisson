package org.gestion.boisson.features.boissons.services.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.gestion.boisson.features.boissons.entities.Boisson;
import org.gestion.boisson.features.boissons.repository.BoissonRepository;
import org.gestion.boisson.features.boissons.services.BoissonService;

@Named
@ApplicationScoped
@Slf4j
public class BoissonServiceImpl implements BoissonService {
    @Inject
    private BoissonRepository boissonRepository;

    @Override
    public Boisson ajouterBoisson(Boisson boisson) {
       try {
           Boisson boisson1 =  boissonRepository.save(boisson);
           log.info("Boisson enregistrée avec succès : {}", boisson);
            return boisson1 ;
       } catch (Exception e) {
           log.error("Erreur lors de l'enregistrement de la boisson : {}", boisson, e);
           throw e;
       }
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
