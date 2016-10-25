package cz.muni.fi.pa165;

import cz.muni.fi.pa165.dao.PersonDAO;
import cz.muni.fi.pa165.dao.PersonDAOImpl;
import cz.muni.fi.pa165.entity.Person;
import cz.muni.fi.pa165.enums.PersonRole;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author mhajas
 */
public class PersonDAOTest extends AbstractDAOTest {

    @Autowired
    private PersonDAOImpl personDAO;

    private Person p;
    private Person p2;
    private Person p3;

    @Before
    public void beforeTest() {
        p = new Person();
        p.setLogin("mhajas");
        p.setName("Michal Hajas");
        p.setPassword("password");
        p.setRole(PersonRole.STORE_OWNER);

        p2 = new Person();
        p2.setLogin("rudky4");
        p2.setName("Rudolf");
        p2.setRole(PersonRole.CUSTOMER);
        p2.setPassword("password");

        p3 = new Person();
        p3.setLogin("fiser");
        p3.setName("Jakub");
        p3.setRole(PersonRole.STORE_OWNER);
        p3.setPassword("password");
        personDAO.createPerson(p);
        personDAO.createPerson(p2);
        personDAO.createPerson(p3);
    }

    @Test
    public void test() {
        for(Person p : personDAO.getPersonsOfRole(PersonRole.STORE_OWNER)) {
            System.out.println(p.getName());
        }
    }
}
