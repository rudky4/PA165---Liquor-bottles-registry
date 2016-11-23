package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.ManufacturerDAO;
import cz.muni.fi.pa165.entity.Bottle;
import cz.muni.fi.pa165.entity.Manufacturer;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collection;

/**
 * @author Martin Sumera
 */
@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    @Inject
    private ManufacturerDAO manufacturerDAO;

    @Inject
    private BottleService bottleService;

    @Override
    public void createManufacturer(Manufacturer manufacturer) {
        manufacturerDAO.save(manufacturer);
    }

    @Override
    public Collection<Manufacturer> findAll() {
        return manufacturerDAO.findAll();
    }

    @Override
    public Manufacturer findById(Long id) {
        return manufacturerDAO.findOne(id);
    }

    @Override
    public Manufacturer findByName(String name) {
        return manufacturerDAO.findByName(name);
    }

    @Override
    public boolean hasToxicProduction(Long id) {
        return hasToxicProduction(findById(id));
    }

    @Override
    public boolean hasToxicProduction(Manufacturer manufacturer) {
        for (Bottle bottle : bottleService.getAllToxicBottles()) {
            if (bottle.getBottleType().getManufacturedBy().getId() == manufacturer.getId()) {
                return true;
            }
        };
        return false;
    }

}
