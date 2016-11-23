package cz.muni.fi.pa165;

import cz.muni.fi.pa165.entity.Laboratory;
import cz.muni.fi.pa165.entity.Person;
import cz.muni.fi.pa165.enums.PersonRole;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author mhajas
 */
public class LaboratoryDaoTest extends AbstractDAOTest {

    @Test
    public void createLaboratoryTest() {
        Laboratory lab = new Laboratory();
        lab.setName("SPECIAL LAB");

        laboratoryDAO.save(lab);

        List<Laboratory> actual = laboratoryDAO.findAll();

        assertEquals("Number of Laboratories in database should be 1", 1, actual.size());
        assertEquals("Laboratory names should be the same", lab.getName(), actual.get(0).getName());
    }

    @Test(expected = ValidationException.class )
    public void createLaboratoryNullName() {
        Laboratory lab = new Laboratory();
        lab.setName(null);
        laboratoryDAO.save(lab);
        laboratoryDAO.findAll();
    }

    @Test(expected = DataIntegrityViolationException.class )
    public void createLaboratoryDuplicateName() {
        Laboratory lab = new Laboratory();
        Laboratory lab1 = new Laboratory();
        lab.setName("SPECIAL LAB");
        lab1.setName("SPECIAL LAB");
        laboratoryDAO.save(lab);
        laboratoryDAO.save(lab1);
        laboratoryDAO.findAll();
    }

    @Test
    public void getLaboratoryByIdTest() {
        Laboratory lab = new Laboratory();
        Laboratory lab1 = new Laboratory();
        lab.setName("SPECIAL LAB");
        lab1.setName("SPECIAL LAB1");

        laboratoryDAO.save(lab);
        laboratoryDAO.save(lab1);

        Laboratory actual = laboratoryDAO.findOne(lab1.getId());

        assertEquals(lab1.getName(), actual.getName());
    }

    @Test
    public void updateLaboratoryTest() {
        Laboratory lab = new Laboratory();
        lab.setName("SPECIAL LAB");

        laboratoryDAO.save(lab);

        lab.setName("CHANGED NAME");
        laboratoryDAO.save(lab);

        List<Laboratory> actual = laboratoryDAO.findAll();
        assertEquals("Number of Laboratories in database should be 1", 1, actual.size());
        assertEquals("Laboratory name should be changed", "CHANGED NAME", actual.get(0).getName());
    }

    @Test
    public void deleteLaboratoryTest() {
        Laboratory lab = new Laboratory();
        lab.setName("SPECIAL LAB");

        laboratoryDAO.delete(lab);

        List<Laboratory> actual = laboratoryDAO.findAll();
        assertEquals("Number of Laboratories in database should be 0", 0, actual.size());
    }

    @Test
    public void getPersonInLabByIdTest() {
        Laboratory lab = new Laboratory();
        Person person = new Person();
        person.setName("name1");
        person.setLogin("login1");
        person.setPassword("password1");
        person.setRole(PersonRole.POLICE);
        personDAO.save(person);
        long id = personDAO.findAll().get(0).getId();
        
        lab.setName("SPECIAL LAB");
        
        List<Person> persons = Arrays.asList(person);
        
        lab.setPersons(persons);
        
        laboratoryDAO.save(lab);
        
        assertEquals(person,laboratoryDAO.isPersonInLaboratory(id));     
    }
}
