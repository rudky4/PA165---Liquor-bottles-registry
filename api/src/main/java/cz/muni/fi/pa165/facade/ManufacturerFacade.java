package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.ManufacturerDTO;

import java.util.Collection;
import java.util.Date;

/**
 * @author Martin Sumera
 */
public interface ManufacturerFacade {

    void createManufacturer(ManufacturerDTO manufacturerDTO);

    boolean hasToxicProduction(Long id);

    boolean hasToxicProduction(ManufacturerDTO manufacturer);

    Collection<ManufacturerDTO> findAll();

    ManufacturerDTO findById(Long id);

    ManufacturerDTO findByName(String name);

}
