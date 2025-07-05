package org.gestion.boisson.features.mouvements.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.gestion.boisson.features.mouvements.enums.TypeAjustement;
import org.gestion.boisson.features.mouvements.enums.TypeMouvement;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class MouvementDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("type")
    private TypeMouvement type;
    @JsonProperty("dateMouvement")
    private String dateMouvement;
    @JsonProperty("quantite")
    private int quantite;
    @JsonProperty("boissonId")
    private Long boissonId;
    @JsonProperty("boissonNom")
    private String boissonNom;
    @JsonProperty("lotId")
    private Long lotId;
    @JsonProperty("lotNumero")
    private String lotNumero;
    @JsonProperty("utilisateurId")
    private Long utilisateurId;
    @JsonProperty("utilisateurNom")
    private String utilisateurEmail;
    @JsonProperty("typeAjustement")
    private TypeAjustement typeAjustement;
    @JsonProperty("raison")
    private String raison;
}
