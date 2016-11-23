package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.BottleDTO;
import cz.muni.fi.pa165.dto.LaboratoryDTO;
import cz.muni.fi.pa165.dto.PersonDTO;
import java.util.List;

/**
 *
 * @author rk
 * @date 2016-11-22
 */
public interface LaboratoryFacade {
    void createLaboratory(LaboratoryDTO laboratory);
    
    List<LaboratoryDTO> findAll();

    LaboratoryDTO findById(Long id);
    
    LaboratoryDTO findByName(String name);
    
    List<PersonDTO> findAllPersonsInLaboratory();
    
    boolean isPersonInLaboratory(PersonDTO person);

    List<BottleDTO> findAllBottlesToCheck();
    
    boolean isBottleToBeChecked(BottleDTO bottle);
}
