package cz.muni.fi.pa165;

import cz.muni.fi.pa165.dao.PersonDAO;
import cz.muni.fi.pa165.entity.Person;
import cz.muni.fi.pa165.enums.PersonRole;
import cz.muni.fi.pa165.service.PersonService;
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
public class PersonServiceTest extends AbstractServiceTest{
    
    @Mock
    private PersonDAO personDAO;

    @InjectMocks
    @Autowired
    private PersonService personService;

    private Person p1;

    private Person p2;

    private List<Person> personList;

    private List<Person> emptyList = Collections.emptyList();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        p1 = new Person();
        p1.setName("name");
        p1.setLogin("login1");
        p1.setEmail("name1@mail.cz");
        p1.setRole(PersonRole.MANUFACTURER);

        p2 = new Person();
        p2.setName("name");
        p2.setLogin("login2");
        p2.setEmail("name2@mail.cz");
        p2.setRole(PersonRole.CUSTOMER);

        personList = new ArrayList<>();
        personList.add(p1);
        personList.add(p2);
    }

    @Test
    public void createBottleTypeWillNotCrash() {
        personService.registerPerson(p1,"password");
        verify(personDAO).save(p1);
    }
    
    @Test
    public void findAllWithEmptyListReturnsEmptyList() {
        when(personDAO.findAll()).thenReturn(emptyList);

        List<Person> bottleList = personService.findAll();

        assertEquals(bottleList, emptyList);
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
        when(personDAO.findOne(0L)).thenReturn(p1);

        Person personFirst = personService.findUserById(0L);
        
        assertEquals(personFirst, p1);
    } 
    
    @Test
    public void findByNameWithWrongIdReturnsNull() {
        when(personDAO.findByLogin("wronglogin1")).thenReturn(null);

        Person personNull = personService.findUserByLogin("wronglogin1");
        
        assertNull(personNull);
    }

    @Test
    public void findByNameWithCorrectIdReturnsBottle() {
        when(personDAO.findByLogin("login1")).thenReturn(p1);

        Person personFirst = personService.findUserByLogin("login1");
        
        assertEquals(personFirst, p1);
    }
    
    @Test
    public void findByEmailWithWrongIdReturnsNull() {
        when(personDAO.findByEmail("bad@mail.com")).thenReturn(null);

        Person personNull = personService.findUserByLogin("bad.mail.com");
        
        assertNull(personNull);
    }

    @Test
    public void findByEmailWithCorrectIdReturnsBottle() {
        when(personDAO.findByLogin("name2@mail.cz")).thenReturn(p2);

        Person personFirst = personService.findUserByLogin("name2@mail.cz");
        
        assertEquals(personFirst, p2);
    } 
}
