package cz.muni.fi.pa165;

import cz.muni.fi.pa165.dto.PersonDTO;
import cz.muni.fi.pa165.entity.Person;
import cz.muni.fi.pa165.enums.PersonRole;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * @author mhajas
 */
public class BeanMappingServiceTest extends AbstractServiceTest {

    private Person person;
    private PersonDTO personDTO;

    @Before
    public void beforeTest() {
        person = new Person();
        personDTO = new PersonDTO();

        person.setName("Michal");
        person.setPasswordHash("StrongPassword");
        person.setLogin("mitko501");
        person.setRole(PersonRole.MANUFACTURER);

        personDTO.setName("Michal");
        personDTO.setPassword("StrongPassword");
        personDTO.setLogin("mitko501");
        personDTO.setRole(PersonRole.MANUFACTURER);
    }

    @Test
    public void objectMapping() {
        {
            PersonDTO actual = beanMappingService.mapTo(person, PersonDTO.class);
            assertEquals(personDTO, actual);
        }

        {
            Person actual = beanMappingService.mapTo(personDTO, Person.class);
            assertEquals(person, actual);
        }

    }

    @Test
    public void listMapping() {
        Person person2 = new Person();
        PersonDTO personDTO2 = new PersonDTO();

        person2.setName("Rudolf");
        person2.setPasswordHash("StrongerPassword");
        person2.setLogin("rudky4");
        person2.setRole(PersonRole.CUSTOMER);

        personDTO2.setName("Rudolf");
        personDTO2.setPassword("StrongerPassword");
        personDTO2.setLogin("rudky4");
        personDTO2.setRole(PersonRole.CUSTOMER);

        List<Person> persons = new ArrayList<>();
        List<PersonDTO> personDTOs = new ArrayList<>();

        persons.add(person);
        persons.add(person2);

        personDTOs.add(personDTO);
        personDTOs.add(personDTO2);

        {
            List<PersonDTO> actual = beanMappingService.mapTo(persons, PersonDTO.class);
            assertEquals(personDTOs, actual);
        }

        {
            List<Person> actual = beanMappingService.mapTo(personDTOs, Person.class);
            assertEquals(persons, actual);
        }
    }
}
