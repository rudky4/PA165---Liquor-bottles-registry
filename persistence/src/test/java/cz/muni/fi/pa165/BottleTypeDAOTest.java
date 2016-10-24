package cz.muni.fi.pa165;

import cz.muni.fi.pa165.config.PersistenceContext;
import cz.muni.fi.pa165.dao.BottleTypeDAO;
import cz.muni.fi.pa165.entity.BottleType;
import cz.muni.fi.pa165.enums.AlcoholType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author Jakub Fiser
 *         24/10/2016
 */
@ContextConfiguration(classes = PersistenceContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BottleTypeDAOTest {

    @Autowired
    private BottleTypeDAO bottleTypeDAO;

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

        bottleTypeDAO.createBottleType(morgan);
        bottleTypeDAO.createBottleType(amundsen);
    }

    @Test
    public void createBottleTypeTest() {
        Assert.assertEquals(bottleTypeDAO.getAllBottleTypes().size(), 2);
    }

    @Test
    public void searchExistingBottleType() {
        Assert.assertNotNull(bottleTypeDAO.getBottleTypeById(morgan.getId()));
    }

    @Test
    public void searchNonexistingBottleTypeTest() {
        Assert.assertNull(bottleTypeDAO.getBottleTypeById(Long.MAX_VALUE));
    }

    @Test
    public void deleteBottleTypeTest() {
        bottleTypeDAO.deleteBottleType(amundsen);
        Assert.assertEquals(bottleTypeDAO.getAllBottleTypes().size(), 1);
    }
}
