package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Bottle;
import cz.muni.fi.pa165.entity.Laboratory;
import cz.muni.fi.pa165.entity.Person;
import cz.muni.fi.pa165.entity.Store;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

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
    
    @Query("select store.persons from Store store") 
    List<Person> findAllPersonsInStore();

    @Query("select p from Store store join store.persons p where p.id = ?1")
    Person findPersonInStore(Long id_person);
    
    @Query("select store.bottles from Store store")
    List<Bottle> findAllBottlesInStore();
    
    @Query("select b from Store store join store.bottles b where b.id = ?1")
    Bottle isBottleInStore(Long id_bottle);
}
