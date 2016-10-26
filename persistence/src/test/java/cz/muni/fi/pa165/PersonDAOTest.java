package cz.muni.fi.pa165;

import cz.muni.fi.pa165.config.PersistenceContext;
import cz.muni.fi.pa165.dao.PersonDAO;
import cz.muni.fi.pa165.entity.Person;
import cz.muni.fi.pa165.enums.PersonRole;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;

/**
 * @author Martin Sumera
 */
@ContextConfiguration(classes = PersistenceContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PersonDAOTest {

    @Autowired
    private PersonDAO personDAO;

    private Person person1;

    private Person person2;

    @Before
    public void initTest(){
        person1 = new Person();
        person1.setName("name1");
        person1.setLogin("login1");
        person1.setPassword("password1");
        person1.setRole(PersonRole.MANUFACTURER);

        person2 = new Person();
        person2.setName("name2");
        person2.setLogin("login2");
        person2.setPassword("password2");
        person2.setRole(PersonRole.CUSTOMER);

        personDAO.createPerson(person1);
        personDAO.createPerson(person2);
    }

    @Test(expected = javax.validation.ValidationException.class)
    public void createNullLogin() {
        person1.setLogin(null);
        personDAO.updatePerson(person1);

        List<Person> persons = new ArrayList<>(personDAO.findAll());
    }

    @Test(expected = javax.validation.ConstraintViolationException.class)
    public void createNullPassword() {
        person1.setPassword(null);
        personDAO.updatePerson(person1);

        List<Person> persons = new ArrayList<>(personDAO.findAll());
    }

    @Test(expected = javax.validation.ValidationException.class)
    public void createNullName() {
        person1.setName(null);
        personDAO.updatePerson(person1);

        List<Person> persons = new ArrayList<>(personDAO.findAll());
    }


    @Test
    public void createPersonTest() {
        List<Person> actual = new ArrayList<>(personDAO.findAll());

        assertEquals(2, actual.size());
        Person person = actual.get(0);
        assertEquals(person1.getId(), person.getId());
        assertEquals(person1.getName(), person.getName());
        assertEquals(person1.getLogin(), person.getLogin());
        assertEquals(person1.getPassword(), person.getPassword());
        assertTrue(person1.equals(person));
        assertTrue(person.equals(person1));
    }

    @Test
    public void searchExistingPersonTest() {
        Person person = personDAO.findById(person1.getId());
        Assert.assertNotNull(person);
        Assert.assertEquals(person, person1);
        assertTrue(person1.equals(person));
        assertTrue(person.equals(person1));
    }

    @Test
    public void searchNonExistingPersonTest() {
        Assert.assertNull(personDAO.findById(Long.MAX_VALUE));
    }

    @Test
    public void searchPersonByRoleTest() {
        List<Person> persons = new ArrayList<>(personDAO.getPersonsOfRole(PersonRole.CUSTOMER));
        assertEquals(1, persons.size());

        persons = new ArrayList<>(personDAO.getPersonsOfRole(PersonRole.MANUFACTURER));
        assertEquals(1, persons.size());

        persons = new ArrayList<>(personDAO.getPersonsOfRole(PersonRole.POLICE));
        assertEquals(0, persons.size());
    }

    @Test
    public void searchPersonByLoginTest() {
        Person person = personDAO.findByLogin(person1.getLogin());
        Assert.assertNotNull(person);
        Assert.assertEquals(person, person1);
        assertTrue(person1.equals(person));
        assertTrue(person.equals(person1));
    }

    @Test
    public void updatePersonTest() {
        String newPassword = "newpassword";
        String newLogin = "newLogin";

        person1.setLogin(newLogin);
        person1.setPassword(newPassword);

        personDAO.updatePerson(person1);

        Person person = personDAO.findByLogin(person1.getLogin());
        Assert.assertNotNull(person);
        Assert.assertEquals(person, person1);
        assertTrue(person1.equals(person));
        assertTrue(person.equals(person1));
    }

    @Test
    public void deletePersonTest() {
        personDAO.deletePerson(person1);
        Assert.assertEquals(personDAO.findAll().size(), 1);
    }

}
