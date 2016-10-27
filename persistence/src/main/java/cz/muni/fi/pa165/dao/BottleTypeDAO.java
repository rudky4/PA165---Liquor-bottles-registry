package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.BottleType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author mhajas
 */
public interface BottleTypeDAO extends CrudRepository<BottleType, Long> {

    List<BottleType> findAll();
}

