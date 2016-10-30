package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Person;
import cz.muni.fi.pa165.enums.PersonRole;

import java.util.List;

/**
 * The interface for managing Person entity in the database.
 *
 * @author Jakub Fiser
 *         24/10/2016
 */
public interface PersonDAO {

    /**
     * Method creates given person in the database.
     * @param person Person to create.
     */
    void createPerson(Person person);

    /**
     * Method updates given person in the database.
     * @param person Person to update.
     * @return Updated Person object.
     */
    Person updatePerson(Person person);

    /**
     * Method deletes given person in the database.
     * @param person Person to delete.
     */
    void deletePerson(Person person);

    /**
     * Method searches the database for Person with given ID value.
     * @param id ID value to search.
     * @return Person with specified ID value or null.
     */
    Person findById(long id);

    /**
     * Method searches the database for Person with given login.
     * @param login Login to search.
     * @return Person with specified login or null.
     */
    Person findByLogin(String login);

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
    List<Person> getPersonsOfRole(PersonRole role);
}