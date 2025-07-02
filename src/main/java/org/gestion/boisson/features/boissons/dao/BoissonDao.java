package org.gestion.boisson.features.boissons.dao;

import org.gestion.boisson.features.boissons.entities.Boisson;

import java.util.List;

public interface BoissonDao {
    /**
     * Retrieves a beverage by its name.
     *
     * @param nom the name of the beverage
     * @return the beverage entity, or null if not found
     */
    Boisson getByNom(String nom);

    /**
     * Saves a new beverage to the database.
     *
     * @param boisson the beverage entity to save
     * @return the saved beverage entity
     */
    Boisson save(Boisson boisson);

    /**
     * Updates an existing beverage in the database.
     *
     * @param boisson the beverage entity to update
     * @return the updated beverage entity
     */
    Boisson update(Boisson boisson);


    /**
     * Retrieves all beverages from the database.
     *
     * @return a list of all beverage entities
     */
    List<Boisson> getAll();
}
