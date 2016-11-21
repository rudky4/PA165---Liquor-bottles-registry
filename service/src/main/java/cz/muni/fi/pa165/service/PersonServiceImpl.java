package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.PersonDAO;
import cz.muni.fi.pa165.entity.Person;
import cz.muni.fi.pa165.enums.PersonRole;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Jakub Fiser
 * @date 14/11/2016
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Inject
    private PersonDAO personDAO;



    @Override
    public void registerPerson(Person person, String unencryptedPassword) {
        // TODO: Use encryption here.
    }

    @Override
    public void changePassword(Person person, String newPassword) {
        // TODO: Use encryption here.
    }

    @Override
    public boolean authenticate(Person person, String password) {
        // TODO: Use encryption here.
        return false;
    }

    @Override
    public boolean isPolice(Person person) {
        return PersonRole.POLICE.equals(person.getRole());
    }

    @Override
    public boolean isManufacturer(Person person) {
        return PersonRole.MANUFACTURER.equals(person.getRole());
    }

    @Override
    public List<Person> findAll() {
        return personDAO.findAll();
    }

    @Override
    public Person findUserById(Long personId) {
        return personDAO.findOne(personId);
    }

    @Override
    public Person findUserByLogin(String login) {
        return personDAO.findByLogin(login);
    }
}
