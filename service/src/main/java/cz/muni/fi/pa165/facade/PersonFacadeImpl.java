package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.PersonService;
import cz.muni.fi.pa165.dto.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Jakub Fiser
 * @date 14/11/2016
 */
public class PersonFacadeImpl implements PersonFacade {

    @Autowired
    private PersonService personService;

    @Override
    public void registerPerson(PersonDTO person, String unencryptedPassword) {
        // map PersonDTO to Person entity and then use autowired PersonService
//        PersonDTO -> Person
//        personService.registerPerson(person, password);
    }

    @Override
    public void changePassword(PersonDTO person, String newPassword) {

    }

    @Override
    public boolean authenticate(PersonDTO person, String password) {
        return false;
    }

    @Override
    public boolean isPolice(PersonDTO person) {
        return false;
    }

    @Override
    public boolean isManufacturer(PersonDTO person) {
        return false;
    }

    @Override
    public List<PersonDTO> findAll() {
        return null;
    }

    @Override
    public PersonDTO findUserById(Long personId) {
        return null;
    }

    @Override
    public PersonDTO findUserByLogin(String login) {
        return null;
    }
}
