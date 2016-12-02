package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Manufacturer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Martin Sumera
 */
public interface ManufacturerDAO extends CrudRepository<Manufacturer, Long> {

    /**
     * Provide list of all existing manufacturers
     * @return all manufacturers
     */
    List<Manufacturer> findAll();

    Manufacturer findByName(String name);

}

