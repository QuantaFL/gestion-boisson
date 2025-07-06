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
           log.info("Boisson  ajouté avec succès : {}", boisson);
            return boisson1 ;
       } catch (Exception e) {
           log.error("Erreur lors de l'enregistrement de la boisson : {}", boisson, e);
           throw e;
       }
    }

    @Override
    public Boisson modifierBoisson(Boisson boisson) {
        try {
            Boisson boisson1 =  boissonRepository.save(boisson);
            log.info("Boisson modifiée avec succès : {}", boisson);
            return boisson1 ;
        } catch (Exception e) {
            log.error("Erreur lors de la modification  de la boisson : {}", boisson, e);
            throw e;
        }
    }

    @Override
    public boolean supprimerBoisson(Long id) {
        return false;
    }

    @Override
    public Boisson getBoissonDetails(String name) {
        try {
            Boisson boisson = boissonRepository.getByNom(name);
            if (boisson != null) {
                log.info("Détails de la boisson récupérés avec succès : {}", boisson);
                return boisson;
            } else {
                log.warn("Aucune boisson trouvée avec le nom : {}", name);
                return null;
            }
        } catch (Exception e) {
            log.error("Erreur lors de la récupération des détails de la boisson : {}", name, e);
            throw e;
        }
    }

    @Override
    public boolean boissonExists(String name) {
        try {
            Boisson boisson = boissonRepository.getByNom(name);
            boolean exists = boisson != null;
            log.info("Vérification de l'existence de la boisson '{}': {}", name, exists);
            return exists;
        } catch (Exception e) {
            log.error("Erreur lors de la vérification de l'existence de la boisson : {}", name, e);
            throw e;
        }
    }
}
