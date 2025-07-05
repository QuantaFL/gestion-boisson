package org.gestion.boisson.features.mouvements.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.gestion.boisson.features.boissons.entities.Boisson;
import org.gestion.boisson.features.lots.entities.Lot;
import org.gestion.boisson.features.mouvements.enums.TypeAjustement;
import org.gestion.boisson.features.mouvements.enums.TypeMouvement;
import org.gestion.boisson.features.utilisateurs.entities.Utilisateur;

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
    private String dateMouvement;

    @Min(0)
    @Column(updatable = false, columnDefinition = "integer CHECK (quantite >= 0)")
    private int quantite;

    @ManyToOne
    @JoinColumn(name = "boisson_id",updatable = false)
    private Boisson boisson;

    @ManyToOne
    @JoinColumn(name = "lot_id",updatable = false)
    private Lot lot;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id",updatable = false)
    private Utilisateur utilisateur;

    @Enumerated(EnumType.STRING)
    private TypeAjustement typeAjustement;

    public String raison;

    @PrePersist
    public void prePersist() {
        this.dateMouvement = String.valueOf(LocalDateTime.now());
    }
}
