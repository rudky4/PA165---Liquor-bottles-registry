package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Laboratory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

/**
 * @author rk
 * @date 2016-10-25
 */
public interface LaboratoryDAO extends CrudRepository<Laboratory, Long> {


    /**
     * Provide collection of Laboratories
     * @return all Laboratories
     */
    List<Laboratory> findAll();

    /**
     * 
     * @param name of laboratory to be found
     * @return Laboratory
     */
    Laboratory findByName(String name);

    @Query("SELECT l FROM Laboratory l " +
            "ORDER BY size(l.bottlesToCheck)")
    List<Laboratory> findByBottlesToCheckAsc();
}
