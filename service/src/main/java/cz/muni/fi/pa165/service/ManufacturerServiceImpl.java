package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.ManufacturerDAO;
import cz.muni.fi.pa165.entity.Bottle;
import cz.muni.fi.pa165.entity.Manufacturer;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

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
    public List<Manufacturer> findAll() {
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
        for (Bottle bottle : bottleService.getAllBottlesFromManufacturer(manufacturer)) {
            if (bottle.isToxic()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void markAllProducedBottlesSinceDateAsToxic(Manufacturer manufacturer, Date date) {
        List<Bottle> bottles = bottleService.getAllBottlesFromManufacturerFromDate(manufacturer, date);
        for (Bottle bottle : bottles) {
            bottleService.setToxic(bottle);
        }
    }
}
