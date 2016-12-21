package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.BottleTypeDAO;
import cz.muni.fi.pa165.entity.BottleType;
import cz.muni.fi.pa165.entity.Manufacturer;
import cz.muni.fi.pa165.entity.Store;
import cz.muni.fi.pa165.enums.AlcoholType;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author mhajas
 */
@Service
public class BottleTypeServiceImpl implements BottleTypeService {

    @Inject
    private BottleTypeDAO bottleTypeDAO;

    @Inject
    private ManufacturerService manufacturerService;

    @Override
    public void createBottleType(BottleType type) {
        bottleTypeDAO.save(type);
    }

    @Override
    public void createBottleType(BottleType type, long manufacturerId) {
        Manufacturer manufacturer = manufacturerService.findById(manufacturerId);
        if(manufacturer == null) {
            throw new IllegalArgumentException("Manufacturer with id=" + manufacturerId + " does not exist.");
        }
        type.setDeleted(false);
        BottleType stored = bottleTypeDAO.save(type);
        stored.setManufacturedBy(manufacturer);
    }

    @Override
    public void updateBottleType(BottleType bottleType) {
        if(bottleType == null || !bottleTypeDAO.exists(bottleType.getId())) {
            throw new IllegalArgumentException();
        }
        bottleTypeDAO.save(bottleType);
    }

    @Override
    public void deleteBottleType(long id) {
        BottleType bottleType = bottleTypeDAO.findOne(id);
        bottleType.setDeleted(true);
    }

    @Override
    public List<BottleType> findAll() {
        return bottleTypeDAO.findAll();
    }

    @Override
    public List<BottleType> findByAlcoholType(AlcoholType type) {
        return bottleTypeDAO.findByType(type);
    }

    @Override
    public List<BottleType> findWithHigherVolume(BigDecimal volume) {
        return bottleTypeDAO.findByVolumeGreaterThanEqual(volume);
    }

    @Override
    public List<BottleType> findBySize(BigDecimal size) {
        return bottleTypeDAO.findBySize(size);
    }

    @Override
    public List<BottleType> findByStore(Store store) {
        return bottleTypeDAO.findByStore(store);
    }

    @Override
    public BottleType findById(Long id) {
        return bottleTypeDAO.findOne(id);
    }
}
