package org.gestion.boisson.features.boissons.database.seeder;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.gestion.boisson.features.boissons.entities.Boisson;
import org.gestion.boisson.features.boissons.services.BoissonService;

import java.time.LocalDateTime;

/**
 * Seeds the database with initial beverage data.
 * This class is executed at application startup.
 */
@Slf4j
@ApplicationScoped
public class BoissonSeeder {
    @Inject
    BoissonService boissonService;

    /**
     * Default constructor.
     */
    public BoissonSeeder() {
        log.debug("🔥 BoissonSeeder: Constructeur appelé");
    }

    /**
     * Populates the database with initial beverage data if no beverages exist.
     * This method is called after the bean has been constructed.
     */
    @PostConstruct
    public void seed() {
        log.debug("🔥 BoissonSeeder: seed() appelé");
        if (boissonService.getAllBoissons().isEmpty()) {
            log.debug("🔥 BoissonSeeder: seed() à commencer");
            boissonService.ajouterBoisson(Boisson.builder()
                    .nom("Coca-Cola")
                    .description("Soda classique")
                    .volume(1.5)
                    .unite("L")
                    .prixUnitaire(1200.0)
                    .seuil(10)
                    .isActive(true)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build());
            boissonService.ajouterBoisson(Boisson.builder()
                    .nom("Fanta")
                    .description("Soda orange")
                    .volume(1.0)
                    .unite("L")
                    .prixUnitaire(1000.0)
                    .seuil(8)
                    .isActive(true)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build());
            boissonService.ajouterBoisson(Boisson.builder()
                    .nom("Eau Minérale")
                    .description("Eau naturelle")
                    .volume(0.5)
                    .unite("L")
                    .prixUnitaire(500.0)
                    .seuil(20)
                    .isActive(true)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build());
        }else {
            log.debug("🔥 BoissonSeeder: Aucune donnée à insérer, la table n'est pas vide.");
        }
    }
}

