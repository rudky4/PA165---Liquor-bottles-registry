package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.BottleType;
import cz.muni.fi.pa165.entity.Store;
import cz.muni.fi.pa165.enums.AlcoholType;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author mhajas
 */
public interface BottleTypeService {

    void createBottleType(BottleType type);

    void createBottleType(BottleType type, long manufacturerId);

    void updateBottleType(BottleType bottleType);

    void deleteBottleType(long id);

    List<BottleType> findAll();

    List<BottleType> findByAlcoholType(AlcoholType type);

    List<BottleType> findWithHigherVolume(BigDecimal volume);

    List<BottleType> findBySize(BigDecimal size);

    List<BottleType> findByStore(Store store);

    BottleType findById(Long id);
}
