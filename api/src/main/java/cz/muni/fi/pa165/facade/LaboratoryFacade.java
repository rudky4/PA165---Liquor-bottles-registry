package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.BottleDTO;
import cz.muni.fi.pa165.dto.LaboratoryDTO;
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
    
    List<BottleDTO> getBottlesToCheck(Long id);
}
