package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.LaboratoryDTO;
import cz.muni.fi.pa165.dto.ManufacturerDTO;
import cz.muni.fi.pa165.dto.StoreDTO;
import cz.muni.fi.pa165.entity.Laboratory;
import cz.muni.fi.pa165.entity.Manufacturer;
import cz.muni.fi.pa165.entity.Store;
import cz.muni.fi.pa165.service.BeanMappingService;
import cz.muni.fi.pa165.service.PersonService;
import cz.muni.fi.pa165.dto.PersonDTO;
import cz.muni.fi.pa165.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of PersonFacade interface.
 *
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
        Person person1 = beanMappingService.mapTo(person, Person.class);
        personService.changePassword(person1, newPassword);
    }

    @Override
    public boolean authenticate(String person, String password) {
        return personService.authenticate(personService.findUserByLogin(person), password);
    }

    @Override
    public boolean isPolice(PersonDTO person) {
        Person police = beanMappingService.mapTo(person, Person.class);
        return personService.isPolice(police);
    }

    @Override
    public boolean worksForLaboratory(PersonDTO person, LaboratoryDTO laboratory) {
        Person employee = beanMappingService.mapTo(person, Person.class);
        Laboratory employer = beanMappingService.mapTo(laboratory, Laboratory.class);
        return personService.worksForLaboratory(employee, employer);
    }

    @Override
    public boolean worksForManufacturer(PersonDTO person, ManufacturerDTO manufacturer) {
        Person employee = beanMappingService.mapTo(person, Person.class);
        Manufacturer employer = beanMappingService.mapTo(manufacturer, Manufacturer.class);
        return personService.worksForManufacturer(employee, employer);
    }

    @Override
    public boolean worksForStore(PersonDTO person, StoreDTO store) {
        Person employee = beanMappingService.mapTo(person, Person.class);
        Store employer = beanMappingService.mapTo(store, Store.class);
        return personService.worksForStore(employee, employer);
    }

    @Override
    public List<PersonDTO> findAll() {
        List<Person> result = personService.findAll();
        return result == null ? null : beanMappingService.mapTo(result, PersonDTO.class);
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

    @Override
    public PersonDTO findUserByEmail(String email) {
        Person result = personService.findUserByEmail(email);
        return result == null ? null : beanMappingService.mapTo(result, PersonDTO.class);
    }
}
