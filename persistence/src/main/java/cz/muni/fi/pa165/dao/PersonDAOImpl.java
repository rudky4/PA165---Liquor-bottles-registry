package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Person;
import cz.muni.fi.pa165.enums.PersonRole;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Jakub Fiser
 *         24/10/2016
 */
@Repository
public class PersonDAOImpl implements PersonDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createPerson(Person person) {
        em.persist(person);
    }

    @Override
    public Person updatePerson(Person person) {
        return em.merge(person);
    }

    @Override
    public void deletePerson(Person person) {
        em.remove(person);
    }

    @Override
    public Person findById(long id) {
        return em.find(Person.class, id);
    }

    @Override
    public Person findByLogin(String login) {
        return em.createNamedQuery(Person.FIND_BY_LOGIN, Person.class)
                .setParameter(Person.PARAMETER_LOGIN, login)
                .getSingleResult();
    }

    @Override
    public List<Person> findAll() {
        return em.createNamedQuery(Person.FIND_ALL, Person.class)
                .getResultList();
    }

    @Override
    public List<Person> getPersonsOfRole(PersonRole role) {
        return em.createNamedQuery(Person.FIND_BY_ROLE, Person.class)
                .setParameter(Person.PARAMETER_ROLE, role)
                .getResultList();
    }
}
