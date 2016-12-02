package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Bottle;
import cz.muni.fi.pa165.entity.BottleType;
import cz.muni.fi.pa165.entity.Manufacturer;

import java.util.Date;
import java.util.List;

/**
 * @author mhajas
 */
public interface BottleService {

    void createBottle(Bottle bottle);

    List<Bottle> findAll();

    List<Bottle> getBottlesFromDate(Date date);

    List<Bottle> getAllToxicBottles();

    List<Bottle> findByBottleType(BottleType type);

    List<Bottle> getAllBottlesFromManufacturer(Manufacturer manufacturer);

    List<Bottle> getAllBottlesFromManufacturerFromDate(Manufacturer manufacturer, Date date);

    boolean isToxic(Long id);

    boolean isToxic(Bottle bottle);

    void setToxic(Long id);

    void setToxic(Bottle bottle);

    Bottle findById(Long id);

    Bottle findByStickerId(String id);
}
