package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Manufacturer;

import java.util.Collection;

/**
 * Created by martin on 26/10/16.
 */
public interface ManufacturerDAO {

    void createManufacturer(Manufacturer Manufacturer);

    Collection<Manufacturer> getAllManufacturers();

    Manufacturer getManufacturerById(Long id);

    void updateManufacturer(Manufacturer Manufacturer);

    void deleteManufacturer(Manufacturer Manufacturer);

}
