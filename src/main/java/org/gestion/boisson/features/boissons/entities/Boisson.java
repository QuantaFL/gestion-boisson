package org.gestion.boisson.features.boissons.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Represents a beverage in the application.
 * This entity is mapped to the "boissons" table in the database.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Table(name = "boissons")
@NamedQueries({
        @NamedQuery(name = "Boisson.findAll", query = "SELECT b FROM Boisson b"),
        @NamedQuery(name = "Boisson.findByNom", query = "SELECT b FROM Boisson b WHERE LOWER(b.nom) = LOWER(:nom)"),
        @NamedQuery(name = "Boisson.findByActive", query = "SELECT b FROM Boisson b WHERE b.isActive = :active"),
        @NamedQuery(name = "Boisson.findByPrixMoinsQue", query = "SELECT b FROM Boisson b WHERE b.prixUnitaire < :prix"),
        @NamedQuery(name = "Boisson.findBySeuilMoinsQue", query = "SELECT b FROM Boisson b WHERE b.seuil < :seuil"),
        @NamedQuery(name = "Boisson.findByVolume", query = "SELECT b FROM Boisson b WHERE b.volume = :volume"),
        @NamedQuery(name = "Boisson.findBetweenPrix", query = "SELECT b FROM Boisson b WHERE b.prixUnitaire BETWEEN :min AND :max"),
        @NamedQuery(name = "Boisson.findRecentes", query = "SELECT b FROM Boisson b WHERE b.createdAt >= :date")
})
public class Boisson{

    /**
     * The unique identifier for the beverage.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * The name of the beverage. Cannot be null or updated.
     */
    @Column(nullable = false, updatable = false)
    private String nom;

    /**
     * A unique description of the beverage. Cannot be updated.
     */
    @Column(unique = true, updatable = false)
    private String description;

    /**
     * The volume of the beverage.
     */
    private Double volume;

    /**
     * The unit of measurement for the volume (e.g., "L", "mL").
     */
    private String unite;

    /**
     * The unit price of the beverage.
     */
    private Double prixUnitaire;

    /**
     * The stock alert threshold for the beverage.
     */
    private int seuil;

    /**
     * Indicates whether the beverage is currently active and available.
     */
    private boolean isActive;
    /**
     * The timestamp when the beverage was created.
     */
    private LocalDateTime createdAt;
    /**
     * The timestamp when the beverage was last updated.
     */
    private LocalDateTime updatedAt;
    /**
     * Sets the creation and update timestamps before the entity is persisted.
     */
    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }
    /**
     * Sets the update timestamp before the entity is updated.
     */
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
