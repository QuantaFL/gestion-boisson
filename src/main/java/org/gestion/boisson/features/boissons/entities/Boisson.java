package org.gestion.boisson.features.boissons.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, updatable = false)
    private String nom;

    @Column(unique = true, updatable = false)
    private String description;

    private Double volume;

    private String unite;

    private Double prixUnitaire;

    private int seuil;

    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
