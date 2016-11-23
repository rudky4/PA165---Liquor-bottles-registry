package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Bottle;
import cz.muni.fi.pa165.entity.Laboratory;
import cz.muni.fi.pa165.entity.Person;
import java.util.List;

/**
 *
 * @author rk
 * @date 2016-11-18
 */
public interface LaboratoryService {
    void createLaboratory(Laboratory laboratory);
    
    List<Laboratory> findAll();

    Laboratory findById(Long id);
    
    Laboratory findByName(String name);
    
    List<Person> findAllPersonsInLaboratory();

    List<Bottle> findAllBottlesToCheck();
    
    boolean isBottleToBeChecked(Bottle bottle);
}
