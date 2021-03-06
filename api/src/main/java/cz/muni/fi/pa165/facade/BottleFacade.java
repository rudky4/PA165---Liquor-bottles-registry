package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.*;

import java.util.Date;
import java.util.List;

/**
 * @author mhajas
 */
public interface BottleFacade {
    void createBottle(BottleDTO bottle);

    void importBottleToStore(BottleCreateDTO bottle);

    List<BottleDTO> findAll();

    List<BottleDTO> getBottlesFromDate(Date date);

    List<BottleDTO> getAllToxicBottles();

    List<BottleDTO> getAllBottlesFromManufacturer(ManufacturerDTO manufacturer);

    List<BottleDTO> getAllBottlesInStore(StoreDTO store);

    List<BottleDTO> getAllNontoxicBottlesInStore(StoreDTO store);

    List<BottleDTO> findByBottleType(BottleTypeDTO type);

    boolean isToxic(Long id);

    boolean isToxic(BottleDTO bottle);

    void setToxic(Long id);

    void setToxic(BottleDTO bottle);
    
    void updateBottle(BottleDTO bottle);

    BottleDTO findById(Long id);

    BottleDTO findByStickerId(String id);

    void deleteBottle(long id);
}
