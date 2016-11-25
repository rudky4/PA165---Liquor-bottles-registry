package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.LaboratoryDTO;
import cz.muni.fi.pa165.entity.Laboratory;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.LaboratoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author rk
 * @date 2016-11-23
 */
@Service
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
        List<Laboratory> labs = laboratoryService.findAll();
        return labs == null ? null : beanMappingService.mapTo(labs, LaboratoryDTO.class);
    }

    @Override
    public LaboratoryDTO findById(Long id) {
        Laboratory lab = laboratoryService.findById(id);
        return lab == null ? null : beanMappingService.mapTo(lab, LaboratoryDTO.class);       
    }

    @Override
    public LaboratoryDTO findByName(String name) {
        Laboratory lab = laboratoryService.findByName(name);
        return lab == null ? null : beanMappingService.mapTo(lab, LaboratoryDTO.class);  
    }    
}
