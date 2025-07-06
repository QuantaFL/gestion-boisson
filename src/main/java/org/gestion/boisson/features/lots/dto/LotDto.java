package org.gestion.boisson.features.lots.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.gestion.boisson.features.boissons.dto.BoissonDto;

/**
 * Data Transfer Object for lot data.
 * Used to transfer lot information between the client and the server.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class LotDto {

    /**
     * The unique identifier for the lot.
     */
    @JsonProperty("id")
    private Long id;

    /**
     * The unique lot number.
     */
    @JsonProperty("numeroLot")
    private String numeroLot;

    /**
     * The initial quantity of the lot.
     */
    @JsonProperty("quantiteInitiale")
    private Integer quantiteInitial;

    /**
     * The current available quantity of the lot.
     */
    @JsonProperty("quantiteActuelle")
    private Integer quantiteActuelle;

    /**
     * The entry date of the lot into the stock.
     */
    @JsonProperty("dateEntree")
    private String dateEntree;

    /**
     * The expiration date of the lot.
     */
    @JsonProperty("datePeremption")
    private String datePeremption;

    /**
     * Whether the lot is available for sale.
     */
    @JsonProperty("vendable")
    private Boolean vendable;

    /**
     * The associated beverage (boisson) for the lot.
     */
    @JsonProperty("boisson")
    private BoissonDto boisson;
}

