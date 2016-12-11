package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.LaboratoryDTO;
import cz.muni.fi.pa165.dto.ManufacturerDTO;
import cz.muni.fi.pa165.dto.PersonDTO;
import cz.muni.fi.pa165.dto.StoreDTO;

import java.util.List;

/**
 * Facade interface for communication with service layer.
 *
 * @author Jakub Fiser
 * @date 14/11/2016
 */
public interface PersonFacade {

    /**
     * Register new person.
     * @param person Person to register.
     * @param unencryptedPassword Password of person.
     */
    void registerPerson(PersonDTO person, String unencryptedPassword);

    /**
     * Change password of person.
     * @param person Person whose password will be changed.
     * @param newPassword New password.
     */
    void changePassword(PersonDTO person, String newPassword);

    /**
     * Authenticate person with given password.
     * @param person Person to authenticate.
     * @param password Given password.
     * @return True if password is correct, false otherwise.
     */
    boolean authenticate(String person, String password);

    /**
     * Check if person works for Laboratory.
     * @param person Person to check.
     * @param laboratory Laboratory to check.
     * @return True if person works for Laboratory, false otherwise.
     */
    boolean worksForLaboratory(PersonDTO person, LaboratoryDTO laboratory);

    /**
     * Check if person works for Manufacturer.
     * @param person Person to check.
     * @param manufacturer Manufacturer to check.
     * @return True if person works for Manufacturer, false otherwise.
     */
    boolean worksForManufacturer(PersonDTO person, ManufacturerDTO manufacturer);

    /**
     * Check if person works for Store.
     * @param person Person to check.
     * @param store Store to check.
     * @return True if person works for Store, false otherwise.
     */
    boolean worksForStore(PersonDTO person, StoreDTO store);

    /**
     * Check if person is of police role.
     * @param person Person to check.
     * @return True if person is of Police role, false otherwise.
     */
    boolean isPolice(PersonDTO person);

    /**
     * Find all persons.
     * @return List of all persons.
     */
    List<PersonDTO> findAll();

    /**
     * Find person by ID.
     * @param personId ID for search.
     * @return Person with specified ID or null.
     */
    PersonDTO findUserById(Long personId);

    /**
     * Find person by login.
     * @param login Login to search.
     * @return Person with specified login or null.
     */
    PersonDTO findUserByLogin(String login);

    /**
     * Find person by email.
     * @param email Email to search.
     * @return Person with specified email or null.
     */
    PersonDTO findUserByEmail(String email);
}
