package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Store;

import java.util.Collection;

/**
 * @author Martin Sumera
 */
public interface StoreDAO {

    /**
     * Create new store
     * @param store
     */
    void createStore(Store store);

    /**
     * Provide collection of all existing stores
     * @return
     */
    Collection<Store> getAllStores();

    /**
     * Provide specific store with given id
     * @param id
     * @return
     */
    Store getStoreById(Long id);

    /**
     * Update existing store
     * @param store
     */
    void updateStore(Store store);

    /**
     * Delete existing store
     * @param store
     */
    void deleteStore(Store store);
}
