package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Bottle;
import cz.muni.fi.pa165.entity.BottleType;
import cz.muni.fi.pa165.entity.Manufacturer;
import cz.muni.fi.pa165.entity.Store;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

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

    List<Bottle> findByStore(Store store);

    @Query("SELECT b FROM Bottle b " +
            "INNER JOIN b.bottleType bt " +
            "INNER JOIN bt.manufacturedBy m " +
            "WHERE bt.manufacturedBy=:manufacturer " +
            "AND b.produced>=:date")
    List<Bottle> getAllBottlesFromManufacturerFromDate(
            @Param("manufacturer") Manufacturer manufacturer,
            @Param("date") Date date);

    @Query("SELECT b FROM Bottle b " +
            "INNER JOIN b.bottleType bt " +
            "INNER JOIN bt.manufacturedBy m " +
            "WHERE bt.manufacturedBy=:manufacturer")
    List<Bottle> getAllBottlesFromManufacturer(
            @Param("manufacturer") Manufacturer manufacturer);

    Bottle findByStickerID(String id);
}

