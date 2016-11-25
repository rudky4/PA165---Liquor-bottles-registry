package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.LaboratoryDTO;
import cz.muni.fi.pa165.dto.PersonDTO;
import cz.muni.fi.pa165.entity.Laboratory;
import cz.muni.fi.pa165.entity.Person;
import cz.muni.fi.pa165.service.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.*;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

/**
 * @author Martin Sumera
 */
public class PersonFacadeTest extends AbstractFacadeTest {

    @Mock
    private PersonService personService;

    @Autowired
    @InjectMocks
    private PersonFacade personFacade;

    private Person person;

    private PersonDTO personDTO;

    private Long personId = 1L;

    private String personName = "name";

    private String password = "password";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        person = new Person();
        person.setName(personName);

        personDTO = new PersonDTO();
        personDTO.setName(personName);
    }

    @Test
    public void testRegisterPerson() {
        when(beanMappingService.mapTo(personDTO, Person.class)).thenReturn(person);

        personFacade.registerPerson(personDTO, password);

        verify(personService).registerPerson(person, password);
        verify(beanMappingService).mapTo(personDTO, Person.class);
    }

    @Test
    public void testChangePassword() {
        when(beanMappingService.mapTo(personDTO, Person.class)).thenReturn(person);

        personFacade.changePassword(personDTO, password);

        verify(personService).changePassword(person, password);
        verify(beanMappingService).mapTo(personDTO, Person.class);
    }

    @Test
    public void testAuthenticate() {
        when(beanMappingService.mapTo(personDTO, Person.class)).thenReturn(person);
        when(personService.authenticate(person, password)).thenReturn(true);

        boolean result = personFacade.authenticate(personDTO, password);

        assertTrue(result);
        verify(personService).authenticate(person, password);
        verify(beanMappingService).mapTo(personDTO, Person.class);
    }

    @Test
    public void testIsPolice() {
        when(beanMappingService.mapTo(personDTO, Person.class)).thenReturn(person);
        when(personService.isPolice(person)).thenReturn(true);

        boolean result = personFacade.isPolice(personDTO);

        assertTrue(result);
        verify(personService).isPolice(person);
        verify(beanMappingService).mapTo(personDTO, Person.class);
    }

    @Test
    public void testFindAll() {
        when(personService.findAll()).thenReturn(Collections.singletonList(person));

        List<PersonDTO> persons = personFacade.findAll();

        assertEquals(person.getName(), persons.get(0).getName());
        verify(personService).findAll();
        verify(beanMappingService).mapTo(Collections.singletonList(person), PersonDTO.class);
    }

    @Test
    public void testFindAllWithNull() {
        when(personService.findAll()).thenReturn(null);

        List<PersonDTO> persons = personFacade.findAll();

        assertNull(persons);
        verify(personService).findAll();
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testFindUserById() {
        when(personService.findUserById(personId)).thenReturn(person);

        PersonDTO personDTO = personFacade.findUserById(personId);

        assertNotNull(personDTO);
        assertEquals(person.getName(), personDTO.getName());
        verify(personService).findUserById(personId);
        verify(beanMappingService).mapTo(person, PersonDTO.class);
    }

    @Test
    public void testFindUserByIdWithNull() {
        when(personService.findUserById(personId)).thenReturn(null);

        PersonDTO personDTO = personFacade.findUserById(personId);

        assertNull(personDTO);
        verify(personService).findUserById(personId);
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testFindUserByLogin() {
        when(personService.findUserByLogin(personName)).thenReturn(person);

        PersonDTO personDTO = personFacade.findUserByLogin(personName);

        assertNotNull(personDTO);
        assertEquals(person.getName(), personDTO.getName());
        verify(personService).findUserByLogin(personName);
        verify(beanMappingService).mapTo(person, PersonDTO.class);
    }

    @Test
    public void testUserByLoginWithNull() {
        when(personService.findUserByLogin(personName)).thenReturn(null);

        PersonDTO personDTO = personFacade.findUserByLogin(personName);

        assertNull(personDTO);
        verify(personService).findUserByLogin(personName);
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testUserByEmail() {
        when(personService.findUserByEmail(personName)).thenReturn(person);

        PersonDTO personDTO = personFacade.findUserByEmail(personName);

        assertNotNull(personDTO);
        assertEquals(person.getName(), personDTO.getName());
        verify(personService).findUserByEmail(personName);
        verify(beanMappingService).mapTo(person, PersonDTO.class);
    }

    @Test
    public void testUserByEmailWithNull() {
        when(personService.findUserByEmail(personName)).thenReturn(null);

        PersonDTO personDTO = personFacade.findUserByEmail(personName);

        assertNull(personDTO);
        verify(personService).findUserByEmail(personName);
        verify(beanMappingService, never()).mapTo(any(), any());
    }
    
}
