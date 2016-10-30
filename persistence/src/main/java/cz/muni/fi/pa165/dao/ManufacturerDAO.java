package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Manufacturer;

import java.util.Collection;

/**
 * Created by martin on 26/10/16.
 */
public interface ManufacturerDAO {

    /**
     * Create new manufacturer
     * @param Manufacturer
     */
    void createManufacturer(Manufacturer Manufacturer);

    /**
     * Provide collection of all existing manufacturers
     * @return
     */
    Collection<Manufacturer> getAllManufacturers();

    /**
     * Provide specific manufacturer with given id
     * @param id
     * @return
     */
    Manufacturer getManufacturerById(Long id);

    /**
     * Update existing manufacturer
     * @param Manufacturer
     */
    void updateManufacturer(Manufacturer Manufacturer);

    /**
     * Delete existing manufacturer
     * @param Manufacturer
     */
    void deleteManufacturer(Manufacturer Manufacturer);

}
