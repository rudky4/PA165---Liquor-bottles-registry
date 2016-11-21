package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.BottleType;
import cz.muni.fi.pa165.enums.AlcoholType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author mhajas
 */
public interface BottleTypeDAO extends CrudRepository<BottleType, Long> {

    /**
     * Get all bottle types from database
     * @return List of all types
     */
    List<BottleType> findAll();

    List<BottleType> findByType(AlcoholType type);

    List<BottleType> findByVolumeGreaterThanEqual(BigDecimal volume);

    List<BottleType> findBySize(BigDecimal size);
}

