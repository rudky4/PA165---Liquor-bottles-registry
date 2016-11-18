package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.Person;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jakub Fiser
 * @date 14/11/2016
 */
public interface PersonService {
    void registerPerson(Person person, String unencryptedPassword);
    void changePassword(Person person, String newPassword);
    boolean authenticate(Person person, String password);
    boolean isPolice(Person person);
    boolean isManufacturer(Person person);
    List<Person> findAll();
    Person findUserById(Long personId);
    Person findUserByLogin(String login);
}
