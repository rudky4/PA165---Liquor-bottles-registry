package cz.muni.fi.pa165;

import cz.muni.fi.pa165.entity.Manufacturer;

import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
 *
 * @author rk + ms
 * @date 2016-10-27
 */
public class ManufacturerDAOTest extends AbstractDAOTest {
    @Test
    public void createManufacturerTest() {
        Manufacturer originalManufacturer = new Manufacturer();
        originalManufacturer.setName("Manufacturer");
        manufacturerDAO.save(originalManufacturer);

        List<Manufacturer> manufacturers = manufacturerDAO.findAll();
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
        manufacturerDAO.save(originalManufacturer1);

        Manufacturer originalManufacturer2 = new Manufacturer();
        originalManufacturer2.setName("Manufacturer2");
        manufacturerDAO.save(originalManufacturer2);

        
        List<Manufacturer> manufacturers = manufacturerDAO.findAll();
        assertEquals(2, manufacturers.size());

        Manufacturer persistManufacturer1 = manufacturers.get(0);
        Manufacturer persistManufacturer2 = manufacturers.get(1);
        assertFalse(persistManufacturer1.equals(persistManufacturer2));
    }

    @Test(expected = javax.validation.ConstraintViolationException.class)
    public void createManufacturerWithNullName() {
        Manufacturer manufacturer = new Manufacturer();
        manufacturerDAO.save(manufacturer);

        manufacturerDAO.findAll();
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void createNotUniqueName() {
        Manufacturer originalManufacturer1 = new Manufacturer();
        originalManufacturer1.setName("Manufacturer");
        manufacturerDAO.save(originalManufacturer1);

        Manufacturer originalManufacturer2 = new Manufacturer();
        originalManufacturer2.setName("Manufacturer");
        manufacturerDAO.save(originalManufacturer2);
        manufacturerDAO.findAll();
    }

    @Test
    public void updateManufacturer() {
        Manufacturer originalManufacturer = new Manufacturer();
        originalManufacturer.setName("firstName");
        manufacturerDAO.save(originalManufacturer);

        List<Manufacturer> Manufacturers = manufacturerDAO.findAll();
        assertEquals(1, Manufacturers.size());

        Manufacturer Manufacturer = Manufacturers.get(0);
        Manufacturer.setName("newName");
        manufacturerDAO.save(Manufacturer);

        List<Manufacturer> newManufacturers = manufacturerDAO.findAll();
        assertEquals(1, newManufacturers.size());

        Manufacturer updatedManufacturer = newManufacturers.get(0);
        assertEquals(updatedManufacturer.getName(), "newName");
    }

    @Test
    public void removeManufacturer() {
        Manufacturer originalManufacturer = new Manufacturer();
        originalManufacturer.setName("Manufacturer");
        manufacturerDAO.save(originalManufacturer);

        List<Manufacturer> Manufacturers = manufacturerDAO.findAll();
        assertEquals(1, Manufacturers.size());

        manufacturerDAO.delete(Manufacturers.get(0));

        List<Manufacturer> emptyManufacturers = manufacturerDAO.findAll();
        assertEquals(0, emptyManufacturers.size());
    }

    @Test
    public void getManufacturerById() {
        Manufacturer originalManufacturer = new Manufacturer();
        originalManufacturer.setName("Manufacturer");
        manufacturerDAO.save(originalManufacturer);

        Manufacturer differentManufacturer = new Manufacturer();
        differentManufacturer.setName("differentManufacturer");
        manufacturerDAO.save(differentManufacturer);

        Manufacturer Manufacturer = manufacturerDAO.findOne(originalManufacturer.getId());
        assertEquals("Expected different manufacturer", Manufacturer.getName(), originalManufacturer.getName());
    }

}