package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Laboratory;
import java.util.Collection;

/**
 * @author rk
 * @date 2016-10-25
 */
public interface LaboratoryDAO {
    /**
     * Create new laboratory
     * @param laboratory object
     */
    void createLaboratory(Laboratory laboratory);

    /**
     * Provide collection of Laboratories
     * @return all Laboratories
     */
    Collection<Laboratory> getAllLaboratories();

    /**
     * Find Laboratory by id
     * @param id of Laboratory
     * @return object Laboratory
     */
    Laboratory getLaboratoryById(Long id);

    /**
     * Uprate laboratory
     * @param laboratory object to be updated
     */
    void updateLaboratory(Laboratory laboratory);

    /**
     * Delete laboratory
     * @param laboratory object to be deleted 
     */
    void deleteLaboratory(Laboratory laboratory);
}
