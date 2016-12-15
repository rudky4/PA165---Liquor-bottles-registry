package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.PersonDAO;
import cz.muni.fi.pa165.entity.Person;
import cz.muni.fi.pa165.enums.PersonRole;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author rk
 * @date 2016-11-25
 */
public class PersonServiceTest extends AbstractServiceTest {
    
    @Mock
    private PersonDAO personDAO;

    @InjectMocks
    @Autowired
    private PersonService personService;

    private Person person1;

    private Person person2;

    private List<Person> personList;

    private List<Person> emptyList = Collections.emptyList();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        person1 = new Person();
        person1.setName("name");
        person1.setLogin("login1");
        person1.setEmail("name1@mail.cz");
        person1.setRole(PersonRole.MANUFACTURER);

        person2 = new Person();
        person2.setName("name");
        person2.setLogin("login2");
        person2.setEmail("name2@mail.cz");
        person2.setRole(PersonRole.LAB);

        personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);
    }

    @Test
    public void createBottleTypeWillNotCrash() {
        personService.registerPerson(person1,"password");
        verify(personDAO).save(person1);
    }
    
    @Test
    public void findAllWithEmptyListReturnsEmptyList() {
        when(personDAO.findAll()).thenReturn(emptyList);

        List<Person> persons = personService.findAll();

        assertEquals(persons, emptyList);
    }
    
    @Test
    public void findAllWithNonEmptyListReturnsNonEmptyList() {
        when(personDAO.findAll()).thenReturn(personList);

        List<Person> persons = personService.findAll();
        
        assertEquals(personList, persons);
    }
    
    @Test
    public void findByIdWithWrongIdReturnsNull() {
        when(personDAO.findOne(123L)).thenReturn(null);

        Person bottleNull = personService.findUserById(123L);
        
        assertNull(bottleNull);
    }

    @Test
    public void findByIdWithCorrectIdReturnsBottle() {
        when(personDAO.findOne(0L)).thenReturn(person1);

        Person personFirst = personService.findUserById(0L);
        
        assertEquals(personFirst, person1);
    } 
    
    @Test
    public void findByNameWithWrongIdReturnsNull() {
        when(personDAO.findByLogin("wronglogin1")).thenReturn(null);

        Person personNull = personService.findUserByLogin("wronglogin1");
        
        assertNull(personNull);
    }

    @Test
    public void findByNameWithCorrectIdReturnsBottle() {
        when(personDAO.findByLogin("login1")).thenReturn(person1);

        Person personFirst = personService.findUserByLogin("login1");
        
        assertEquals(personFirst, person1);
    }
    
    @Test
    public void findByEmailWithWrongIdReturnsNull() {
        final String wrongEmail = "bad@mail.com";
        when(personDAO.findByEmail(wrongEmail)).thenReturn(null);

        Person personNull = personService.findUserByLogin(wrongEmail);
        
        assertNull(personNull);
    }

    @Test
    public void findByEmailWithCorrectIdReturnsBottle() {
        final String correctLogin = "name2@mail.cz";
        when(personDAO.findByLogin(correctLogin)).thenReturn(person2);

        Person personFirst = personService.findUserByLogin(correctLogin);
        
        assertEquals(personFirst, person2);
    } 
}
