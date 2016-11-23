package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Bottle;
import cz.muni.fi.pa165.entity.BottleType;
import cz.muni.fi.pa165.entity.Manufacturer;
import org.springframework.data.jpa.repository.Query;
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

    @Query("SELECT b FROM Bottle b " +
            "INNER JOIN b.bottleType bt " +
            "INNER JOIN bt.manufacturedBy m " +
            "WHERE bt.id=b.bottleType " +
            "AND bt.manufacturedBy=:manufacturerId " +
            "AND b.produced>=:date")
    List<Bottle> getAllBottlesFromManufacturerFromDate(Long manufacturerId, Date date);

    Bottle findByStickerID(String id);
}

