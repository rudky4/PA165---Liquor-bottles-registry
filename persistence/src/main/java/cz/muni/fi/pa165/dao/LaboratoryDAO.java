package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Laboratory;
import java.util.Collection;

/**
 * @author rk
 * @date 2016-10-25
 */
public interface LaboratoryDAO {
    void createLaboratory(Laboratory laboratory);

    Collection<Laboratory> getAllLaboratories();

    Laboratory getLaboratoryById(Long id);

    void updateLaboratory(Laboratory laboratory);

    void deleteLaboratory(Laboratory laboratory);
}
