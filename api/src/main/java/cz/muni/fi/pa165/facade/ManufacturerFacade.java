package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.BottleTypeDTO;
import cz.muni.fi.pa165.dto.ManufacturerDTO;
import cz.muni.fi.pa165.dto.PersonDTO;
import java.util.List;

/**
 *
 * @author rk
 * @date 2016-11-22
 */
public interface ManufacturerFacade {
    void createManufacturer(ManufacturerDTO type);
    
    List<ManufacturerDTO> findAll();

    ManufacturerDTO findById(Long id);
    
    ManufacturerDTO findByName(String name);
    
    List<PersonDTO> findAllPersons();
    
    boolean isPersonInManufacturer(PersonDTO person);

    List<BottleTypeDTO> findAllTypesProduced();
    
    boolean isBottleTypeInPortfolio(BottleTypeDTO bottle);
}
