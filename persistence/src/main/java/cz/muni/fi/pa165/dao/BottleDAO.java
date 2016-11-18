package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Bottle;
import cz.muni.fi.pa165.entity.BottleType;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * @author mhajas
 */
@org.springframework.stereotype.Repository
public interface BottleDAO extends CrudRepository<Bottle, Long> {

    /**
     * Get all bottles from database
     * @return List of bottles
     */
    List<Bottle> findAll();

    List<Bottle> findByProduced(Date date);

    List<Bottle> findByBottleType(BottleType bottleType);

    List<Bottle> findByToxic(boolean toxic);

    Bottle findByStickerID(String id);
}

