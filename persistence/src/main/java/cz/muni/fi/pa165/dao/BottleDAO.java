package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Bottle;

import java.util.Collection;

/**
 * @author mhajas
 */
public interface BottleDAO {
    void createBottle(Bottle bottle);

    Collection<Bottle> getAllBottles();

    Bottle getBottleById(Long id);

    void updateBottle(Bottle bottle);

    void deleteBottle(Bottle bottle);
}

