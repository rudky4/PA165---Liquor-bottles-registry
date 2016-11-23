package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Laboratory;
import cz.muni.fi.pa165.entity.Manufacturer;
import cz.muni.fi.pa165.entity.Person;
import cz.muni.fi.pa165.entity.Store;

import java.util.List;

/**
 * Interface for communication with service layer.
 *
 * @author Jakub Fiser
 * @date 14/11/2016
 */
public interface PersonService {

    /**
     * Register new person.
     * @param person Person to register.
     * @param unencryptedPassword Password of person.
     */
    void registerPerson(Person person, String unencryptedPassword);

    /**
     * Change password of person.
     * @param person Person whose password will be changed.
     * @param newPassword New password.
     */
    void changePassword(Person person, String newPassword);

    /**
     * Authenticate person with given password.
     * @param person Person to authenticate.
     * @param password Given password.
     * @return True if password is correct, false otherwise.
     */
    boolean authenticate(Person person, String password);

    /**
     * Check if person works for Laboratory.
     * @param person Person to check.
     * @param laboratory Laboratory to check.
     * @return True if person works for Laboratory, false otherwise.
     */
    boolean worksForLaboratory(Person person, Laboratory laboratory);

    /**
     * Check if person works for Manufacturer.
     * @param person Person to check.
     * @param manufacturer Manufacturer to check.
     * @return True if person works for Manufacturer, false otherwise.
     */
    boolean worksForManufacturer(Person person, Manufacturer manufacturer);

    /**
     * Check if person works for Store.
     * @param person Person to check.
     * @param store Store to check.
     * @return True if person works for Store, false otherwise.
     */
    boolean worksForStore(Person person, Store store);

    /**
     * Check if person is of police role.
     * @param person Person to check.
     * @return True if person is of Police role, false otherwise.
     */
    boolean isPolice(Person person);

    /**
     * Find all persons.
     * @return List of all persons.
     */
    List<Person> findAll();

    /**
     * Find person by ID.
     * @param personId ID for search.
     * @return Person with specified ID or null.
     */
    Person findUserById(Long personId);

    /**
     * Find person by login.
     * @param login Login to search.
     * @return Person with specified login or null.
     */
    Person findUserByLogin(String login);

    /**
     * Find person by email.
     * @param email Email to search.
     * @return Person with specified email or null.
     */
    Person findUserByEmail(String email);
}
