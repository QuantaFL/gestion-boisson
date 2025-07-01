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
@Table(name = "mouvements")
public class Mouvement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(updatable = false)
    private TypeMouvement type;

    @Column(updatable = false)
    private LocalDateTime dateMouvement;

    private int quantite;

    @ManyToOne
    @JoinColumn(name = "boisson_id")
    private Boisson boisson;

    @ManyToOne
    @JoinColumn(name = "lot_id")
    private Lot lot;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @Enumerated(EnumType.STRING)
    private TypeAjustement typeAjustement;

    @PrePersist
    public void prePersist() {
        this.dateMouvement = LocalDateTime.now();
    }
}
