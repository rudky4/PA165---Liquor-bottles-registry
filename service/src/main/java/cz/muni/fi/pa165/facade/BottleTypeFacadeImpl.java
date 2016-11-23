package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.BottleTypeDTO;
import cz.muni.fi.pa165.entity.BottleType;
import cz.muni.fi.pa165.enums.AlcoholType;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.BottleTypeService;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author mhajas
 */
public class BottleTypeFacadeImpl implements BottleTypeFacade {

    @Inject
    private BottleTypeService bottleTypeService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public void createBottleType(BottleTypeDTO type) {
        bottleTypeService.createBottleType(beanMappingService.mapTo(type, BottleType.class));
    }

    @Override
    public List<BottleTypeDTO> findAll() {
        return beanMappingService.mapTo(bottleTypeService.findAll(), BottleTypeDTO.class);
    }

    @Override
    public List<BottleTypeDTO> findByAlcoholType(AlcoholType type) {
        return beanMappingService.mapTo(bottleTypeService.findByAlcoholType(type), BottleTypeDTO.class);
    }

    @Override
    public List<BottleTypeDTO> findWithHigherVolume(BigDecimal volume) {
        return beanMappingService.mapTo(bottleTypeService.findWithHigherVolume(volume), BottleTypeDTO.class);

    }

    @Override
    public List<BottleTypeDTO> findBySize(BigDecimal size) {
        return beanMappingService.mapTo(bottleTypeService.findBySize(size), BottleTypeDTO.class);

    }

    @Override
    public BottleTypeDTO findById(Long id) {
        return beanMappingService.mapTo(bottleTypeService.findById(id), BottleTypeDTO.class);
    }
}
