package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.BottleTypeDTO;
import cz.muni.fi.pa165.entity.BottleType;
import cz.muni.fi.pa165.enums.AlcoholType;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.BottleTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author mhajas
 */
@Service
@Transactional
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
    public void createBottleType(BottleTypeDTO type, long manufacturerId) {
        bottleTypeService.createBottleType(beanMappingService.mapTo(type, BottleType.class), manufacturerId);
    }

    @Override
    public void updateBottleType(BottleTypeDTO bottleType) {
        bottleTypeService.updateBottleType(beanMappingService.mapTo(bottleType, BottleType.class));
    }

    @Override
    public void deleteBottleType(long id) {
        bottleTypeService.deleteBottleType(id);
    }

    @Override
    public List<BottleTypeDTO> findAll() {
        List<BottleType> types = bottleTypeService.findAll();
        return types == null ? null : beanMappingService.mapTo(types, BottleTypeDTO.class);
    }

    @Override
    public List<BottleTypeDTO> findByAlcoholType(AlcoholType type) {
        List<BottleType> types = bottleTypeService.findByAlcoholType(type);
        return types == null ? null : beanMappingService.mapTo(types, BottleTypeDTO.class);
    }

    @Override
    public List<BottleTypeDTO> findWithHigherVolume(BigDecimal volume) {
        List<BottleType> types = bottleTypeService.findWithHigherVolume(volume);
        return types == null ? null : beanMappingService.mapTo(types, BottleTypeDTO.class);

    }

    @Override
    public List<BottleTypeDTO> findBySize(BigDecimal size) {
        List<BottleType> types = bottleTypeService.findBySize(size);
        return types == null ? null : beanMappingService.mapTo(types, BottleTypeDTO.class);

    }

    @Override
    public BottleTypeDTO findById(Long id) {
        BottleType type = bottleTypeService.findById(id);
        return type == null ? null : beanMappingService.mapTo(type, BottleTypeDTO.class);
    }
}
