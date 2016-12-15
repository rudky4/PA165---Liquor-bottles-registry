package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.ManufacturerDTO;

import java.util.Date;
import java.util.List;

/**
 * @author Martin Sumera
 */
public interface ManufacturerFacade {

    void createManufacturer(ManufacturerDTO manufacturerDTO);

    void markAllProducedBottlesSinceDateAsToxic(ManufacturerDTO manufacturer, Date date);

    boolean hasToxicProduction(Long id);

    boolean hasToxicProduction(ManufacturerDTO manufacturer);

    List<ManufacturerDTO> findAll();

    ManufacturerDTO findById(Long id);

    ManufacturerDTO findByName(String name);
}
