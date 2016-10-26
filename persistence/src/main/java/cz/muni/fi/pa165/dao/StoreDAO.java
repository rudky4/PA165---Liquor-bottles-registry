package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Store;

import java.util.Collection;

/**
 * @author Martin Sumera
 */
public interface StoreDAO {

    void createStore(Store store);

    Collection<Store> getAllStores();

    Store getStoreById(Long id);

    void updateStore(Store store);

    void deleteStore(Store store);
}
