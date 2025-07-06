package org.gestion.boisson.features.boissons.services.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.extern.slf4j.Slf4j;
import org.gestion.boisson.features.boissons.entities.Boisson;
import org.gestion.boisson.features.boissons.repository.BoissonRepository;
import org.gestion.boisson.features.boissons.services.BoissonService;

import java.util.List;

/**
 * Implementation of the BoissonService interface.
 * This class contains the business logic for managing beverages.
 */
@Named
@ApplicationScoped
@Slf4j
public class BoissonServiceImpl implements BoissonService {
    @Inject
    private BoissonRepository boissonRepository;

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supprimerBoisson(Long id) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boisson getBoissonDetails(String name) {
        try {
            Boisson boisson = boissonRepository.getByNom(name.trim());
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

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Boisson> getAllBoissons() {
        try {
            List<Boisson> boissons = boissonRepository.getAll();
            log.info("Récupération de toutes les boissons : {} éléments", boissons.size());
            return boissons;
        } catch (Exception e) {
            log.error("Erreur lors de la récupération de toutes les boissons", e);
            throw e;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Boisson> getAllBoissonsActives() {
        try {
            List<Boisson> boissons = boissonRepository.getAll()
                    .stream()
                    .filter(Boisson::isActive)
                    .toList();
            log.info("Récupération des boissons actives : {} éléments", boissons.size());
            return boissons;
        } catch (Exception e) {
            log.error("Erreur lors de la récupération des boissons actives", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Boisson> getAllBoissonsInactives() {
        try {
            List<Boisson> boissons = boissonRepository.getAll()
                    .stream()
                    .filter(boisson -> !boisson.isActive())
                    .toList();
            log.info("Récupération des boissons inactives : {} éléments", boissons.size());
            return boissons;
        } catch (Exception e) {
            log.error("Erreur lors de la récupération des boissons inactives", e);
            throw new RuntimeException(e);
        }
    }
}
