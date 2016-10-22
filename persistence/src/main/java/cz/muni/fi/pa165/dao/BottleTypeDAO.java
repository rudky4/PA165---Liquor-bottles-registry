package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.BottleType;

import java.util.Collection;

/**
 * @author mhajas
 */
public interface BottleTypeDAO {
    void createBottleType(BottleType bottleType);

    Collection<BottleType> getAllBottleTypes();

    BottleType getBottleTypeById(Long id);

    void updateBottleType(BottleType bottleType);

    void deleteBottleType(BottleType bottleType);
}

