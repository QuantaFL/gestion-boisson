package org.gestion.boisson.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Table(name = "lots")
public class Lot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(unique = true, updatable = false)
    private String numeroLot;

    @Column(updatable = false)
    private int quantiteInitial;

    private int quantiteActuelle;

    @Column(updatable = false)
    private LocalDateTime dateEntree;

    @Column(updatable = false)
    private LocalDateTime datePeremption;

    @ManyToOne
    @JoinColumn(name = "boisson_id",updatable = false)
    private Boisson boisson;

    @PrePersist
    public void prePersist() {
        this.dateEntree = LocalDateTime.now();
        this.quantiteActuelle = this.quantiteInitial;
    }
}
