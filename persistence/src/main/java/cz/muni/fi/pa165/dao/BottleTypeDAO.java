package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.BottleType;

import java.util.Collection;

/**
 * @author mhajas
 */
public interface BottleTypeDAO {
    /**
     * Create new bottle type
      * @param bottleType new type to create
     */
    void createBottleType(BottleType bottleType);

    /**
     * Get all bottle types from database
     * @return Collection of all types
     */
    Collection<BottleType> getAllBottleTypes();

    /**
     * Get bottle type by id
     * @param id of bottle type to find
     * @return BotttleType
     */
    BottleType getBottleTypeById(Long id);

    /**
     * Update bottle type to new values
     * @param bottleType object with new values
     */
    void updateBottleType(BottleType bottleType);

    /**
     * Removes bottle type from database
     * @param bottleType to delete
     */
    void deleteBottleType(BottleType bottleType);
}

