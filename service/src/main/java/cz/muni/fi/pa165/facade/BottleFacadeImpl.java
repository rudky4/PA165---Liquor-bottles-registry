package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.BottleDTO;
import cz.muni.fi.pa165.dto.BottleTypeDTO;
import cz.muni.fi.pa165.dto.ManufacturerDTO;
import cz.muni.fi.pa165.dto.StoreDTO;
import cz.muni.fi.pa165.entity.Bottle;
import cz.muni.fi.pa165.entity.BottleType;
import cz.muni.fi.pa165.entity.Manufacturer;
import cz.muni.fi.pa165.entity.Store;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.BottleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * @author mhajas
 */
@Service
@Transactional
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
        List<Bottle> bottles = bottleService.findAll();
        return bottles == null ? null : beanMappingService.mapTo(bottles, BottleDTO.class);
    }

    @Override
    public List<BottleDTO> getBottlesFromDate(Date date) {
        List<Bottle> bottles = bottleService.getBottlesFromDate(date);
        return bottles == null ? null : beanMappingService.mapTo(bottles, BottleDTO.class);
    }

    @Override
    public List<BottleDTO> getAllToxicBottles() {
        List<Bottle> bottles = bottleService.getAllToxicBottles();
        return bottles == null ? null : beanMappingService.mapTo(bottles, BottleDTO.class);
    }

    @Override
    public List<BottleDTO> getAllBottlesFromManufacturer(ManufacturerDTO manufacturerDTO) {
        Manufacturer manufacturer = beanMappingService.mapTo(manufacturerDTO, Manufacturer.class);
        List<Bottle> bottles = bottleService.getAllBottlesFromManufacturer(manufacturer);
        return bottles == null ? null : beanMappingService.mapTo(bottles, BottleDTO.class);
    }

    @Override
    public List<BottleDTO> getAllBottlesInStore(StoreDTO storeDTO) {
        Store store = beanMappingService.mapTo(storeDTO, Store.class);
        List<Bottle> bottles = bottleService.getAllBottlesInStore(store);
        return bottles == null ? null : beanMappingService.mapTo(bottles, BottleDTO.class);
    }

    @Override
    public List<BottleDTO> getAllNontoxicBottlesInStore(StoreDTO storeDTO) {
        Store store = beanMappingService.mapTo(storeDTO, Store.class);
        List<Bottle> bottles = bottleService.getAllNontoxicBottlesInStore(store);
        return bottles == null ? null : beanMappingService.mapTo(bottles, BottleDTO.class);
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
        Bottle bottle = bottleService.findById(id);
        return bottle == null ? null : beanMappingService.mapTo(bottle, BottleDTO.class);
    }

    @Override
    public BottleDTO findByStickerId(String id) {
        Bottle bottle = bottleService.findByStickerId(id);
        return beanMappingService.mapTo(bottle, BottleDTO.class);
    }
    
    @Override
    public void updateBottle(BottleDTO bottle){
        bottleService.updateBottle(beanMappingService.mapTo(bottle, Bottle.class));
    }
}
