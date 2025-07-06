package org.gestion.boisson.features.lots.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.gestion.boisson.features.boissons.entities.Boisson;

import java.time.LocalDateTime;

/**
 * Represents a lot of a beverage.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@NamedQueries({
        @NamedQuery(name = "Lot.findAll", query = "SELECT l FROM Lot l"
        ),
        @NamedQuery(name = "Lot.findByNumeroLot", query = "SELECT l FROM Lot l WHERE l.numeroLot = :numeroLot"
        ),
        @NamedQuery(name = "Lot.findDisponiblesByBoisson", query = "SELECT l FROM Lot l WHERE l.boisson.id = :boissonId AND l.vendable = true AND l.quantiteActuelle > 0"
        ),
        @NamedQuery(name = "Lot.findExpiringBefore", query = "SELECT l FROM Lot l WHERE l.datePeremption < :limitDate"
        ),
        @NamedQuery(name = "Lot.findByBoissonBetweenDates", query = "SELECT l FROM Lot l WHERE l.boisson.id = :boissonId AND l.dateEntree BETWEEN :startDate AND :endDate"
        ),
        @NamedQuery(name = "Lot.countByBoisson", query = "SELECT COUNT(l) FROM Lot l WHERE l.boisson.id = :boissonId"
        )
})
@Table(name = "lots")
public class Lot {
    /**
     * The unique identifier for the lot.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    /**
     * The unique lot number.
     */
    @Column(unique = true, updatable = false,nullable = false)
    private String numeroLot;

    /**
     * The initial quantity of the lot.
     */
    @Min(0)
    @Column(name = "quantite_initial", updatable = false, columnDefinition = "integer CHECK (quantite_initial >= 0)")
    private int quantiteInitial;

    /**
     * The current available quantity of the lot.
     */
    @Min(0)
    @Column(name = "quantite_actuelle", columnDefinition = "integer CHECK (quantite_actuelle >= 0)")
    private int quantiteActuelle;

    /**
     * The entry date of the lot into the stock.
     */
    @Column(updatable = false)
    private LocalDateTime dateEntree;

    /**
     * The expiration date of the lot.
     */
    @Column(updatable = false)
    private LocalDateTime datePeremption;

    /**
     * The associated beverage (boisson) for the lot.
     */
    @ManyToOne
    @JoinColumn(name = "boisson_id",updatable = false)
    private Boisson boisson;

    /**
     * Whether the lot is available for sale.
     */
    private boolean vendable;

    /**
     * Sets the entry date and initial quantity before persisting the entity.
     */
    @PrePersist
    public void prePersist() {
        this.dateEntree = LocalDateTime.now();
        this.quantiteActuelle = this.quantiteInitial;
    }

    /**
     * Validates that the current quantity is not greater than the initial quantity before updating the entity.
     */
    @PreUpdate
    public void validateQuantities() {
        if (quantiteActuelle > quantiteInitial) {
            throw new IllegalArgumentException("quantiteActuelle cannot be greater than quantiteInitial");
        }
    }
}
