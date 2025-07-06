package org.gestion.boisson.features.boissons.services;

import org.gestion.boisson.features.boissons.entities.Boisson;

import java.util.List;

/**
 * Service interface for managing beverages.
 * Defines the business logic for beverage operations.
 */
public interface BoissonService {
    /**
     * Adds a new beverage.
     *
     * @param boisson the beverage to add
     * @return the added beverage
     */
    Boisson ajouterBoisson(Boisson boisson);

    /**
     * Modifies an existing beverage.
     *
     * @param boisson the beverage to modify
     * @return the modified beverage
     */
    Boisson modifierBoisson(Boisson boisson);

    /**
     * Deletes a beverage by its ID.
     *
     * @param id the ID of the beverage to delete
     * @return true if the deletion was successful, false otherwise
     */
    boolean supprimerBoisson(Long id);

    /**
     * Retrieves the details of a beverage by its name.
     *
     * @param name the name of the beverage
     * @return the beverage details, or null if not found
     */
    Boisson getBoissonDetails(String name);

    /**
     * Checks if a beverage with the given name exists.
     *
     * @param name the name of the beverage
     * @return true if the beverage exists, false otherwise
     */
    boolean boissonExists(String name);

    /**
     * Retrieves a list of all beverages.
     *
     * @return a list of all beverages
     */
    List<Boisson> getAllBoissons();

    /**
     * Retrieves a list of all active beverages.
     *
     * @return a list of all active beverages
     */
    List<Boisson> getAllBoissonsActives();

    /**
     * Retrieves a list of all inactive beverages.
     *
     * @return a list of all inactive beverages
     */
    List<Boisson> getAllBoissonsInactives();
}
