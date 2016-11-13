package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Person;
import cz.muni.fi.pa165.enums.PersonRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * The interface for managing Person entity in the database.
 *
 * @author Jakub Fiser
 *         24/10/2016
 */
public interface PersonDAO extends CrudRepository<Person, Long> {

    /**
     * Method searches the database for Person with given login.
     * @param login Login to search.
     * @return Person with specified login or null.
     */
    Person findByLogin(@Param(Person.PARAMETER_LOGIN) String login);

    /**
     * Method to retrieve all persons from the database.
     * @return List of all persons.
     */
    List<Person> findAll();

    /**
     * Method searches the database for persons of given role.
     * @param role Role to search.
     * @return List of persons with given role.
     */
    List<Person> findByRole(@Param(Person.PARAMETER_ROLE) PersonRole role);
}