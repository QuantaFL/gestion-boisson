package org.gestion.boisson.features.stocks.entities;


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
public class Stock{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "boisson_id",updatable = false)
    private Boisson boisson;
    @Min(0)
    @Column(name = "quantite_totale",updatable = false, columnDefinition = "integer CHECK (quantite_totale >= 0)")
    private int quantiteTotale;

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
