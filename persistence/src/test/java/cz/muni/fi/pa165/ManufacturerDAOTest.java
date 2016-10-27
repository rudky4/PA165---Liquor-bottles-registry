package cz.muni.fi.pa165;

import cz.muni.fi.pa165.config.PersistenceContext;
import cz.muni.fi.pa165.dao.ManufacturerDAO;
import cz.muni.fi.pa165.entity.Manufacturer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.springframework.test.annotation.DirtiesContext;
/**
 *
 * @author rk + ms
 * @date 2016-10-27
 */
@ContextConfiguration(classes = PersistenceContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class ManufacturerDAOTest {

    @Autowired
    private ManufacturerDAO manufacturerDAO;

    @Test
    public void createManufacturerTest() {
        Manufacturer originalManufacturer = new Manufacturer();
        originalManufacturer.setName("Manufacturer");
        manufacturerDAO.createManufacturer(originalManufacturer);

        List<Manufacturer> manufacturers = new ArrayList<>(manufacturerDAO.getAllManufacturers());
        assertEquals(1, manufacturers.size());

        Manufacturer persistManufacturer = manufacturers.get(0);
        assertEquals(originalManufacturer.getName(), persistManufacturer.getName());
        assertTrue(originalManufacturer.equals(persistManufacturer));
        assertTrue(persistManufacturer.equals(originalManufacturer));
    }

    @Test
    public void createTwoManufacturerTest() {
        Manufacturer originalManufacturer1 = new Manufacturer();
        originalManufacturer1.setName("Manufacturer1");
        manufacturerDAO.createManufacturer(originalManufacturer1);

        Manufacturer originalManufacturer2 = new Manufacturer();
        originalManufacturer2.setName("Manufacturer2");
        manufacturerDAO.createManufacturer(originalManufacturer2);

        
        List<Manufacturer> manufacturers = new ArrayList<>(manufacturerDAO.getAllManufacturers());
        assertEquals(2, manufacturers.size());

        Manufacturer persistManufacturer1 = manufacturers.get(0);
        Manufacturer persistManufacturer2 = manufacturers.get(1);
        assertFalse(persistManufacturer1.equals(persistManufacturer2));
    }

    @Test(expected = javax.validation.ConstraintViolationException.class)
    public void createManufacturerWithNullName() {
        Manufacturer manufacturer = new Manufacturer();
        manufacturerDAO.createManufacturer(manufacturer);

        List<Manufacturer> manufacturers = new ArrayList<>(manufacturerDAO.getAllManufacturers());
    }

    @Test(expected = javax.persistence.PersistenceException.class)
    public void createNotUniqueName() {
        Manufacturer originalManufacturer1 = new Manufacturer();
        originalManufacturer1.setName("Manufacturer");
        manufacturerDAO.createManufacturer(originalManufacturer1);

        Manufacturer originalManufacturer2 = new Manufacturer();
        originalManufacturer2.setName("Manufacturer");
        manufacturerDAO.createManufacturer(originalManufacturer2);

        List<Manufacturer> Manufacturers = new ArrayList<>(manufacturerDAO.getAllManufacturers());
    }

    @Test
    public void updateManufacturer() {
        Manufacturer originalManufacturer = new Manufacturer();
        originalManufacturer.setName("firstName");
        manufacturerDAO.createManufacturer(originalManufacturer);

        List<Manufacturer> Manufacturers = new ArrayList<>(manufacturerDAO.getAllManufacturers());
        assertEquals(1, Manufacturers.size());

        Manufacturer Manufacturer = Manufacturers.get(0);
        Manufacturer.setName("newName");
        manufacturerDAO.updateManufacturer(Manufacturer);

        List<Manufacturer> newManufacturers = new ArrayList<>(manufacturerDAO.getAllManufacturers());
        assertEquals(1, newManufacturers.size());

        Manufacturer updatedManufacturer = newManufacturers.get(0);
        assertEquals(updatedManufacturer.getName(), "newName");
    }

    @Test
    public void removeManufacturer() {
        Manufacturer originalManufacturer = new Manufacturer();
        originalManufacturer.setName("Manufacturer");
        manufacturerDAO.createManufacturer(originalManufacturer);

        List<Manufacturer> Manufacturers = new ArrayList<>(manufacturerDAO.getAllManufacturers());
        assertEquals(1, Manufacturers.size());

        manufacturerDAO.deleteManufacturer(Manufacturers.get(0));

        List<Manufacturer> emptyManufacturers = new ArrayList<>(manufacturerDAO.getAllManufacturers());
        assertEquals(0, emptyManufacturers.size());
    }

    @Test
    public void getManufacturerById() {
        Manufacturer originalManufacturer = new Manufacturer();
        originalManufacturer.setName("Manufacturer");
        manufacturerDAO.createManufacturer(originalManufacturer);

        Manufacturer differentManufacturer = new Manufacturer();
        differentManufacturer.setName("differentManufacturer");
        manufacturerDAO.createManufacturer(differentManufacturer);

        Manufacturer Manufacturer = manufacturerDAO.getManufacturerById(originalManufacturer.getId());
        assertEquals("Expected different manufacturer", Manufacturer.getName(), originalManufacturer.getName());
    }

}