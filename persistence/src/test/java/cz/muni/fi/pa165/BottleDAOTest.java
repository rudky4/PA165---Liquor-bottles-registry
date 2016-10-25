package cz.muni.fi.pa165;

import cz.muni.fi.pa165.config.PersistenceContext;
import cz.muni.fi.pa165.dao.BottleDAO;
import cz.muni.fi.pa165.dao.BottleDAOImpl;
import cz.muni.fi.pa165.entity.Bottle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author mhajas
 */
public class BottleDAOTest extends AbstractDAOTest{

    @Autowired
    private BottleDAOImpl bottleDAO;

    @Test
    public void createBottleTest() {
        Bottle b = new Bottle();
        b.setToxic(false);
        b.setStickerID("ID1");
        b.setProduced(new Date());

        bottleDAO.createBottle(b);

        List<Bottle> actual = new ArrayList<>(bottleDAO.getAllBottles());

        assertEquals("Size of list should be 1", 1, actual.size());
        Bottle actualBottle = actual.get(0);
        assertEquals(b.getId(), actualBottle.getId());
        assertEquals(b.isToxic(), actualBottle.isToxic());
        assertEquals(b.getProduced(), actualBottle.getProduced());
        assertEquals(b.getStickerID(), actualBottle.getStickerID());

    }
}
