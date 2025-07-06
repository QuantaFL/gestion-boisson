package org.gestion.boisson.features.lots.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.gestion.boisson.features.boissons.entities.Boisson;

import java.time.LocalDateTime;

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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(unique = true, updatable = false,nullable = false)
    private String numeroLot;

    @Min(0)
    @Column(name = "quantite_initial", updatable = false, columnDefinition = "integer CHECK (quantite_initial >= 0)")
    private int quantiteInitial;

    @Min(0)
    @Column(name = "quantite_actuelle", columnDefinition = "integer CHECK (quantite_actuelle >= 0)")
    private int quantiteActuelle;

    @Column(updatable = false)
    private LocalDateTime dateEntree;

    @Column(updatable = false)
    private LocalDateTime datePeremption;

    @ManyToOne
    @JoinColumn(name = "boisson_id",updatable = false)
    private Boisson boisson;

    private boolean vendable;

    @PrePersist
    public void prePersist() {
        this.dateEntree = LocalDateTime.now();
        this.quantiteActuelle = this.quantiteInitial;
    }

    @PreUpdate
    public void validateQuantities() {
        if (quantiteActuelle > quantiteInitial) {
            throw new IllegalArgumentException("quantiteActuelle cannot be greater than quantiteInitial");
        }
    }
}
