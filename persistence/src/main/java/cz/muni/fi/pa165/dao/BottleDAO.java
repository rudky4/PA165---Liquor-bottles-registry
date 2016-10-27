package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Bottle;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author mhajas
 */
@org.springframework.stereotype.Repository
public interface BottleDAO extends CrudRepository<Bottle, Long> {

    List<Bottle> findAll();
}

