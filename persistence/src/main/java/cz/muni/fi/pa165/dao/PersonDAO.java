package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Person;
import cz.muni.fi.pa165.enums.PersonRole;

import java.util.List;

/**
 * @author Jakub Fiser
 *         24/10/2016
 */
public interface PersonDAO {

    void createPerson(Person person);

    Person updatePerson(Person person);

    void deletePerson(Person person);

    Person findById(long id);

    Person findByLogin(String login);

    List<Person> findAll();

    List<Person> getPersonsOfRole(PersonRole role);
}