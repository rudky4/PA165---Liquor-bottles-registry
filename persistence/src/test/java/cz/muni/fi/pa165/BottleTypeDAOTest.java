package cz.muni.fi.pa165;

import cz.muni.fi.pa165.entity.BottleType;
import cz.muni.fi.pa165.enums.AlcoholType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;

/**
 * @author Jakub Fiser
 *         24/10/2016
 */
public class BottleTypeDAOTest extends AbstractDAOTest {

    private BottleType morgan;
    private BottleType amundsen;

    @Before
    public void initTest() {
        morgan = new BottleType();
        morgan.setName("Captain Morgan");
        morgan.setSize(BigDecimal.valueOf(750));
        morgan.setType(AlcoholType.RUM);
        morgan.setVolume(BigDecimal.valueOf(35));

        amundsen = new BottleType();
        amundsen.setName("Vodka Amundsen");
        amundsen.setSize(BigDecimal.valueOf(1000));
        amundsen.setType(AlcoholType.VODKA);
        amundsen.setVolume(BigDecimal.valueOf(37.5));

        bottleTypeDAO.save(morgan);
        bottleTypeDAO.save(amundsen);
    }

    @Test
    public void createBottleTypeTest() {
        Assert.assertEquals(bottleTypeDAO.findAll().size(), 2);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void createNullNameBottleTypeTest() {
        BottleType bottleType = new BottleType();
        bottleType.setName(null);
        bottleType.setSize(BigDecimal.TEN);
        bottleType.setVolume(BigDecimal.TEN);
        bottleType.setType(AlcoholType.COGNAC);
        bottleTypeDAO.save(bottleType);

        bottleTypeDAO.findAll();
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void createNullVolumeBottleTypeTest() {
        BottleType bottleType = new BottleType();
        bottleType.setName("Name");
        bottleType.setSize(BigDecimal.TEN);
        bottleType.setVolume(null);
        bottleType.setType(AlcoholType.COGNAC);
        bottleTypeDAO.save(bottleType);

        bottleTypeDAO.findAll();
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void createNullSizeBottleTypeTest() {
        BottleType bottleType = new BottleType();
        bottleType.setName("Name");
        bottleType.setSize(null);
        bottleType.setVolume(BigDecimal.TEN);
        bottleType.setType(AlcoholType.COGNAC);
        bottleTypeDAO.save(bottleType);

        bottleTypeDAO.findAll();
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void createNonUniqueBottleTypeTest() {
        BottleType bottleType = new BottleType();
        bottleType.setName(morgan.getName());
        bottleType.setSize(morgan.getSize());
        bottleType.setVolume(morgan.getVolume());
        bottleType.setType(AlcoholType.COGNAC);
        bottleTypeDAO.save(bottleType);

        bottleTypeDAO.findAll();
    }

    @Test
    public void searchExistingBottleType() {
        BottleType b = bottleTypeDAO.findOne(morgan.getId());
        Assert.assertNotNull(b);
        Assert.assertEquals(b, morgan);
        Assert.assertEquals(b.getName(), morgan.getName());
        Assert.assertEquals(b.getSize(), morgan.getSize());
        Assert.assertEquals(b.getVolume(), morgan.getVolume());
    }

    @Test
    public void searchNonExistingBottleTypeTest() {
        Assert.assertNull(bottleTypeDAO.findOne(Long.MAX_VALUE));
    }

    @Test
    public void updateBottleTypeTest() {
        morgan.setSize(BigDecimal.valueOf(500));
        bottleTypeDAO.save(morgan);

        BottleType after = bottleTypeDAO.findOne(morgan.getId());

        Assert.assertNotNull(after);
        Assert.assertEquals(after, morgan);
        Assert.assertEquals(after.getSize(), BigDecimal.valueOf(500));
    }

    @Test
    public void deleteBottleTypeTest() {
        bottleTypeDAO.delete(amundsen);
        Assert.assertEquals(bottleTypeDAO.findAll().size(), 1);
    }
}
