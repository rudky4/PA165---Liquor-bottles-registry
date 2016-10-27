package cz.muni.fi.pa165;

import cz.muni.fi.pa165.entity.Bottle;
import cz.muni.fi.pa165.entity.BottleType;
import cz.muni.fi.pa165.enums.AlcoholType;

import java.math.BigDecimal;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Date;
import java.util.List;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;
import org.junit.Before;

import javax.validation.ConstraintViolationException;

/**
 * @author rk
 * @date 2016-10-25
 */
public class BottleDAOTest extends AbstractDAOTest {

    private BottleType dzama_rhum;
    private Bottle b1;
    private Bottle b2;

    @Before
    public void initTest(){
        dzama_rhum = new BottleType();
        dzama_rhum.setName("Dzama Rhum");
        dzama_rhum.setSize(BigDecimal.valueOf(700));
        dzama_rhum.setType(AlcoholType.RUM);
        dzama_rhum.setVolume(BigDecimal.valueOf(40));
        bottleTypeDAO.save(dzama_rhum);

        b1 = new Bottle();
        b1.setToxic(false);
        b1.setStickerID("ID1");
        b1.setProduced(new Date());
        b1.setBottleType(dzama_rhum);

        b2 = new Bottle();
        b2.setToxic(false);
        b2.setStickerID("ID2");
        b2.setProduced(new Date());
        b2.setBottleType(dzama_rhum);

        bottleDAO.save(b1);
        bottleDAO.save(b2);
    }

    @Test(expected = ConstraintViolationException.class)
    public void createInsufficientBottleTest() {
        b2.setProduced(null);
        bottleDAO.save(b2);
        bottleDAO.findAll();
    }

    @Test(expected = DataIntegrityViolationException.class)
        public void createNotUniqeStickerIDBottleTest() {
        b2.setStickerID("ID1");
        bottleDAO.save(b2);

        bottleDAO.findAll();
    }

    @Test
    public void createBottleTest() {
        List<Bottle> actual = bottleDAO.findAll();

        assertEquals(2, actual.size());
        Bottle bottle = actual.get(0);
        assertEquals(b1.getId(), bottle.getId());
        assertEquals(b1.isToxic(), bottle.isToxic());
        assertEquals(b1.getProduced(), bottle.getProduced());
        assertEquals(b1.getStickerID(), bottle.getStickerID());
        assertEquals(b1.getBottleType(), bottle.getBottleType());
    }

    @Test
    public void searchExistingBottleTest() {
        Bottle bottle = bottleDAO.findOne(b1.getId());
        Assert.assertNotNull(bottle);
        Assert.assertEquals(bottle, b1);
        Assert.assertFalse(bottle.isToxic());
        Assert.assertEquals(bottle.getBottleType(), dzama_rhum);
        Assert.assertEquals(bottle.getStickerID(), b1.getStickerID());
        Assert.assertEquals(bottle.getProduced(), b1.getProduced());
    }

    @Test
    public void searchNonexistingBottleTest() {
        Assert.assertNull(bottleDAO.findOne(Long.MAX_VALUE));
    }

    @Test
    public void deleteBottleTest() {
        bottleDAO.delete(b1);
        Assert.assertEquals(bottleDAO.findAll().size(), 1);
    }

}
