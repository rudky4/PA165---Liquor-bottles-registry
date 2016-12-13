package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.BottleDAO;
import cz.muni.fi.pa165.entity.Bottle;
import cz.muni.fi.pa165.entity.BottleType;
import cz.muni.fi.pa165.entity.Manufacturer;
import cz.muni.fi.pa165.entity.Store;
import cz.muni.fi.pa165.exceptions.BottleIsAlreadyMarkedAsToxic;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * @author mhajas
 */
@Service
public class BottleServiceImpl implements BottleService {

    @Inject
    private BottleDAO bottleDAO;

    @Override
    public void createBottle(Bottle bottle) {
        bottleDAO.save(bottle);
    }

    @Override
    public List<Bottle> findAll() {
        return bottleDAO.findAll();
    }

    @Override
    public List<Bottle> getBottlesFromDate(Date date) {
        return bottleDAO.findByProduced(date);
    }

    @Override
    public List<Bottle> getAllToxicBottles() {
        return bottleDAO.findByToxic(true);
    }

    @Override
    public List<Bottle> getAllBottlesFromManufacturer(Manufacturer manufacturer) {
        return bottleDAO.getAllBottlesFromManufacturer(manufacturer);
    }

    @Override
    public List<Bottle> getAllToxicBottlesFromManufacturer(Manufacturer manufacturer) {
        return bottleDAO.getAllBottlesFromManufacturerWithToxicity(manufacturer, true);
    }

    @Override
    public List<Bottle> getAllNonToxicBottlesFromManufacturer(Manufacturer manufacturer) {
        return bottleDAO.getAllBottlesFromManufacturerWithToxicity(manufacturer, false);
    }

    @Override
    public List<Bottle> getAllToxicBottlesInStore(Store store) {
        return bottleDAO.getAllBottlesFromStoreWithToxicity(store, true);
    }

    @Override
    public List<Bottle> getAllNonToxicBottlesInStore(Store store) {
        return bottleDAO.getAllBottlesFromStoreWithToxicity(store, false);
    }

    @Override
    public List<Bottle> getAllBottlesFromManufacturerFromDate(Manufacturer manufacturer, Date date) {
        return bottleDAO.getAllBottlesFromManufacturerFromDate(manufacturer, date);
    }

    @Override
    public List<Bottle> findByBottleType(BottleType type) {
        return bottleDAO.findByBottleType(type);
    }

    @Override
    public boolean isToxic(Long id) {
        return isToxic(findById(id));
    }

    @Override
    public boolean isToxic(Bottle bottle) {
        return bottle.isToxic();
    }

    @Override
    public void setToxic(Long id) {
        setToxic(findById(id));
    }

    @Override
    public void setToxic(Bottle bottle) {
        if (isToxic(bottle)) {
            throw new BottleIsAlreadyMarkedAsToxic();
        }

        bottle.setToxic(true);
        bottleDAO.save(bottle);
    }


    @Override
    public Bottle findById(Long id) {
        return bottleDAO.findOne(id);
    }

    @Override
    public Bottle findByStickerId(String id) {
        return bottleDAO.findByStickerID(id);
    }
}
