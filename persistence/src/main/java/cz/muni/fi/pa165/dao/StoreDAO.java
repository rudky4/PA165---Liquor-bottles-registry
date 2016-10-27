package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Store;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Martin Sumera
 */
public interface StoreDAO extends CrudRepository<Store, Long> {

    List<Store> findAll();
}
