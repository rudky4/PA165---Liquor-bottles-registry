package cz.muni.fi.pa165;

import cz.muni.fi.pa165.entity.Person;
import cz.muni.fi.pa165.enums.PersonRole;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;

/**
 * @author Martin Sumera
 */
public class PersonDAOTest extends AbstractDAOTest {

    private Person person1;

    private Person person2;

    @Before
    public void initTest(){
        person1 = new Person();
        person1.setName("name");
        person1.setLogin("login1");
        person1.setPasswordHash("password1");
        person1.setEmail("name1@mail.cz");
        person1.setRole(PersonRole.MANUFACTURER);

        person2 = new Person();
        person2.setName("name");
        person2.setLogin("login2");
        person2.setPasswordHash("password2");
        person2.setEmail("name2@mail.cz");
        person2.setRole(PersonRole.LAB);

        personDAO.save(person1);
        personDAO.save(person2);
    }

    @Test(expected = javax.validation.ValidationException.class)
    public void createNullLogin() {
        person1.setLogin(null);
        personDAO.save(person1);
        personDAO.findAll();
    }

    @Test(expected = javax.validation.ConstraintViolationException.class)
    public void createNullPassword() {
        person1.setPasswordHash(null);
        personDAO.save(person1);
        personDAO.findAll();
    }

    @Test(expected = javax.validation.ValidationException.class)
    public void createNullName() {
        person1.setName(null);
        personDAO.save(person1);
        personDAO.findAll();
    }


    @Test
    public void createPersonTest() {
        List<Person> actual = personDAO.findAll();

        assertEquals(2, actual.size());
        Person person = actual.get(0);
        assertEquals(person1.getId(), person.getId());
        assertEquals(person1.getName(), person.getName());
        assertEquals(person1.getLogin(), person.getLogin());
        assertEquals(person1.getPasswordHash(), person.getPasswordHash());
        assertTrue(person1.equals(person));
        assertTrue(person.equals(person1));
    }

    @Test
    public void searchExistingPersonTest() {
        Person person = personDAO.findOne(person1.getId());
        Assert.assertNotNull(person);
        Assert.assertEquals(person, person1);
        assertTrue(person1.equals(person));
        assertTrue(person.equals(person1));
    }

    @Test
    public void searchNonExistingPersonTest() {
        Assert.assertNull(personDAO.findOne(Long.MAX_VALUE));
    }

    @Test
    public void searchPersonByRoleTest() {
        List<Person> persons = personDAO.findByRole(PersonRole.LAB);
        assertEquals(1, persons.size());

        persons = personDAO.findByRole(PersonRole.MANUFACTURER);
        assertEquals(1, persons.size());

        persons = personDAO.findByRole(PersonRole.POLICE);
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
        person1.setPasswordHash(newPassword);

        personDAO.save(person1);

        Person person = personDAO.findByLogin(person1.getLogin());
        Assert.assertNotNull(person);
        Assert.assertEquals(person, person1);
        assertTrue(person1.equals(person));
        assertTrue(person.equals(person1));
    }

    @Test
    public void deletePersonTest() {
        personDAO.delete(person1);
        Assert.assertEquals(personDAO.findAll().size(), 1);
    }

}
