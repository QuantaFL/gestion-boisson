package org.gestion.boisson.features.boissons.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Data Transfer Object for beverage data.
 * Used to transfer beverage information between the client and the server.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class BoissonDto {

    /**
     * The unique identifier for the beverage.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The name of the beverage.
     */
    @JsonProperty("nom")
    private String nom;

    /**
     * A description of the beverage.
     */
    @JsonProperty("description")
    private String description;

    /**
     * The volume of the beverage.
     */
    @JsonProperty("volume")
    private Double volume;

    /**
     * The unit of measurement for the volume (e.g., "L", "mL").
     */
    @JsonProperty("unite")
    private String unite;

    /**
     * The unit price of the beverage.
     */
    @JsonProperty("prixUnitaire")
    private Double prixUnitaire;

    /**
     * The stock alert threshold for the beverage.
     */
    @JsonProperty("seuil")
    private Integer seuil;

    /**
     * Indicates whether the beverage is currently active and available.
     */
    @JsonProperty("isActive")
    private Boolean isActive;

    /**
     * The timestamp when the beverage was created.
     */
    @JsonProperty("createdAt")
    private String createdAt;

    /**
     * The timestamp when the beverage was last updated.
     */
    @JsonProperty("updatedAt")
    private String updatedAt;
}