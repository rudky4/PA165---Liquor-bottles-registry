package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.BeanMappingService;
import cz.muni.fi.pa165.PersonService;
import cz.muni.fi.pa165.dto.PersonDTO;
import cz.muni.fi.pa165.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jakub Fiser
 * @date 14/11/2016
 */
@Service
@Transactional
public class PersonFacadeImpl implements PersonFacade {

    @Inject
    private PersonService personService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public void registerPerson(PersonDTO person, String unencryptedPassword) {
        Person person1 = beanMappingService.mapTo(person, Person.class);
        personService.registerPerson(person1, unencryptedPassword);
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
        Person police = beanMappingService.mapTo(person, Person.class);
        return personService.isPolice(police);
    }

    @Override
    public boolean isManufacturer(PersonDTO person) {
        Person manufacturer = beanMappingService.mapTo(person, Person.class);
        return personService.isManufacturer(manufacturer);
    }

    @Override
    public List<PersonDTO> findAll() {
        return beanMappingService.mapTo(personService.findAll(), PersonDTO.class);
    }

    @Override
    public PersonDTO findUserById(Long personId) {
        Person result = personService.findUserById(personId);
        return result == null ? null : beanMappingService.mapTo(result, PersonDTO.class);
    }

    @Override
    public PersonDTO findUserByLogin(String login) {
        Person result = personService.findUserByLogin(login);
        return result == null ? null : beanMappingService.mapTo(result, PersonDTO.class);
    }
}
