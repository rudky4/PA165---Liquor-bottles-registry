package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.BottleDTO;
import cz.muni.fi.pa165.dto.BottleTypeDTO;
import cz.muni.fi.pa165.entity.Bottle;
import cz.muni.fi.pa165.entity.BottleType;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.BottleService;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * @author mhajas
 */
public class BottleFacadeImpl implements BottleFacade {

    @Inject
    private BottleService bottleService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public void createBottle(BottleDTO bottle) {
        bottleService.createBottle(beanMappingService.mapTo(bottle, Bottle.class));
    }

    @Override
    public List<BottleDTO> findAll() {
        return beanMappingService.mapTo(bottleService.findAll(), BottleDTO.class);
    }

    @Override
    public List<BottleDTO> getBottlesFromDate(Date date) {
        return beanMappingService.mapTo(bottleService.getBottlesFromDate(date), BottleDTO.class);
    }

    @Override
    public List<BottleDTO> getAllToxicBottles() {
        return beanMappingService.mapTo(bottleService.getAllToxicBottles(), BottleDTO.class);
    }

    @Override
    public List<BottleDTO> findByBottleType(BottleTypeDTO type) {
        return beanMappingService.mapTo(bottleService.findByBottleType(beanMappingService.mapTo(type, BottleType.class)), BottleDTO.class);
    }

    @Override
    public boolean isToxic(Long id) {
        return bottleService.isToxic(id);
    }

    @Override
    public boolean isToxic(BottleDTO bottle) {
        return bottleService.isToxic(beanMappingService.mapTo(bottle, Bottle.class));
    }

    @Override
    public void setToxic(Long id) {
        bottleService.setToxic(id);
    }

    @Override
    public void setToxic(BottleDTO bottle) {
        bottleService.setToxic(beanMappingService.mapTo(bottle, Bottle.class));
    }

    @Override
    public BottleDTO findById(Long id) {
        return beanMappingService.mapTo(bottleService.findById(id), BottleDTO.class);
    }

    @Override
    public BottleDTO findByStickerId(String id) {
        return beanMappingService.mapTo(bottleService.findByStickerId(id), BottleDTO.class);

    }
}
