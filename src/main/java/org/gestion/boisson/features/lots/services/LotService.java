package org.gestion.boisson.features.lots.services;

import org.gestion.boisson.features.lots.entities.Lot;
import org.gestion.boisson.features.mouvements.entities.Mouvement;

import java.util.List;

/**
 * Service for handling lot-related business logic.
 */
public interface LotService {
    /**
     * Adds a given quantity to a lot.
     * @param lotId The ID of the lot.
     * @param quantity The quantity to add.
     * @return A boolean indicating whether the quantity was successfully added.
     */
    boolean addQuantityToLot(Long lotId, int quantity);

    /**
     * Removes a given quantity from a lot.
     * @param lotId The ID of the lot.
     * @param quantity The quantity to remove.
     * @return A boolean indicating whether the quantity was successfully removed.
     */
    boolean removeQuantityFromLot(Long lotId, int quantity);

    /**
     * Checks if a lot is expired.
     * @param lotId The ID of the lot to check.
     * @return A boolean indicating whether the lot is expired.
     */
    boolean isExpired(Long lotId);

    /**
     * Checks if a lot is available.
     * @param lotId The ID of the lot to check.
     * @return A boolean indicating whether the lot is available.
     */
    boolean isLotAvailable(Long lotId);

    /**
     * Retrieves all lots for a given beverage.
     * @param boissonId The ID of the beverage.
     * @return A list of all lots for the given beverage.
     */
    List<Lot> getAllLotsByBoisson(Long boissonId);

    /**
     * Retrieves all lots for a given beverage, ordered by expiration date.
     * @param boissonId The ID of the beverage.
     * @return A list of all lots for the given beverage, ordered by expiration date.
     */
    List<Lot> getAllLotOrderByExpirationDate(Long boissonId);

    /**
     * Updates an existing lot.
     * @param lot The lot to update.
     * @return A boolean indicating whether the lot was successfully updated.
     */
    boolean updateLot(Lot lot);

    /**
     * Retrieves all available lots.
     * @return A list of all available lots.
     */
    List<Lot> getAllLotsDisponibles();

    /**
     * Retrieves all movements for a given beverage.
     * @param boissonId The ID of the beverage.
     * @return A list of all movements for the given beverage.
     */
    List<Mouvement> findByBoissonId(Long boissonId);

    /**
     * Retrieves all movements for a given lot.
     * @param lotId The ID of the lot.
     * @return A list of all movements for the given lot.
     */
    List<Mouvement> findByLotId(Long lotId);

    /**
     * Saves a new lot.
     * @param lot The lot to save.
     */
    void save(Lot lot);
}
