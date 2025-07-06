package org.gestion.boisson.features.boissons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BoissonDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("nom")
    private String nom;

    @JsonProperty("description")
    private String description;

    @JsonProperty("volume")
    private Double volume;

    @JsonProperty("unite")
    private String unite;

    @JsonProperty("prixUnitaire")
    private Double prixUnitaire;

    @JsonProperty("seuil")
    private Integer seuil;

    @JsonProperty("isActive")
    private Boolean isActive;

    @JsonProperty("createdAt")
    private String createdAt;

    @JsonProperty("updatedAt")
    private String updatedAt;
}