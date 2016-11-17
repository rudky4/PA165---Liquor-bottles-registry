package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.PersonDTO;

import java.util.List;

/**
 * @author Jakub Fiser
 * @date 14/11/2016
 */
public interface PersonFacade {
    void registerPerson(PersonDTO person, String unencryptedPassword);
    void changePassword(PersonDTO person, String newPassword);
    boolean authenticate(PersonDTO person, String password);
    boolean isPolice(PersonDTO person);
    boolean isManufacturer(PersonDTO person);
    List<PersonDTO> findAll();
    PersonDTO findUserById(Long personId);
    PersonDTO findUserByLogin(String login);
}
