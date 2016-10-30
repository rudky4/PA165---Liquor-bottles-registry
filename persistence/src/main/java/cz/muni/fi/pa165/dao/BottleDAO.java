package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Bottle;

import java.util.Collection;

/**
 * @author mhajas
 */
public interface BottleDAO {

    /**
     * Create bottle in database
     * @param bottle object to create
     */
    void createBottle(Bottle bottle);

    /**
     * Get all bottles from database
     * @return Collection of bottles
     */
    Collection<Bottle> getAllBottles();

    /**
     * Get bottle with ID
     * @param id of bottle
     * @return Bottle
     */
    Bottle getBottleById(Long id);

    /**
     * Update new values for bottle
     * @param bottle with new values
     */
    void updateBottle(Bottle bottle);

    /**
     * Remove bottle
     * @param bottle to remove
     */
    void deleteBottle(Bottle bottle);
}

