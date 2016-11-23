package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.LaboratoryDTO;
import cz.muni.fi.pa165.entity.Laboratory;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.LaboratoryService;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author rk
 * @date 2016-11-23
 */
public class LaboratoryFacadeImpl implements LaboratoryFacade {
    @Inject
    private LaboratoryService laboratoryService;

    @Inject
    private BeanMappingService beanMappingService;
    
    
    @Override
    public void createLaboratory(LaboratoryDTO laboratory) {
        laboratoryService.createLaboratory(beanMappingService.mapTo(laboratory, Laboratory.class));
    }

    @Override
    public List<LaboratoryDTO> findAll() {
         return beanMappingService.mapTo(laboratoryService.findAll(), LaboratoryDTO.class);
    }

    @Override
    public LaboratoryDTO findById(Long id) {
         return beanMappingService.mapTo(laboratoryService.findById(id), LaboratoryDTO.class);     
    }

    @Override
    public LaboratoryDTO findByName(String name) {
         return beanMappingService.mapTo(laboratoryService.findByName(name), LaboratoryDTO.class);
    }    
}
