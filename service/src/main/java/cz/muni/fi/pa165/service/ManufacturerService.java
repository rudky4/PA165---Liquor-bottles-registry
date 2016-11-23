package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dto.ManufacturerDTO;
import cz.muni.fi.pa165.entity.Manufacturer;

import java.util.Collection;
import java.util.Date;

/**
 * @author Martin Sumera
 */
public interface ManufacturerService {

    void createManufacturer(Manufacturer manufacturer);

    void markAllProducedBottlesSinceDateAsToxic(Manufacturer manufacturer, Date date);

    Collection<Manufacturer> findAll();

    Manufacturer findById(Long id);

    Manufacturer findByName(String name);

    boolean hasToxicProduction(Long id);

    boolean hasToxicProduction(Manufacturer manufacturer);

}
