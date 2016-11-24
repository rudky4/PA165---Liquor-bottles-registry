package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Store;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

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
}
