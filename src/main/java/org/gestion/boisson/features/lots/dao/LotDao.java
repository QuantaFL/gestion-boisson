package org.gestion.boisson.features.lots.dao;

import org.gestion.boisson.features.lots.entities.Lot;

import java.util.List;

/**
 * Data Access Object for Lot entities.
 */
public interface LotDao {
    /**
     * Saves a lot to the database.
     * @param lot The lot to save.
     * @return The saved lot.
     */
    Lot saveLot(Lot lot);

    /**
     * Retrieves a lot by its ID.
     * @param id The ID of the lot to retrieve.
     * @return The lot with the given ID, or null if not found.
     */
    Lot getById(Long id);

    /**
     * Updates a lot in the database.
     * @param lot The lot to update.
     * @return The updated lot.
     */
    Lot updateLot(Lot lot);

    /**
     * Retrieves all lots from the database.
     * @return A list of all lots.
     */
    List<Lot> getAllLots();

    /**
     * Retrieves all lots for a given beverage.
     * @param boissonId The ID of the beverage.
     * @return A list of all lots for the given beverage.
     */
    List<Lot> getLotsByBoissonId(Long boissonId);

    /**
     * Retrieves a lot by its number.
     * @param numero The number of the lot to retrieve.
     * @return The lot with the given number, or null if not found.
     */
    Lot getByNumero(String numero);
}
