package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Manufacturer;

import java.util.Collection;

/**
 * @author Martin Sumera
 */
public interface ManufacturerService {

    void createManufacturer(Manufacturer manufacturer);

    Collection<Manufacturer> findAll();

    Manufacturer findById(Long id);

    Manufacturer findByName(String name);

    boolean hasToxicProduction(Long id);

    boolean hasToxicProduction(Manufacturer manufacturer);

}
