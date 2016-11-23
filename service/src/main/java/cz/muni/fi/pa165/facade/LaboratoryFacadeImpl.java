package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.BottleDTO;
import cz.muni.fi.pa165.dto.LaboratoryDTO;
import cz.muni.fi.pa165.dto.PersonDTO;
import cz.muni.fi.pa165.entity.Bottle;
import cz.muni.fi.pa165.entity.Laboratory;
import cz.muni.fi.pa165.entity.Person;
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

    @Override
    public List<PersonDTO> findAllPersonsInLaboratory() {
        return beanMappingService.mapTo(laboratoryService.findAllPersonsInLaboratory(), PersonDTO.class);       
    }

    @Override
    public boolean isPersonInLaboratory(PersonDTO person) {
        return laboratoryService.isPersonInLaboratory(beanMappingService.mapTo(person, Person.class)); 
    }

    @Override
    public List<BottleDTO> findAllBottlesToCheck() {
        return beanMappingService.mapTo(laboratoryService.findAllBottlesToCheck(), BottleDTO.class);
    }

    @Override
    public boolean isBottleToBeChecked(BottleDTO bottle) {
        return laboratoryService.isBottleToBeChecked(beanMappingService.mapTo(bottle, Bottle.class));
    }    
}
