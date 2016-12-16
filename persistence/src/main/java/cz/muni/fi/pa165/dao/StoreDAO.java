package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Store;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

/**
 * @author Martin Sumera
 */
public interface StoreDAO extends CrudRepository<Store, Long> {

    /**
     * Provide list of all existing stores
     * @return all stores
     */
    List<Store> findAll();
    
    /**
     * 
     * @param name of store to be found
     * @return Store
     */
    Store findByName(String name);

    @Query("SELECT s FROM Bottle b " +
            "INNER JOIN b.bottleType bt " +
            "INNER JOIN b.store s " +
            "WHERE bt.id=:id")
    Set<Store> findByBottleType(@Param("id") Long id);
}
