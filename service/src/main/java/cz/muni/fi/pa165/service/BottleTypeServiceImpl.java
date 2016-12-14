package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.BottleTypeDAO;
import cz.muni.fi.pa165.entity.BottleType;
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
        BottleType stored = bottleTypeDAO.save(type);
        stored.setManufacturedBy(manufacturerService.findById(manufacturerId));
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
    public BottleType findById(Long id) {
        return bottleTypeDAO.findOne(id);
    }
}
