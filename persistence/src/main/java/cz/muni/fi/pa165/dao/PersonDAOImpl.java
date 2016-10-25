package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Person;
import cz.muni.fi.pa165.enums.PersonRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * @author Jakub Fiser
 *         24/10/2016
 */
@Repository
public class PersonDAOImpl {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private PersonDAO personDAO;

    public void createPerson(Person person) {
        personDAO.save(person);
    }

    public Person updatePerson(Person person) {
        return personDAO.save(person);
    }

    public void deletePerson(Person person) {
        personDAO.delete(person);
    }

    public Person findById(long id) {
        return personDAO.findOne(id);
    }

    public Person findByLogin(String login) {
        return personDAO.findByLogin(login);
    }

    public List<Person> findAll() {
        return personDAO.findAll();
    }

    public List<Person> getPersonsOfRole(PersonRole role) {
        return personDAO.findByRole(role);
    }
}
