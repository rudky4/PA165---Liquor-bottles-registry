package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Bottle;
import cz.muni.fi.pa165.entity.Person;
import cz.muni.fi.pa165.entity.Store;
import java.util.List;

/**
 *
 * @author rk
 * @date 2016-11-18
 */
public interface StoreService {
    void createStore(Store store);
    
    List<Store> findAll();

    Store findById(Long id);
    
    Store findByName(String name);
    
    List<Person> findAllPersonsInStore();
    
    boolean isPersonInStore(Person person);

    List<Bottle> findAllBottles();
    
    boolean isBottleInStore(Bottle bottle);
}
