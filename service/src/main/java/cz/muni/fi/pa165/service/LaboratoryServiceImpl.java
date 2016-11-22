package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.LaboratoryDAO;
import cz.muni.fi.pa165.entity.Bottle;
import cz.muni.fi.pa165.entity.Laboratory;
import cz.muni.fi.pa165.entity.Person;
import java.util.List;

import javax.inject.Inject;

/**
 *
 * @author rk
 */
public class LaboratoryServiceImpl implements LaboratoryService{
    @Inject
    private LaboratoryDAO laboratoryDAO;
    
    @Override
    public void createLaboratory(Laboratory laboratory) {
        laboratoryDAO.save(laboratory);
    }

    @Override
    public List<Laboratory> findAll() {
    }

    @Override
    public Laboratory findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Laboratory findByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Person> findAllPersons() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isPersonInLaboratory(Person person) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Bottle> findAllBottlesToCheck() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isBottleToBeChecked(Bottle bottle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
