package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Laboratory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author rk
 * @date 2016-10-25
 */
public interface LaboratoryDAO extends CrudRepository<Laboratory, Long> {
    List<Laboratory> findAll();
}
