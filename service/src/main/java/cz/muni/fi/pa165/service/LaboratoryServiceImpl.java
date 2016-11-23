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
 * @date 2016-11-22
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
        return laboratoryDAO.findAll();
    }

    @Override
    public Laboratory findById(Long id) {
        return laboratoryDAO.findOne(id);
    }

    @Override
    public Laboratory findByName(String name) {
        return laboratoryDAO.findByName(name);
    }

    @Override
    public List<Person> findAllPersons() {
        return laboratoryDAO.findAllPersonsInLaboratory();
    }

    @Override
    public boolean isPersonInLaboratory(Person person) {
        return null != laboratoryDAO.isPersonInLaboratory(person.getId());
    }

    @Override
    public List<Bottle> findAllBottlesToCheck() {
        return laboratoryDAO.findAllBottlesToCheck();
    }

    @Override
    public boolean isBottleToBeChecked(Bottle bottle) {
        return null != laboratoryDAO.isBottleToBeChecked(bottle.getId());
    }   
}
